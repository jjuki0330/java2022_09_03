package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log= LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	
	private MemberService msv;
       

    public MemberController() {
        msv=new MemberServiceImpl();
    }


	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri= req.getRequestURI();///mem/list형태로 가져옴
	    String path=uri.substring(uri.lastIndexOf("/")+1);
	    
	    log.info("uri>>"+uri);
	    log.info("path>>"+path);
	    
	    switch(path) {
	    case "login":
	    	//login 페이지
	    	destPage="/member/login.jsp";
	    	break;
	    case "login_auth": //실제 로그인이 일어나는 케이스
	    	try {
	    		MemberVO mvo = msv.login(
	    				new MemberVO(req.getParameter("email"),
	    						req.getParameter("pwd")));
	    		log.info("login 객체 in");
	    		if(mvo!=null) {
	    			HttpSession ses = req.getSession();//연결된 세션이 없다면 새로 생성하세요(세션 가져오기)
	    			//ses라는 이름으로 mvo를 바인딩
	    			ses.setAttribute("ses", mvo);
	    			ses.setMaxInactiveInterval(30*60);//로그인 유지시간(10분 설정)
	    			destPage="/";
	    		}else {
	    			log.info("로그인 실패");
	    			req.setAttribute("msg_login", 0);//여기에 담는 값은 맘대로 해도 됨."failed"해도 됨.
	    			destPage="login";
	    		}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	break;
	    	
	    case "join":
	    	destPage="/member/join.jsp";
	    	break;
	    case "register"://회원가입 db 저장
	    	try {
	    		String email=req.getParameter("email");
	    		MemberVO mvo=msv.getDetail(email);
	    		if(mvo!= null) {
	    			req.setAttribute("email_dupli_msg", 0);
	    			destPage="join";
	    		}else {
	    			isOk= msv.register(new MemberVO(
	    					req.getParameter("email"),
	    					req.getParameter("pwd"),
	    					req.getParameter("nick_name")));
	    			
	    			log.info("join>> "+(isOk>0?"ok":"fail"));
	    			destPage="login";
	    			
	    		}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	break;
	    case "logout":
	    	//연결된 세션이 있다면 해당 세션을 가져와야함
	    	try {
				HttpSession ses= req.getSession();
				MemberVO mvo=(MemberVO)ses.getAttribute("ses");
				String email = mvo.getEmail();
				log.info(email);
				ses.invalidate();//세션끊기
				//로그인 정보 email을 주고 로그인 시간 update
//				isOk = msv.lastLogin(req.getParameter("email"));
				isOk = msv.lastLogin(email);
				log.info("lastlogin>>"+(isOk>0? "ok": "fail"));
				destPage="/";
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	break;
	    case "modify":
	    	try {
				String email=req.getParameter("email");
				MemberVO mvo = msv.getDetail(email);
				log.info("modify check 1");
				req.setAttribute("mvo", mvo);
				destPage="/member/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	break;
	    case "edit":
	    	try {
	    		MemberVO mvo=new MemberVO(req.getParameter("email"),req.getParameter("pwd"),req.getParameter("nick_name"),
	    				req.getParameter("reg_at"),req.getParameter("last_login"));
				int isOk= msv.getEdit(mvo);
				if(mvo!=null) {
	    			HttpSession ses = req.getSession();//연결된 세션이 없다면 새로 생성하세요(세션 가져오기)
	    			//ses라는 이름으로 mvo를 바인딩
	    			ses.setAttribute("ses", mvo);
	    			ses.setMaxInactiveInterval(30*60);//로그인 유지시간(10분 설정)
	    		}else {
	    			req.setAttribute("msg_login", 0);//여기에 담는 값은 맘대로 해도 됨."failed"해도 됨.
	    		}
				log.info("update "+(isOk>0?"성공": "실패"));
				destPage="/";				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	break;
	    case "remove":
	    	try {
				String email=req.getParameter("email");
				log.info("delete check 1");
				int isOk=msv.delete(email);
				log.info("delete check 4");
				HttpSession ses= req.getSession();
				ses.invalidate();//세션끊기
				destPage="/";
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	break;
	    case "list":
	    	try {
				List<MemberVO> list = msv.getList();
				req.setAttribute("list", list);
				destPage="/member/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	break;
	    }
	    
	    
	    //목적지 주소 값 세팅
	    rdp=req.getRequestDispatcher(destPage);
	    //정보 싣고 보내기
	    rdp.forward(req, res);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
