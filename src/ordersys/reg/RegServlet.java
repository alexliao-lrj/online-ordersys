package ordersys.reg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ordersys.db.UserInfo;
import ordersys.db.UserinfoMapping;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;
import com.chinasofti.util.web.upload.MultipartRequestParser;

public class RegServlet extends HttpServlet {

	public RegServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		String sessionToken = session.getAttribute("TOKEN").toString();
		String requestToken = req.getParameter("requestToken");
		System.out.println(sessionToken);
		System.out.println(requestToken);

		String trueValidation = session.getAttribute("TRUEVALIDATION")
				.toString();
		String inputValidation = req.getParameter("val");

		if (requestToken.equals(sessionToken)) {

			if (inputValidation.equals(trueValidation)) {
//				syso
				System.out.println("start");
				MultipartRequestParser parser = new MultipartRequestParser();
				UserInfo info = (UserInfo) parser.parse(req, UserInfo.class);
				UserinfoMapping mapping = MapperFactory
						.getDBMapper(UserinfoMapping.class);
				mapping.insertUser(info);
				System.out.println(info.getAccount());
				System.out.println(info.getBpic());
				req.setAttribute("account", info.getAccount());
				req.setAttribute("pic", info.getBpic());
				
				int flag = info.getFlag();
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
				req.getRequestDispatcher("/reg.jsp").forward(req, resp);
			}

			session.setAttribute("TOKEN", "ICSS");

		} else {

		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

}
