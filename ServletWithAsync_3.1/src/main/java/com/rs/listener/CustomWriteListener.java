package com.rs.listener;

import java.io.IOException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rs.service.IUserService;
import com.rs.service.impl.UserServiceImpl;

public class CustomWriteListener implements WriteListener {
	private IUserService userService = new UserServiceImpl();
	private Logger logger = LoggerFactory.getLogger(CustomWriteListener.class);
	private ServletOutputStream outputStream;
	private AsyncContext asyncContext;

	public CustomWriteListener(ServletOutputStream outputStream, AsyncContext asyncContext) {
		this.outputStream = outputStream;
		this.asyncContext = asyncContext;
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("onError...");
		logger.error(throwable.getMessage(), throwable);
	}

	@Override
	public void onWritePossible() throws IOException {
		System.out.println("WriteListener:onWritePossible() called to send response data on thread : "
				+ Thread.currentThread().getName());
		
		asyncContext.getRequest().setAttribute("users", userService.displayAllUsers());
		outputStream.print(userService.displayAllUsers().toString());
		outputStream.flush();
		outputStream.close();
		asyncContext.complete();
		/*RequestDispatcher dispatcher = asyncContext.getRequest().getRequestDispatcher("/user_info");
		try {
			dispatcher.forward(asyncContext.getRequest(), asyncContext.getResponse());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//asyncContext.dispatch("/user_info");
		
	}
}
