package test.query.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import test.common.XMLManager;
import test.query.model.vo.Query;

/**
 * Servlet implementation class InsertQueryController
 */
@WebServlet("/insertQuery")
public class InsertQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(InsertQueryController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertQueryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/views/insertQueryPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mappingId = request.getParameter("mappingId");
		String sql = request.getParameter("sql");
		String description = request.getParameter("description");
		
		log.debug("mappingId : {}", mappingId);
		log.debug("sql : {}", sql);
		log.debug("description : {}", description);
		
		Query query = new Query(mappingId, sql, description);
		
		int result = XMLManager.insertXML(query);
		
		if(result>0) {
			request.getSession().setAttribute("msg", "쿼리문 추가 완료");
			response.sendRedirect("/");
		}else {
			request.getSession().setAttribute("msg", "쿼리문 추가 실패");
			response.sendRedirect("/");
		}
		
	}

}
