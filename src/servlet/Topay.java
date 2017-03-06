package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ly.GetJIEZHANGservlet;

import com.chinasofti.util.web.serverpush.MessageProducer;

import ordersys.db.*;

public class Topay extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Topay() {
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
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int n = Integer.parseInt(req.getParameter("odnumber"));
		ArrayList<MyorderInfo> olist = (ArrayList<MyorderInfo>)req.getSession().getAttribute("topaylist");
		MyorderInfo m = olist.get(n-1);
		if(m==null){
			//转发到waitpay servlet
			req.setAttribute("opage", new Integer(1));
			req.getRequestDispatcher("/waitpay").forward(req, resp);
		}
		
		int poid = m.getId();
		int toid = m.getTable_id();
		
		MessageProducer producer = new MessageProducer();
		System.out.println(GetJIEZHANGservlet.clients.size());
		for (int i = 0; i < GetJIEZHANGservlet.clients.size(); i++) {
			String sessionID = GetJIEZHANGservlet.clients.get(i);
			producer.sendMessage(sessionID, "jiezhang", "订单号： " + poid + " 桌号： " + toid + " 等待结账");
		}
		req.getRequestDispatcher("/waitermain.jsp").forward(req, resp);
		
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
