package ly;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.web.serverpush.MessageProducer;

public class PushJIEZHANGservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PushJIEZHANGservlet() {
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
		System.out.println(GetJIEZHANGservlet.clients.size());
		for (int i = 0; i < GetJIEZHANGservlet.clients.size(); i++) {
			String sessionID = GetJIEZHANGservlet.clients.get(i);
			producer.sendMessage(sessionID, "jiezhang", "¶©µ¥ºÅ£º " + orderId + " ×ÀºÅ£º " + tableId + " µÈ´ý½áÕË");
		}
	}

}
