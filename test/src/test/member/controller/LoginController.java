package test.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import test.common.Crypto;
import test.member.model.service.MemberService;
import test.member.model.vo.Member;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger log = LogManager.getLogger(LoginController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/views/loginPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		Member loginMember = new MemberService().selectMember(memberId);
		
		log.debug("가져온 멤버 : {}", loginMember);
		
		String decodedPwd = null;

		if(loginMember != null) {
			//조회된다면 해당 회원의 비밀번호를 디코딩
			decodedPwd = Crypto.decodeToBase(loginMember.getMemberPwd());
			
			log.debug("디코딩된 패스워드 : {}", decodedPwd);
			
			//false도 같이 넘김 -> MD5만 적용하기 위해
			String encoPwd = Crypto.encodeToBase(memberPwd, false);
			
			log.debug("MD만 인코딩 한 입력된패스워드 : {}", encoPwd);
			
			if(encoPwd.equals(decodedPwd)) {
				//로그인성공
				
				log.debug("로그인한 ID : {}", loginMember.getMemberId());
				
				request.getSession().setAttribute("loginMember", loginMember);
				request.getSession().setAttribute("msg", "로그인 성공");
				
				response.sendRedirect("/");
			}else {
				//아이디는 있지만 비밀번호 틀림
				
				log.debug("입력받은 패스워드 인코딩 한 값 : {}",encoPwd);
				log.debug("DB에  pwd값 디코딩값 : {}",decodedPwd);
				
				request.getSession().setAttribute("msg", "비밀번호를 다시 확인해주세요.");
				
				
				response.sendRedirect(request.getContextPath()+"login");
			}
		}else {
			//해당 아이디 존재하지않음
			request.getSession().setAttribute("msg", "로그인실패");
			response.sendRedirect(request.getContextPath()+"login");
		}
	}

}
