package com.chinasofti.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.FoodInfo;
import ordersys.db.FoodMapping;
import ordersys.db.UserInfo;
import ordersys.db.UserinfoMapping;

/**
 * Servlet implementation class changeUserServlet
 */
public class changeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		UserinfoMapping mapping = MapperFactory
				.getDBMapper(UserinfoMapping.class);
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		UserInfo info =  mapping.getUserById(id);
		request.setAttribute("user", info);
		request.getRequestDispatcher("/changeUser.jsp").forward(request,
				response);
		
		
	}

}
