package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ordersys.db.*;

/**
 * Servlet implementation class Fdetail
 */
//@WebServlet("/Fdetail")
public class Fdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fdetail() {
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
		int n = Integer.parseInt(req.getParameter("number"));
		ArrayList<FoodInfo> fl = (ArrayList<FoodInfo>)req.getSession().getAttribute("aflist");
		FoodInfo f = fl.get(n-1);
		if(f==null){
			//×ª·¢µ½alldish servlet
			req.setAttribute("page", new Integer(1));
			req.getRequestDispatcher("/alldish").forward(req, resp);
		}
		req.getSession().setAttribute("thisfood", f);
		req.getRequestDispatcher("/fooddetail.jsp").forward(req,resp);
	}

}
