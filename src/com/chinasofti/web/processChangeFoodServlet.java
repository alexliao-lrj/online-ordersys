package com.chinasofti.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.FoodInfo;
import ordersys.db.FoodMapping;

/**
 * Servlet implementation class processChangeFoodServlet
 */
public class processChangeFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public processChangeFoodServlet() {
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
		String fname = request.getParameter("fname");
		String fdescription = request.getParameter("fdescription");
		int flag = Integer.parseInt(request.getParameter("flag"));
		float fprice = Float.parseFloat(request.getParameter("fprice"));
		String bpic = request.getParameter("bpic");
		
		FoodMapping mapping = MapperFactory
				.getDBMapper(FoodMapping.class);
		
		mapping.updateFood(fname, fdescription, flag, fprice, bpic, id);
		
		request.getRequestDispatcher("/allFood.jsp").forward(request,
				response);
		
	}

}
