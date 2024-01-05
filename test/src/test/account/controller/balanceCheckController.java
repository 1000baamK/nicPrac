package test.account.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import test.account.model.service.AccountService;
import test.account.model.vo.Account;
import test.common.Masking;
import test.member.model.vo.Member;

/**
 * Servlet implementation class balanceCheckController
 */
@WebServlet("/balCheck")
public class balanceCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static Logger log = LogManager.getLogger(balanceCheckController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public balanceCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//번호가져오기
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		//계좌정보 불러오기
		ArrayList<Account> accounts = new AccountService().selectAccount(memberNo);
		
		log.debug("계좌 리스트 - {}", accounts);
		
		for(Account ac : accounts) {
			ac.setAccountNumber(Masking.AccountMasking(ac.getAccountNumber()));
		}
		
		log.debug("마스킹처리된 계좌 리스트 - {}", accounts);
		
		//ajax통신중 문자깨짐방지+list를 json형태로
		response.setContentType("json/application; charset=UTF-8");
		new Gson().toJson(accounts, response.getWriter());
		
	}

}
