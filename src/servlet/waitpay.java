package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.*;

public class waitpay extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public waitpay() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderMapping omap = MapperFactory
				.getDBMapper(OrderMapping.class);
		int page = 1;
		if (request.getParameter("opage") != null) {
			page = Integer.parseInt(request.getParameter("page"));

			if (((page-1) * 10) >= omap.getAllOrderCount()) {
				page = page - 1;
			} else if (page < 1) {
				page = 1;
			}

		}
		ArrayList<MyorderInfo> topaylist = omap.getAllUnfinishedOrder((page-1)*10, 10);
		HttpSession session = request.getSession();
		session.setAttribute("topaylist", topaylist);
		request.setAttribute("opage", new Integer(page));
		request.getRequestDispatcher("/paybill.jsp").forward(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
