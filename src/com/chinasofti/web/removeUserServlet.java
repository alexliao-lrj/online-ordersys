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
import ordersys.db.UserInfo;
import ordersys.db.UserinfoMapping;

/**
 * Servlet implementation class removeUserServlet
 */
public class removeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeUserServlet() {
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
		int page = 1;
		int id = 0;

		if (request.getParameter("pageIndex") != null) {
			page = Integer.parseInt(request.getParameter("pageIndex"));
		}
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		mapping.deleteUser(id);
		response.setContentType("text/xml");
		ArrayList<UserInfo> list = mapping.getAllUser((page - 1) * 5, 5);
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			Element root = doc.createElement("users");
			doc.appendChild(root);

			for (UserInfo info : list) {
				Element user = doc.createElement("user");
				root.appendChild(user);

				Element uid = doc.createElement("id");
				uid.setTextContent(info.getId() + "");
				user.appendChild(uid);

				Element uaccount = doc.createElement("account");
				uaccount.setTextContent(info.getAccount());
				user.appendChild(uaccount);

				Element upass = doc.createElement("password");
				upass.setTextContent(info.getPassword());
				user.appendChild(upass);
				
				Element usalary = doc.createElement("salary");
				usalary.setTextContent(info.getSalary() + "");
				user.appendChild(usalary);
				
				Element uphone = doc.createElement("phone");
				uphone.setTextContent(info.getPhone() + "");
				user.appendChild(uphone);
				
				Element uflag = doc.createElement("flag");
				uflag.setTextContent(info.getFlag()+"");
				user.appendChild(uflag);
				
				
				Element bpic = doc.createElement("bpic");
				bpic.setTextContent(info.getBpic());
				user.appendChild(bpic);

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
