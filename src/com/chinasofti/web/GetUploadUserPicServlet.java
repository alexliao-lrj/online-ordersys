package com.chinasofti.web;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.util.web.serverpush.BaseGetPushMsgServlet;
import com.chinasofti.util.web.serverpush.Message;
import com.chinasofti.util.web.serverpush.MessageHandler;
import com.chinasofti.util.web.serverpush.ServerPushKey;

/**
 * Servlet implementation class GetUploadUserPicServlet
 */
public class GetUploadUserPicServlet extends BaseGetPushMsgServlet {

	@Override
	public MessageHandler setHandler(HttpServletRequest request,
			final HttpServletResponse response) {
		// TODO Auto-generated method stub

		return new MessageHandler() {

			@Override
			public void handle(Hashtable<ServerPushKey, Message> messageQueue,
					ServerPushKey key, Message msg) {
				try {
					response.getWriter().print(msg.getMsg());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
	}

}