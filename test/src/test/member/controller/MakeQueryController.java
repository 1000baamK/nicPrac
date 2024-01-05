package test.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import test.common.FileManager;
import test.common.MapperBuilder;

/**
 * Servlet implementation class MakeQueryController
 */
@WebServlet("/makeQuery")
public class MakeQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(MakeQueryController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeQueryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/views/makeQueryPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String tableName = request.getParameter("tableName");
		String mapId = request.getParameter("mapId");
		String action = request.getParameter("action");
		String typeKey = request.getParameter("typeKey");
		String typeValue = request.getParameter("typeValue");
		String inputText = request.getParameter("inputText");
		
		log.debug("tableName : {}",tableName);
		log.debug("mapId : {}",mapId);
		log.debug("action : {}",action);
		log.debug("typeKey : {}",typeKey);
		log.debug("typeValue : {}",typeValue);
		log.debug("inputText : {}",inputText);
		
		//xml 파일에 들어갈 text만들기
		String mapperText = new MapperBuilder().buildMapper(mapId, action, typeKey, typeValue, inputText);
		
		
		try {
			new FileManager().updateQuery(mapperText);
		
			//성공
			request.getSession().setAttribute("msg", "쿼리문이 추가되었습니다.");
			response.sendRedirect("/");
		
		} catch(IOException e) {
			//업데이트 실패
			request.getSession().setAttribute("msg", "쿼리문 등록이 실패하였습니다.");
			response.sendRedirect("/makeQuery");
		}
	}

}
