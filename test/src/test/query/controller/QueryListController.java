package test.query.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class QueryListController
 */
@WebServlet("/queryList")
public class QueryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(QueryListController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		File file = new File("C:\\\\Users\\\\USER\\\\Desktop\\\\eGovFrameDev-3.10.0-64bit\\\\workspace\\\\test\\\\src\\\\resources\\\\sql\\\\sql.xml");
		
		if(file.exists()) {
			//파일이 존재
			ArrayList<Query> qList = XMLManager.selectXML();
			
			log.debug("뽑혓냐 : {}", qList);
			
			//파일존재여부는 뷰에서 처리
			request.setAttribute("qList", qList);
			request.getRequestDispatcher("/views/queryListPage.jsp").forward(request, response);
				
			
		}else {
			//없음
			int result = XMLManager.createXML();
			
			if(result>0) {
				request.getSession().setAttribute("msg", "기존 XML파일이 존재하지 않아 새로 생성합니다. 다시 시도하세요.");
				response.sendRedirect("/");
			}else {
				request.getSession().setAttribute("msg", "파일 생성 실패.");
				response.sendRedirect("/");
			}
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
