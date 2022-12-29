package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log= LoggerFactory.getLogger(CommentController.class);
//	private String destPage;안만들거임-> 가져올거임 보내는거 안할거임
	private CommentService csv;
	private int isOk;
       

    public CommentController() {
    	csv=new CommentServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
//		res.setContentType("text/html; charset=UTF-8");값을 안보낼거라서 할 필요 없음
		
		String uri= req.getRequestURI();// /cmt/list/10
		String pathUri= uri.substring("/cmt/".length());//list/10
		String path=pathUri;// /cmt/list/10 /cmt/post /cmt/modify
		String pathVar=null;
		if(pathUri.contains("/")) {
			path= pathUri.substring(0, pathUri.lastIndexOf("/"));//list
			pathVar=pathUri.substring(pathUri.lastIndexOf("/")+1);//10
			
		}
		log.info(">>>uri>>"+uri);
		log.info(">>>pathUri>>"+pathUri);
		log.info(">>>path>>"+path);
		log.info(">>>pathVar>>"+pathVar);
		
		switch(path) {
		case "post": 
			try {
				//js에서 보낸 데이터를 stringbuffer로 읽어들이는 작업
//				req.getParameter();를 사용했지만 지금은 json 형태를 사용하기 때문에 다른 방법을 사용
				StringBuffer sb = new StringBuffer();
				String line=null;
				BufferedReader br= req.getReader();
				while((line = br.readLine())!=null) {//값이 널이 아니라면(값이 있다면)
					sb.append(line);//stringbuffer에 json으로 보낸 객체를 읽어옴
				}
				log.info(">>> sb: "+sb.toString());//sb는 stringbuffer의 값이기 때문에 tostring해줘야함
				//위에는 string형태이기 때문에 다시 객체형태로 보내줘야함.(아까 받았던 json 파일 사용)
				//객체로 생성
				//읽어들이는 줄
				JSONParser parser =new JSONParser();//객체인척하는 스트링을 객체화하는것
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());//아까 읽어온걸 json 객체로 만들겠다//.parse(read in) : 읽어온걸 뭐시기...
				//jsonobj는 key,value형태로
				
				int bNo= Integer.parseInt(jsonObj.get("bNo").toString());//jsonObj가 key, value형태로 되어있음.리턴타입이 object임 -> tostring으로 변환
				String writer = jsonObj.get("writer").toString();
				String content= jsonObj.get("content").toString();
				isOk = csv.post(new CommentVO(bNo, writer, content));
				//화면에 출력//읽어드린걸 뿌리는 줄
				PrintWriter out=res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info(">>> Comment > post > error");
				e.printStackTrace();
			}
			break;
			
		case "list":
			try {
				//리스트 요청 먼저 해야함
				//해당 번호의 게시글에 달린 댓글 리스트를 가져와야함
				log.info(pathVar);
				List<CommentVO> list = csv.getList(Integer.parseInt(pathVar));
				log.info(">>> Comment > list > DB 성공 ");
				//여기까지 온거는 값을 COMMENTVO클래스 형태로 가져온것
				//JSON 형태로 변환
				JSONObject[] jsonObjArr = new JSONObject[list.size()];//하나의 객체의 집합: bNo, cNo, writer, content, reg_at의 오브젝트 형태를 배열로 묶는 것
				JSONArray jsonObjList= new JSONArray();//객체들 리스트
				
				for(int i=0;i<list.size();i++) {
					jsonObjArr[i] = new JSONObject();//jsonobject는 key,value형태
					jsonObjArr[i].put("cNo", list.get(i).getcNo());
					jsonObjArr[i].put("bNo", list.get(i).getbNo());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("reg_at", list.get(i).getReg_at());
					
					jsonObjList.add(jsonObjArr[i]);					
				}
				String jsonData = jsonObjList.toJSONString();//toJSONString...?
				
				PrintWriter out = res.getWriter();
				out.print(jsonData);//js가 result로 받음
				
				
			} catch (Exception e) {
				log.info(">>> Comment > list > error");
				e.printStackTrace();
			}
			break;
		
		case "remove": 
			try {
				isOk=csv.remove(Integer.parseInt(pathVar));
				log.info("Comment remove"+(isOk>0?"성공":"실패"));
				PrintWriter out = res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info(">>> Comment > remove > error");
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				StringBuffer sb=new StringBuffer();
				String line="";
				BufferedReader br= req.getReader();
				while((line=br.readLine())!=null) {
					sb.append(line);
				}
				log.info(">>sb: "+sb.toString());
				//객체로 생성
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int cNo = Integer.parseInt(jsonObj.get("cno").toString());
				String content= jsonObj.get("content").toString();
				isOk = csv.update(new CommentVO(cNo, content));
				log.info("modify"+(isOk>0?"success": "failed"));
				
				//화면에 출력
				PrintWriter out = res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info(">>> Comment > modify > error");
				e.printStackTrace();
			}
			break;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
