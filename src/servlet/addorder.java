package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ly.GetXIADANservlet;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;
import com.chinasofti.util.web.serverpush.MessageProducer;

import ordersys.db.*;

/**
 * Servlet implementation class addorder
 */
//@WebServlet("/addorder")
public class addorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addorder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TableMapping tmap = MapperFactory
				.getDBMapper(TableMapping.class);
		OrderMapping omap = MapperFactory
				.getDBMapper(OrderMapping.class);
		int otid = Integer.parseInt(req.getParameter("ordertid"));
		double psum = tmap.getPriceSum(otid);
		System.out.println("--------------"+psum+"----------------------");
		MyorderInfo mi = new MyorderInfo();
		mi.setTable_id(otid);
		mi.setPrice(psum);
		mi.setStarttime(new Date(System.currentTimeMillis()));
		mi.setFlag(0);
		mi.setEndtime(null);
		omap.insertOrder(mi);
		/*本单结束，req全部清空，重定向至服务生主页*/
		
		MessageProducer producer = new MessageProducer();
		for (int i = 0; i < GetXIADANservlet.clients.size(); i++) {
			String sessionID = GetXIADANservlet.clients.get(i);
			producer.sendMessage(sessionID, "xiadan", " 桌号： "+otid+"    已下单，请尽快备菜");
		}
		
		resp.sendRedirect(req.getContextPath()+"/waitermain.jsp");
		//req.getRequestDispatcher("/waitermain.jsp").forward(req, resp);
	}

}
