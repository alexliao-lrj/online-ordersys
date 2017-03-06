package ly;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.web.serverpush.MessageProducer;

/**
 * Servlet implementation class GetXIADANservlet
 */

public class PushXIADANservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PushXIADANservlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int tableId = Integer.parseInt(request.getParameter("table"));
		int orderId = Integer.parseInt(request.getParameter("order"));
		System.out.println(tableId);
		System.out.println(orderId);
		MessageProducer producer = new MessageProducer();
		System.out.println(GetXIADANservlet.clients.size());
		for (int i = 0; i < GetXIADANservlet.clients.size(); i++) {
			String sessionID = GetXIADANservlet.clients.get(i);
			producer.sendMessage(sessionID, "xiadan", "订单号： "+orderId+" 桌号： "+tableId+" 已下单，请尽快备菜");
		}
	}

}
