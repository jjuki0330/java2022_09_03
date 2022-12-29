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
import domain.PagingVO;
import handler.PagingHandler;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String destPage;
	private int isOk;
	private RequestDispatcher rdp;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService msv;
       

    public MemberController() {
        msv=new MemberServiceImpl();
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
		case "login": 
			log.info("login check 1");
			destPage="/member/login.jsp";
			break;
		case "login_auth": 
			try {
				log.info("login_auth check 1");
				log.info(req.getParameter("email"));
				MemberVO mvo= msv.login(new MemberVO(req.getParameter("email"),req.getParameter("password")));
				log.info("login_auth check 4");
				if(mvo!=null) {
					if(!mvo.getPassword().equals(req.getParameter("password"))) {
						req.setAttribute("msg_login_pwd_fail", 0);
						destPage="login";
					}else {
						HttpSession ses = req.getSession();//연결된 세션이 없다면 생성
						ses.setAttribute("ses", mvo);
						req.setAttribute("msg_login_complete", 0);
						ses.setMaxInactiveInterval(30*60);
						destPage="/";						
					}
				}else {
					log.info("로그인 실패");
					req.setAttribute("msg_login_fail", 0);//계정이 없는 경우
					destPage="login";
				}
			} catch (Exception e) {
				log.info("login_auth error");
				e.printStackTrace();
			}
			break;
		case "log_out":
			try {
				HttpSession ses = req.getSession();
				ses.invalidate();
				destPage="/";
			} catch (Exception e) {
				log.info("log_out error");
				e.printStackTrace();
			}
			break;
		case "detail": 
			try {		
				
				destPage="/member/detail.jsp";
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
		case "join":
			destPage="/member/register.jsp";
			break;
		case "register":
			try {
				log.info("join check 1");
				MemberVO mvo = msv.getDetail(req.getParameter("email"));
				if(mvo==null) {
					String email=req.getParameter("email");
					String password=req.getParameter("password");
					String nickname=req.getParameter("nickname");
					if(!password.equals("")&&!nickname.equals("")) {
						mvo =new MemberVO(email,password,nickname);
						int isOk=msv.insert(mvo);
						log.info(isOk>0?"register check 4":"register fail");
						destPage="login";						
					}else {
						req.setAttribute("msg_register_fail",0);
						destPage="/member/register.jsp";
					}
				}else{
					log.info("중복된 이메일");
					req.setAttribute("msg_email_check", 0);
					destPage="/member/register.jsp";
				}
				
			} catch (Exception e) {
				log.info("register error");
				e.printStackTrace();
			}
			break;
		case "modify": 
			try {
				destPage="/member/modify.jsp";
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;
		case "edit": 
			try {
				log.info("edit check 1");
				String email= req.getParameter("email");
				String password= req.getParameter("password")==null?req.getParameter("priorpassword"):req.getParameter("password");
				String nickname= req.getParameter("nickname");
				MemberVO mvo= new MemberVO(email, password, nickname);
				int isOk = msv.edit(mvo);
				if(isOk>0) {
					HttpSession ses = req.getSession();
					ses.setAttribute("ses", mvo);
				}
				log.info("edit check 4");
				destPage="/";
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}
			break;
		case "list": 
			try {
				log.info("list check 1");
				PagingVO pgvo = new PagingVO();//기본생성자라서 1페이지를 보여줌
				int totCount = msv.getPageCnt();
				List <MemberVO> list = msv.getList(pgvo);
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);
				req.setAttribute("pgh", ph);
				destPage="/member/list.jsp";
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
				
				int totCount = msv.getPageCnt();
				List<MemberVO> list = msv.getList(pgvo);
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				System.out.println(ph.getStartPage());
				System.out.println(ph.getEndPage());
				req.setAttribute("list", list);
				req.setAttribute("pgh", ph);
				destPage="/member/list.jsp";
				
			} catch (Exception e) {
				log.info("pagination error!");
				e.printStackTrace();
			}
			break;
		case "delete":
			try {
				log.info("delete check 1");
				String email=req.getParameter("email");
				int isOk = msv.delete(email);
				if(isOk>0) {
					HttpSession ses = req.getSession();
					ses.invalidate();
					destPage="/";
				}
				log.info(isOk>0?"delete check 4": "delete fail");
			} catch (Exception e) {
				log.info("delete error!");
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
