package ly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.util.web.serverpush.BaseGetPushMsgServlet;
import com.chinasofti.util.web.serverpush.Message;
import com.chinasofti.util.web.serverpush.MessageHandler;
import com.chinasofti.util.web.serverpush.ServerPushKey;

public class GetXIADANservlet extends BaseGetPushMsgServlet {

	public static ArrayList<String> clients = new ArrayList<String>();

	@Override
	public void initMSGServer(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		clients.add(session.getId());
	}

	@Override
	public MessageHandler setHandler(HttpServletRequest request,
			final HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new MessageHandler() {

			@Override
			public void handle(Hashtable<ServerPushKey, Message> messageQueue,
					ServerPushKey key, Message msg) {
				try {
					response.setCharacterEncoding("utf-8");
					response.getWriter().print(msg.getMsg());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
	}

}
