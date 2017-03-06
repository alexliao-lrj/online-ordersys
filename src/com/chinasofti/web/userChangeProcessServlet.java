package com.chinasofti.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.FoodMapping;
import ordersys.db.UserinfoMapping;

/**
 * Servlet implementation class userChangeProcessServlet
 */
public class userChangeProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userChangeProcessServlet() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String uaccount = request.getParameter("account");
		String upass = request.getParameter("password");
		double  usalary = Double.parseDouble(request.getParameter("salary"));
		String uphone = request.getParameter("phone");
		int flag = Integer.parseInt(request.getParameter("flag"));
		String bpic = request.getParameter("bpic");
		
		UserinfoMapping mapping = MapperFactory
				.getDBMapper(UserinfoMapping.class);
		
		mapping.updateUser(uaccount, upass, usalary, uphone, flag, bpic, id);
		
		
		request.getRequestDispatcher("/allUsers.jsp").forward(request,
				response);
		
	}
}
