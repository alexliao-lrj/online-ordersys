package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.*;

/**
 * Servlet implementation class adddish
 */
//@WebServlet("/adddish")
public class adddish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adddish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FoodMapping fmap = MapperFactory
				.getDBMapper(FoodMapping.class);
		TableMapping tmap = MapperFactory
				.getDBMapper(TableMapping.class);
		FoodInfo f = (FoodInfo)req.getSession().getAttribute("thisfood");
		String fname = f.getName();
		int tid = Integer.parseInt(req.getParameter("tableid"));
		int count = Integer.parseInt(req.getParameter("dishnum"));
		MytableInfo mt = new MytableInfo();
		mt.setFood_name(fname);
		mt.setId(tid);
		mt.setCount(count);
		tmap.insertFood(mt);
		req.setAttribute("page", new Integer(1));
		/*page置为0，发给alldish servlet处理*/
		req.getRequestDispatcher("/alldish").forward(req, resp);
	}

}
