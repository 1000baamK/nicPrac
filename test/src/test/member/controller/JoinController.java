package test.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import test.common.Crypto;
import test.common.Regex;
import test.member.model.service.MemberService;
import test.member.model.vo.Member;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("/join")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger log = LogManager.getLogger(JoinController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/joinPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//유효성검사
		boolean pass = Regex.regPass(request.getParameter("memberId"),
												 request.getParameter("memberPwd"),
												 request.getParameter("email"),
												 request.getParameter("name"));
		
		log.debug("pass : {}", pass);
		if(pass == true) { //유효성검사통과
			
			//비밀번호 암호화를 위해
			String memberPwd = request.getParameter("memberPwd");
			
			//MD5 암호화 -> Base64 인코딩(반환값 String)
			String encoPwd = Crypto.encodeToBase(memberPwd, true);
			
			log.debug("변경된 비밀번호 : {}", encoPwd);
			
			//해시맵에 암호화된 비밀번호와 넘어온 값들 담기.
//			Map<String, String> param = new HashMap<>();
//			param.put("memberId", request.getParameter("memberId"));
//			param.put("memberPwd", encoPwd);
//			param.put("email", request.getParameter("email"));
//			param.put("name", request.getParameter("name"));
			
			Member m = new Member(request.getParameter("memberId"),
								  encoPwd,
								  request.getParameter("email"),
								  request.getParameter("name"));
			
			int result = new MemberService().insertMember(m);
			
			if(result>0) {
				//회원 등록 성공
				log.debug("회원 등록 완료");
				//메인으로 리다이렉트
				request.getSession().setAttribute("msg", "회원가입 성공");
				response.sendRedirect("/");
			}else {
				//회원 등록 실패
				
				//회원가입 실패메세지들고 가입페이지로
				request.getSession().setAttribute("msg", "회원가입 실패");
				response.sendRedirect(request.getContextPath()+"join");
			}
			
		}else {
			//유효성검사 실패
			//회원가입 실패메세지들고 가입페이지로
			request.getSession().setAttribute("msg", "회원가입 실패");
			response.sendRedirect(request.getContextPath()+"join");
		}
		
		
		
	}
		

}
