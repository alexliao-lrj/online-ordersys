package com.chinasofti.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.jdbc.template.automapper.MapperFactory;
import com.chinasofti.util.web.serverpush.MessageProducer;
import com.chinasofti.util.web.upload.FormFile;
import com.chinasofti.util.web.upload.MultipartRequestParser;

import ordersys.db.FoodMapping;

/**
 * Servlet implementation class uploadFoodPicServlet
 */
public class uploadFoodPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadFoodPicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MultipartRequestParser parser = new MultipartRequestParser();
		UploadInfo info = (UploadInfo) parser.parse(request, UploadInfo.class);
		FormFile file = info.getFaceimg();
		String path = getServletContext().getRealPath("/foodpic");
		file.saveToFileSystem(request, path + "/" + file.getFileName());
		System.out.println(path + "/" + file.getFileName());
		

		
		MessageProducer producer = new MessageProducer();
		producer.sendMessage(request.getSession().getId(), "upload",
				file.getFileName());

	}

}
