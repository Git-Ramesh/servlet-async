package com.rs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rs.model.User;
import com.rs.service.IUserService;
import com.rs.service.impl.UserServiceImpl;

@WebServlet(asyncSupported = true, urlPatterns = { "/user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1017847368520760928L;
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	private IUserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		userService = new UserServiceImpl();
		logger.info("UserServiceImpl object created");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		//req.getRequestDispatcher("/user_info").forward(req, resp);
		final AsyncContext asyncContext = req.startAsync(req, resp);
		asyncContext.start(() -> {
			logger.info(Thread.currentThread().getName() + " executing runnable task");
			// HttpServletRequest httpServletRequest = (HttpServletRequest)
			// asyncContext.getRequest();
			// HttpServletResponse httpServletResponse = (HttpServletResponse)
			// asyncContext.getResponse();
			// pw.print(userService.displayAllUsers());
			// asyncContext.complete();
			sleep(5000);
			List<User> users = userService.displayAllUsers();
			asyncContext.getRequest().setAttribute("users", users);
			asyncContext.dispatch("/user_info");
			//java.lang.IllegalStateException: 
			//Calling [asyncComplete()] is not valid for a request with Async state [DISPATCHING]
			//asyncContext.complete();

		});
		logger.info(Thread.currentThread().getName() + " completed request processig..");
	}
	public void sleep(long millisec) {
		try {
			Thread.sleep(millisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
