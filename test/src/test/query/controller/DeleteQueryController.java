package test.query.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.common.XMLManager;
import test.query.model.vo.Query;

/**
 * Servlet implementation class DeleteQueryController
 */
@WebServlet("/deleteQuery")
public class DeleteQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQueryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mappingId = request.getParameter("mappingId");
		
		int result = XMLManager.deleteXML(mappingId);
		
		if(result>0) {
			//성공
			request.getSession().setAttribute("msg", "삭제 완료했습니다.");
			response.sendRedirect("/");
			
		}else {
			request.getSession().setAttribute("msg", "삭제 실패하였습니다..");
			response.sendRedirect("/");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
