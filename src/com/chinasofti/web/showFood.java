package com.chinasofti.web;

import java.io.IOException;
import java.util.ArrayList;

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

/**
 * Servlet implementation class showFood
 */
public class showFood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showFood() {
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
		FoodMapping mapping = MapperFactory
				.getDBMapper(FoodMapping.class);
		

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		response.setContentType("text/xml");
		
		
		
		ArrayList<FoodInfo> list = mapping.getAllFood((page - 1) * 5, 5);
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			Element root = doc.createElement("foods");
			doc.appendChild(root);

			for (FoodInfo info : list) {
				Element food = doc.createElement("food");
				root.appendChild(food);

				Element id = doc.createElement("id");
				id.setTextContent(info.getId() + "");
				food.appendChild(id);

				Element fname = doc.createElement("name");
				fname.setTextContent(info.getName());
				food.appendChild(fname);

				Element fdescription = doc.createElement("description");
				fdescription.setTextContent(info.getDescription());
				food.appendChild(fdescription);
				
				Element flag = doc.createElement("flag");
				flag.setTextContent(info.getFlag() + "");
				food.appendChild(flag);
				
				Element fprice = doc.createElement("price");
				fprice.setTextContent(info.getPrice() + "");
				food.appendChild(fprice);
				
				Element bpic = doc.createElement("bpic");
				bpic.setTextContent(info.getBpic());
				food.appendChild(bpic);

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
