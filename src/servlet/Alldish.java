package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.*;

/**
 * Servlet implementation class Alldish
 */
//@WebServlet("/Alldish")
public class Alldish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Alldish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*ÔÚreqÉÏ°ópage*/
		FoodMapping fmap = MapperFactory
				.getDBMapper(FoodMapping.class);
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));

			if (((page-1) * 5) >= fmap.getAllFoodCount()) {
				page = page - 1;
			} else if (page < 1) {
				page = 1;
			}

		}
		ArrayList<FoodInfo> fl = fmap.getAllFood((page-1)*5, 5);
		HttpSession session = request.getSession();
		session.setAttribute("aflist", fl);
		request.setAttribute("page", new Integer(page));
		request.getRequestDispatcher("/alldish.jsp").forward(request,response);
	}

}
