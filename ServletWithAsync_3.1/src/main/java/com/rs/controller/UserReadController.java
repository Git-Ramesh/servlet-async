package com.rs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rs.listener.CustomReadListener;
import com.rs.service.IUserService;
import com.rs.service.impl.UserServiceImpl;

@WebServlet(asyncSupported = true, urlPatterns = { "/user" })
public class UserReadController extends HttpServlet {
	private static final long serialVersionUID = 1017847368520760928L;
	private Logger logger = LoggerFactory.getLogger(UserReadController.class);
	private IUserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		userService = new UserServiceImpl();
		logger.info("UserServiceImpl object created");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*resp.setContentType("text/html");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user_info");
	//	req.getAsyncContext()
		final AsyncContext asyncContext = req.startAsync();
		ServletInputStream inputStream = req.getInputStream();
		inputStream.setReadListener(new ReadListener() {
			
			@Override
			public void onError(Throwable t) {
				System.out.println("onError(t)");
				System.out.println(t.getMessage());
				
			}
			
			@Override
			public void onDataAvailable() throws IOException {
				System.out.println("onDataAvailable()");
				
			}
			
			@Override
			public void onAllDataRead() throws IOException {
				String path = "http://" + req.getServerName() +":"+req.getServerPort()+""+req.getContextPath();
				System.out.println(path);
				System.out.println("onAllDataread()");
				req.setAttribute("users", userService.displayAllUsers());
				//throw new RuntimeException("Developer Exception");
				//asyncContext.dispatch("/user_info");
				try {
					logger.info("forwarding request..");
					dispatcher.forward(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				}
				
			}
		});
		logger.info(Thread.currentThread().getName() + " completed request processig..");*/
		processRequest(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		PrintWriter pw = null;
		resp.setContentType("text/html;charset=UTF-8");
		pw = resp.getWriter();
		pw.println("<h2>Welcome to Nonblocking I/O</h2>");
		AsyncContext asyncContext = req.startAsync();
		ServletInputStream inputStream = req.getInputStream();
		inputStream.setReadListener(new CustomReadListener(inputStream,asyncContext));
		logger.info("processRequest completed");
	}
}
