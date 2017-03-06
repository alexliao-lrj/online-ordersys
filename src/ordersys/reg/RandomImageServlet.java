package ordersys.reg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RandomImageServlet extends HttpServlet {

	public RandomImageServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		char[] element = "ABCDEFGHIJKMNLOPQRSTUVWXYZ".toCharArray();
		Random random = new Random();
		String str = "";

		for (int i = 0; i < 5; i++) {
			int randomIndex = Math.abs(random.nextInt())%element.length;
			str += element[randomIndex];
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("TRUEVALIDATION", str);
		
		resp.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(50,30,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 100, 50);
		g.setColor(Color.RED);
		g.drawString(str, 10, 20);
		ImageIO.write(image, "jpeg", resp.getOutputStream());

		
		
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		
	}

}
