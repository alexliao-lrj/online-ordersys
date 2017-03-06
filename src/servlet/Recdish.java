package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.*;

/**
 * Servlet implementation class Recdish
 */
//@WebServlet("/Recdish")
public class Recdish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recdish() {
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
		RecommendMapping mapping = MapperFactory
				.getDBMapper(RecommendMapping.class);
		FoodMapping fmap = MapperFactory
				.getDBMapper(FoodMapping.class);
		ArrayList<RecommendInfo> rlist = mapping.getAllRecommend();
		ArrayList<FoodInfo> flist = new ArrayList<FoodInfo>();
		
		for(RecommendInfo ri:rlist){
			FoodInfo f = fmap.getFoodById(ri.getFood_id());
			System.out.println(f.getName());
			if (f!=null){
				flist.add(f);
			}
		}
		HttpSession session = req.getSession();
		session.setAttribute("reclist", flist);
		req.getRequestDispatcher("/rdish.jsp").forward(req,resp);
	}

}
