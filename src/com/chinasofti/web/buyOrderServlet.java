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

/**
 * Servlet implementation class buyOrderServlet
 */
public class buyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyOrderServlet() {
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
		OrderMapping mapping = MapperFactory
				.getDBMapper(OrderMapping.class);
		int page = 1;
		int id = 0;

		if (request.getParameter("pageIndex") != null) {
			page = Integer.parseInt(request.getParameter("pageIndex"));
		}
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		mapping.setFinished(id,new Date(System.currentTimeMillis()));
		
		response.setContentType("text/xml");
		
		ArrayList<MyorderInfo> list = mapping.getAllUnfinishedOrder((page - 1) * 5, 10);
		
		//ArrayList<FoodInfo> list = mapping.getAllFood((page - 1) * 5, 5);
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			Element root = doc.createElement("orders");
			doc.appendChild(root);
			
			int maxPage = list.size() / 5 + 1;
			int count = 0;
			
			for (MyorderInfo info : list) {
				
				Element order = doc.createElement("order");
				root.appendChild(order);

				Element oid = doc.createElement("id");
				oid.setTextContent(info.getId() + "");
				order.appendChild(oid);

				Element table_id = doc.createElement("table_id");
				table_id.setTextContent(info.getTable_id()+"");
				order.appendChild(table_id);

				Element starttime = doc.createElement("starttime");
				starttime.setTextContent(info.getStarttime()+"");
				order.appendChild(starttime);
				
				Element endtime = doc.createElement("endtime");
				endtime.setTextContent(info.getFlag() + "");
				order.appendChild(endtime);
				
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
