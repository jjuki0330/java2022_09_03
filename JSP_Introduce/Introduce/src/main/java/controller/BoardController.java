package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String destPage;
    private int isOk;
    private String savePath;//파일 경로를 저장할 변수
    private final String UTF8 = "utf-8";//encoding 설정할 때 필요
    private RequestDispatcher rdp;
    private static final Logger log=LoggerFactory.getLogger(BoardController.class);
    
    private BoardService bsv;

    public BoardController() {
        bsv=new BoardServiceImpl();
    }


	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("test/html; charset=UTF-8");
		
		String uri=req.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/")+1);
		
		log.info("uri>> "+uri);
		log.info("path>> "+path);
		
		switch(path) {
		case "post": 
			try {
				log.info("post check 1");
				HttpSession ses=req.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				req.setAttribute("ses", mvo);
				destPage="/board/post.jsp";
			} catch (Exception e) {
				log.info("post error");
			}
			break;
		case "insert": 
			try {
				log.info("insert check 1");
				//파일을 저장할 물리적인 경로 설정(파일을 업로드할 대 설정되는 파일 경로)
				savePath=getServletContext().getRealPath("/_fileUpload");
				File fileDir= new File(savePath);
				log.info("저장 위치>> "+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);//저장할 위치를 file객체로 지정
				fileItemFactory.setSizeThreshold(2*1024*1024);//파일을 저장할 때 저장할 값을 가지고 가는 임시 메모리의 용량 설정(byte 단위)
				
				BoardVO bvo= new BoardVO();
				
				//multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				for(FileItem item: itemList) {
					switch(item.getFieldName()) {
					case "writer": bvo.setWriter(item.getString(UTF8));break;
					case "content": bvo.setContent(item.getString(UTF8));break;
					case "image_file":
						//이미지가 있는지 없는지 체크
						if(item.getSize()>0) {//이미지가 있는 경우
							String fileName= item.getName().substring(item.getName().lastIndexOf("/")+1);//파일이름만 분리
							fileName=System.currentTimeMillis()+"_"+fileName;
							File UploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("파일 경로 + 이름: "+UploadFilePath);
							
							//저장
							try {
								item.write(UploadFilePath);//해당 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								
								//Thumbnail작업: 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(UploadFilePath)
								.size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
							
						}
						break;
					
					}
				}
				isOk= bsv.insert(bvo);
				log.info(">>> insert  > "+(isOk>0?"ok":"fail"));
//				destPage="/board/list";//이게 왜 안되지?
				destPage="list";
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
			
		case "detail": 
			try {
				log.info("detail check 1");
				int bNo=Integer.parseInt(req.getParameter("bNo"));
				System.out.println(bNo);
				BoardVO bvo= bsv.getDetail(bNo);
				log.info("bvo>> "+bvo);
				req.setAttribute("bvo", bvo);
				destPage="/board/detail.jsp";
				
			} catch (Exception e) {
				log.info("detail error!");
				e.printStackTrace();
				
			}
			break;
		case "list": 
			try {
				log.info("list check 1");
				PagingVO pgvo = new PagingVO();//기본생성자라서 1페이지를 보여줌
				int totCount = bsv.getPageCnt();
				List <MemberVO> list = bsv.getList(pgvo);
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);
				req.setAttribute("pgh", ph);
				destPage="/board/list.jsp";
			} catch (Exception e) {
				log.info("list error!");
				e.printStackTrace();
			}
			break;
		case "pagination":
			try {
				int pageNo= Integer.parseInt(req.getParameter("pageNo"));
				int qty= Integer.parseInt(req.getParameter("qty"));
				
				PagingVO pgvo=new PagingVO(pageNo, qty);
				System.out.println(pgvo.getPageNo()+"&"+pgvo.getQty());
				
				int totCount = bsv.getPageCnt();
				List<MemberVO> list = bsv.getList(pgvo);
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				System.out.println(ph.getStartPage());
				System.out.println(ph.getEndPage());
				req.setAttribute("list", list);
				req.setAttribute("pgh", ph);
				destPage="/board/list.jsp";
				
			} catch (Exception e) {
				log.info("pagination error!");
				e.printStackTrace();
			}
			break;
		case "modify":
			try {
				log.info("modify check 1");
				int bNo=Integer.parseInt(req.getParameter("bNo"));
				BoardVO bvo= bsv.getDetail(bNo);
				req.setAttribute("bvo", bvo);
				log.info("modify check 4");
				destPage="/board/modify.jsp";												
			} catch (Exception e) {
				log.info("modify error!");
				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				log.info("edit check 1");
				savePath=getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory= new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">> Update 준비 >>");
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				
				String old_file = null;
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "bNo": bvo.setbNo(Integer.parseInt(item.getString(UTF8)));break;
					case "content": bvo.setContent(item.getString(UTF8));break;
					case "image_file":
						old_file=item.getString(UTF8);						
						break;
					case "new_file":
						if(item.getSize()>0) {
							if(old_file!=null) {
								FileHandler fileHandler=new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
							}
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);
							log.info("newFile Name>>"+fileName);
							fileName = System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							try {
								item.write(uploadFilePath);
								bvo.setImage_file(fileName);
								log.info(">> upload imag_file"+bvo.getImage_file());
								
								Thumbnails.of(uploadFilePath)
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e) {
								log.info(">>> File write on disk fail");
								e.printStackTrace();
							}
						}else {
							bvo.setImage_file(old_file);
						}
						break;
					}
				}
				System.out.println(bvo);
				isOk = bsv.getEdit(bvo);
				log.info(">>update> "+(isOk>0?"ok":"fail"));
				
				destPage="detail?bNo="+bvo.getbNo();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
			
		case "delete":
			try {
				int bNo=Integer.parseInt(req.getParameter("bNo"));
				
				String imageFileName = bsv.getFileName(bNo);
				FileHandler fileHandler = new FileHandler();
				savePath=getServletContext().getRealPath("_/fileUpload");
				isOk= fileHandler.deleteFile(imageFileName, savePath);
				log.info("fileDelete>> "+(isOk>0?"ok":"fail"));
				isOk = bsv.delete(bNo);
				log.info("all delete>> "+(isOk>0?"ok":"fail"));
				destPage="list";
				
			} catch (Exception e) {
				log.info("delete error!");
				e.printStackTrace();
			}
			break;
		}
		
		rdp=req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
