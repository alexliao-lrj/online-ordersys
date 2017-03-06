package com.chinasofti.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;

import ordersys.db.FoodInfo;
import ordersys.db.FoodMapping;
import ordersys.db.MyorderInfo;
import ordersys.db.OrderMapping;
import ordersys.db.RecommendInfo;
import ordersys.db.RecommendMapping;

/**
 * Servlet implementation class showUnfinishedOrders
 */
public class showUnfinishedOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showUnfinishedOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderMapping mapping = MapperFactory
				.getDBMapper(OrderMapping.class);
		
		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		response.setContentType("text/xml");
		
    	ArrayList<MyorderInfo> list = mapping.getAllUnfinishedOrder((page - 1) * 5, 10);
    	
    	if (list == null){
    		System.out.println("null");
    	}
    	
    	//ArrayList<RecommendInfo> recommendList = mapping2.getAllRecommend();
    	//ArrayList<FoodInfo> list = new ArrayList();//mapping.getAllFood((page - 1) * 5, 5);
		System.out.print("size:" + list.size());


		
		
		
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			Element root = doc.createElement("orders");
			doc.appendChild(root);
			
			
			for (MyorderInfo info : list) {
				
				Element order = doc.createElement("order");
				root.appendChild(order);

				Element id = doc.createElement("id");
				id.setTextContent(info.getId() + "");
				order.appendChild(id);

				Element table_id = doc.createElement("table_id");
				table_id.setTextContent(info.getTable_id()+"");
				order.appendChild(table_id);

				Element starttime = doc.createElement("starttime");
				starttime.setTextContent(info.getStarttime()+"");
				order.appendChild(starttime);
				
				Element price = doc.createElement("price");
				price.setTextContent(info.getPrice() + "");
				order.appendChild(price);
				

			}

			Element pageNode = doc.createElement("page");
			pageNode.setTextContent(page + "");
			root.appendChild(pageNode);

			TransformerFactory
					.newInstance()
					.newTransformer()
					.transform(new DOMSource(doc),
							new StreamResult(response.getOutputStream()));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
