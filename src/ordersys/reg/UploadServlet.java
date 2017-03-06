package ordersys.reg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ordersys.db.UploadInfo;

import com.chinasofti.util.web.serverpush.MessageProducer;
import com.chinasofti.util.web.upload.FormFile;
import com.chinasofti.util.web.upload.MultipartRequestParser;

public class UploadServlet extends HttpServlet {

	public UploadServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MultipartRequestParser parser = new MultipartRequestParser();
		UploadInfo info = (UploadInfo) parser.parse(request, UploadInfo.class);
		FormFile file = info.getPic();
		String path = getServletContext().getRealPath("/userhead");
		file.saveToFileSystem(request, path + "/" + file.getFileName());

		MessageProducer producer = new MessageProducer();
		producer.sendMessage(request.getSession().getId(), "upload",
				file.getFileName());

	}

	public void init() throws ServletException {

	}

}
