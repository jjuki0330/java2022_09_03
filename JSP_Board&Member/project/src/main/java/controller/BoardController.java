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
	private static final Logger log=LoggerFactory.getLogger(BoardController.class);
    private RequestDispatcher rdp;
    private String destPage;
    private int isOk;
    private String savePath; //파일경로를 저장할 변수
    private final String UTF8="utf-8";// encoding 설정할 때 필요
    
    private BoardService bsv;

    public BoardController() {
        bsv= new BoardServiceImpl();
        
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri= req.getRequestURI();
		String path= uri.substring(uri.lastIndexOf("/")+1);
		
		log.info("uri>>"+uri);
		log.info("path>>"+path);
		
		switch(path) {
		case "post": 
			destPage="/board/post.jsp";			
			break;
		case "insert":
			try {
//				System.out.println(112321312);
//				BoardVO bvo= new BoardVO(req.getParameter("title"), req.getParameter("writer"), req.getParameter("content"));
//				int isOk=bsv.insert(bvo);
//				log.info("insert"+(isOk>0?"성공": "실패"));
//				destPage="list";
				//파일을 저장할 물리적인 경로 설정(파일을 업로드 할 때 설정되는 파일 경로)
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir= new File(savePath);
				log.info("저장 위치"+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);//저장할 위치를 file객체로 지정
				fileItemFactory.setSizeThreshold(2*1024*1024);//파일을 저장할 때 저장할 값을 가지고가는 임시 메모리의 용량 설정(byte 단위)
				
				BoardVO bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				for(FileItem item: itemList) {
					switch(item.getFieldName()) {
					case "title": 
						bvo.setTitle(item.getString(UTF8)); //인코딩 형식을 담아서 변환//숫자일 때는 item.getString()전에 parseint 해주기
						break;
					case "writer": 
						bvo.setWriter(item.getString(UTF8));
						break;
					case "content": 
						bvo.setContent(item.getString(UTF8));
						break;
					case "image_file": 
						//이미지가 있는지 없는지 체크
						if(item.getSize()>0) {//이미지가 있는 경우 //데이터의 크기를 바이트 단위로 리턴
//							String fileName = item.getName();//경로가 포함된 전체 이름을 가져옴// ~~~~/dog.jsp
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);//파일 이름만 분리
							//시스템의 현재 시간 _ dog.jsp //currentTimeMillis(): 현재 시스템의 밀리초
							fileName= System.currentTimeMillis()+"_"+fileName;//이렇게 해주는 이유는: 같은 파일을 올려도 서로 다른 객체로 형성해주기 위해
							File UploadFilePath = new File(fileDir+File.separator+fileName);
							//filedDir(파일이 저장된 실제 경로)+파일 구분자+파일명
							log.info("파일 경로 +이름: "+UploadFilePath);
							
							//저장
							try {
								item.write(UploadFilePath);//해당 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								
								//썸네일 작업: 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(UploadFilePath)
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));//기존 파일명 앞에 th_를 붙여서 썸네일 파일 구분
							} catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
									
						}
						break;
					
					}
				}
				isOk = bsv.insert(bvo);
				log.info(">>> insert > "+(isOk>0? "ok": "fail"));
				destPage="list";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list":
	         try {
	        	 log.info("list check 1");
	           HttpSession ses= req.getSession();
	           MemberVO mvo = (MemberVO) ses.getAttribute("ses");
	           String email = mvo.getEmail();
	           List<BoardVO> list = bsv.getMyList(email);
	           req.setAttribute("list", list);
	           log.info("list check 4");
	           destPage="/board/list.jsp";
	         } catch (Exception e) {
	            log.info("Controller List Error");
	            e.printStackTrace();
	         }
	         break;
		case "pageList":
			try {
				PagingVO pgvo= new PagingVO();
				int totCount = bsv.getPageCnt();//전체 카운트 호출
				List<BoardVO> list=bsv.getListPage(pgvo);//limit을 이용한 한페이지 리스트 호출 
				PagingHandler ph=new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);//한페이지에 나와야하는 리스트 보내기
				req.setAttribute("pgh", ph);//페이지 정보 보내기
				destPage="/board/pageList.jsp";
			} catch (Exception e) {
				log.info("paging error!");
				e.printStackTrace();
			}
			
			break;
		case "page":
			try {
				int pageNo=Integer.parseInt(req.getParameter("pageNo"));
				int qty=Integer.parseInt(req.getParameter("qty"));
				
				PagingVO pgvo= new PagingVO(pageNo, qty);
				System.out.println(pgvo.getPageNo()+" & "+pgvo.getQty());
				
				int totCount = bsv.getPageCnt();//전체 카운트 호출
				List<BoardVO> list=bsv.getListPage(pgvo);//limit을 이용한 한페이지 리스트 호출 
				PagingHandler ph=new PagingHandler(pgvo, totCount);
				System.out.println(ph.getStartPage());
				System.out.println(ph.getEndPage());
				req.setAttribute("list", list);//한페이지에 나와야하는 리스트 보내기
				req.setAttribute("pgh", ph);//페이지 정보 보내기
				destPage="/board/pageList.jsp";
			} catch (Exception e) {
				log.info("subPage error!");
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
				int bNo= Integer.parseInt(req.getParameter("bNo"));
				int isOk= bsv.addcount(bNo);
				BoardVO bvo = bsv.getdetail(bNo);
				req.setAttribute("bvo", bvo);
				log.info((isOk>0?"addcount 성공": "addcount 실패"));
				log.info("detail check 4");
				destPage="/board/detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				savePath=getServletContext().getRealPath("/_fileUpload");//실 내 프로젝트의 경로를 구해라
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);
				
				BoardVO bvo= new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>update 준비>>");
				
				List<FileItem> itemList = fileUpload.parseRequest(req);//업로드에 실어진 req의 값을 itemList에 담아주세요
				
				String old_file=null;//기존에 있었던 파일
				for(FileItem item: itemList) {
					switch(item.getFieldName()) {
					case "bNo":
						bvo.setbNo(Integer.parseInt(item.getString(UTF8)));
						break;
					case "title":
						bvo.setTitle(item.getString(UTF8));
						break;
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
					case "image_file":
						//old_file
						old_file= item.getString(UTF8);
						//이전파일이 있다고 무조건 지우면 안됨 -> 이미지 파일을 수정하려던게 아닐 수도 있기 때문 
						//=> new_file이 들어오면 삭제할 예정
						break;
					case "new_file":
						if(item.getSize()>0) {//새로운 파일이 들어온 상황
							if(old_file != null) {
								//파일 핸들러 작업(기존 파일을 삭제)
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);//deletefile의 return값이 int(1 or 0)//savePath:저장경로
								//기존파일 삭제 완료								
							}
							//경로가 포함된 전체 경로와 파일이름 생성(새로운 파일을 저장)
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);//윈도우 말고 다른 운영체제를 쓰는 경우엔 "/" 대신 File.separator 사용
							log.info("newFile Name"+fileName);
							fileName= System.currentTimeMillis()+"_"+fileName;//실제 저장될 내 파일이름
							File uploadFilePath = new File(fileDir + File.separator+fileName);//실제 업로드 되어야하는 파일의 경로
							try {
								item.write(uploadFilePath);//자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								log.info(">> upload img_file"+bvo.getImage_file());
								
								//썸네일 작업
								Thumbnails.of(uploadFilePath)
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							} catch (Exception e) {
								log.info(">>> File write on disk fail ");
								e.printStackTrace();
							}
						}else {//새로운 파일이 없다면 기존파일을 bvo 객체에 담기
							bvo.setImage_file(old_file);
						}
						break;
					}//switch구문의 닫는 괄호
				}
				isOk = bsv.edit(bvo);
				log.info(">>update> "+(isOk>0?"ok":"fail"));
							
				destPage="detail?bNo="+bvo.getbNo();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;			
//				BoardVO bvo = new BoardVO(
//						Integer.parseInt(req.getParameter("bNo")),
//						req.getParameter("title"),
//						req.getParameter("content"));
//				int isOk=bsv.edit(bvo);
//				log.info("edit"+(isOk>0?"성공":"실패"));
//				destPage="pageList";
			
		case "modify":
			try {
				log.info("modifyPage 1");
				int bNo = Integer.parseInt(req.getParameter("bNo"));
				BoardVO bvo=bsv.getdetail(bNo);
				
				HttpSession ses = req.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				if(mvo!=null) {
//	               BoardVO bvo = bsv.checkModify(new BoardVO(bno, writer));
					if(bvo.getWriter().equals(mvo.getEmail())) {
						req.setAttribute("bvo", bvo);
						destPage = "/board/modify.jsp";
					}else {
						req.setAttribute("msg_edit", 0);
						destPage = "detail";
					}
				}else {
					req.setAttribute("msg_modify_login_null", 0);
					destPage = "/mem/login";
				}
				log.info("modifyPage 4");
//				int bNo= Integer.parseInt(req.getParameter("bNo"));
//				BoardVO bvo= bsv.getdetail(bNo);
//				req.setAttribute("bvo", bvo);
//				log.info("modify check 4");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				
				int bNo = Integer.parseInt(req.getParameter("bNo"));
//				//image_file Name 불러오기
				String imageFileName = bsv.getFileName(bNo);
				FileHandler fileHandler = new FileHandler();
				savePath= getServletContext().getRealPath("/_fileUpload");
				isOk = fileHandler.deleteFile(imageFileName, savePath);
				log.info("fileDelete >> "+(isOk>0?"ok":"fail"));
				isOk=bsv.remove(bNo);
				log.info("all delete >> "+(isOk>0?"ok":"fail"));
				destPage="list";
				
//				int bNo= Integer.parseInt(req.getParameter("bNo"));
//				log.info("실제 이미지 파일 삭제");
//				//image_file Name 불러오기
//				BoardVO bvo=new BoardVO();
//				bvo=bsv.getdetail(bNo);
//				
//				HttpSession ses = req.getSession();
//		        MemberVO mvo = (MemberVO) ses.getAttribute("ses");
//		           if(mvo!=null) {
//		        	   if(bvo.getWriter().equals(mvo.getEmail())) {
////		            	  req.setAttribute("bvo", bvo);
//		        		   int isOk=bsv.remove(bNo);
//		        		   if(isOk>0) {
//		        			   FileHandler fileHandler = new FileHandler();
//		        			   savePath=getServletContext().getRealPath("/_fileUpload");
//		        			   isOk = fileHandler.deleteFile(bvo.getImage_file(), savePath);
//		        		   }
//		        		   log.info("remove"+(isOk>0? "성공": "실패"));
//		        		   destPage = "list";
//		        	   }else {
//		        		   req.setAttribute("msg_remove", 0);
//		        		   destPage = "list";
//		        	   }
//		            }else {
//		            	req.setAttribute("msg_remove_login_null", 0);
//		            	destPage = "/member/login.jsp";
////		               BoardVO bvo = bsv.checkModify(new BoardVO(bno, writer));
//		            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

		
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service(request, response);
	}

}
