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
 * Servlet implementation class addFoodServlet
 */
public class addFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addFoodServlet() {
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
		FoodMapping mapping = MapperFactory
				.getDBMapper(FoodMapping.class);
		
		String fname = request.getParameter("name");
		String fdescription = request.getParameter("description");
		int flag = Integer.parseInt(request.getParameter("flag"));
		float fprice = Float.parseFloat(request.getParameter("price"));
		String bpic = request.getParameter("bpic");
		FoodInfo f = new FoodInfo();
		f.setBpic(bpic);
		f.setDescription(fdescription);
		f.setFlag(flag);
		f.setName(fname);
		f.setPrice(fprice);
		mapping.insertFood(f);
		
		request.getRequestDispatcher("/allFood.jsp").forward(request,
				response);
	}

}
