package ordersys.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ordersys.db.UserInfo;
import ordersys.db.UserinfoMapping;
import com.chinasofti.util.jdbc.template.automapper.MapperFactory;
import com.chinasofti.util.web.serverpush.MessageProducer;
import com.chinasofti.util.web.upload.MultipartRequestParser;

public class LoginServlet extends HttpServlet {

	public LoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String sessionToken = session.getAttribute("LOGINTOKEN").toString();
		String requestToken = req.getParameter("requestToken");
		System.out.println(sessionToken);
		System.out.println(requestToken);
		if (requestToken.equals(sessionToken)) {
			MultipartRequestParser parser = new MultipartRequestParser();
			UserInfo info = (UserInfo) parser.parse(req, UserInfo.class);
			UserinfoMapping mapping = MapperFactory
					.getDBMapper(UserinfoMapping.class);
			String account = info.getAccount();
			String password = info.getPassword();

			UserInfo i = mapping.getUserByAccount(account);
			

			if (i == null) {
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			} else {
				if (password.equals(i.getPassword())) {
					session.setAttribute("account", account);
					session.setAttribute("pic", i.getBpic());
					int flag = i.getFlag();
					switch (flag) {
					case 1:
						//TODO
						req.getRequestDispatcher("/admin.jsp").forward(req,resp);
						System.out.println("2");
						break;
					case 2:
						req.getRequestDispatcher("/waitermain.jsp").forward(req,
								resp);
						System.out.println("3");
						break;
					case 3:
						req.getRequestDispatcher("/chef").forward(req,
								resp);
						System.out.println("4");
					}
				} else {
					System.out.println(5);
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
			}

			session.setAttribute("LOGINTOKEN", "ICSS");
		}

	}

	public void init() throws ServletException {

	}

}
