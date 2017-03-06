package com.chinasofti.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;
import com.chinasofti.util.web.serverpush.MessageProducer;

import ordersys.db.FoodMapping;
import ordersys.db.RecommendInfo;
import ordersys.db.RecommendMapping;

/**
 * Servlet implementation class addAsRecommendFoodServlet
 */
public class addAsRecommendFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addAsRecommendFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RecommendMapping mapping = MapperFactory
				.getDBMapper(RecommendMapping.class);
		int id = Integer.parseInt(request.getParameter("id"));
		RecommendInfo f = new RecommendInfo();
		f.setFood_id(id);
		
		mapping.insertRecommend(f);
		
		MessageProducer producer = new MessageProducer();
		producer.sendMessage(request.getSession().getId(), "addAsRecommendFood",
				"ok");
		
		
	}

}
