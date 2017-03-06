package ordersys.chef;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.MyorderInfo;
import ordersys.db.OrderMapping;

public class ChefServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChefServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {

	}

	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		OrderMapping mapping = MapperFactory.getDBMapper(OrderMapping.class);

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));

			if ((page * 5) >= mapping.getAllOrderCount()) {
				page = page - 1;
			} else if (page < 1) {
				page = 1;
			}

		}
		ArrayList<MyorderInfo> list = mapping.getAllUnfinishedOrder(
				(page - 1) * 5, 5);
		request.setAttribute("orderlist", list);
		request.setAttribute("page", new Integer(page));
		request.getRequestDispatcher("/chef.jsp").forward(request, response);
	}
}
