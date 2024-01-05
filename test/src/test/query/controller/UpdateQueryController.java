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
 * Servlet implementation class UpdateQueryController
 */
@WebServlet("/updateQuery")
public class UpdateQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQueryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mappingId = request.getParameter("mappingId");
		
		Query query = XMLManager.selectOne(mappingId);
		
		request.setAttribute("query", query);
		request.getRequestDispatcher("/views/updateQueryPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		Query query = new Query(request.getParameter("mappingId"),
								request.getParameter("sql"),
								request.getParameter("description"));
		
		int result = XMLManager.updateXML(query);
		
		if(result>0) {
			
			request.getSession().setAttribute("msg", "업데이트 성공하였습니다.");
			response.sendRedirect("/");
			
		}else {
			request.getSession().setAttribute("msg", "업데이트 실패하였습니다.");
			response.sendRedirect("/");
		}
	}

}
