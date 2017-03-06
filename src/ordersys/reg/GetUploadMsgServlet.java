package ordersys.reg;

import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chinasofti.util.web.serverpush.BaseGetPushMsgServlet;
import com.chinasofti.util.web.serverpush.Message;
import com.chinasofti.util.web.serverpush.MessageHandler;
import com.chinasofti.util.web.serverpush.ServerPushKey;

public class GetUploadMsgServlet extends BaseGetPushMsgServlet {

	@Override
	public MessageHandler setHandler(HttpServletRequest request,
			final HttpServletResponse response) {

		return new MessageHandler() {

			@Override
			public void handle(Hashtable<ServerPushKey, Message> messageQueue,
					ServerPushKey key, Message msg) {
				try {
					response.getWriter().print(msg.getMsg());
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		};
	}

}