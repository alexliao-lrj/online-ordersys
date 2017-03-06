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
 * Servlet implementation class addUserServlet
 */
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUserServlet() {
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
		UserinfoMapping mapping = MapperFactory
				.getDBMapper(UserinfoMapping.class);
		
		String uaccount = request.getParameter("account");
		String upass = request.getParameter("password");
		double  usalary = Double.parseDouble(request.getParameter("salary"));
		String uphone = request.getParameter("phone");
		int flag = Integer.parseInt(request.getParameter("flag"));
		String bpic = request.getParameter("bpic");
		UserInfo f = new UserInfo();
		f.setAccount(uaccount);
		f.setBpic(bpic);
		f.setFlag(flag);
		f.setPassword(upass);
		f.setPhone(uphone);
		f.setSalary(usalary);

		mapping.insertUser(f);
		
		request.getRequestDispatcher("/allUsers.jsp").forward(request,
				response);
	}

}
