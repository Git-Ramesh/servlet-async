package com.rs.controller;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rs.listener.CustomWriteListener;
import com.rs.service.IUserService;
import com.rs.service.impl.UserServiceImpl;

@WebServlet(asyncSupported = true, urlPatterns = { "/userWrite"})
public class UserWriteController extends HttpServlet {
	private static final long serialVersionUID = 1017847368520760928L;
	private Logger logger = LoggerFactory.getLogger(UserWriteController.class);
	private IUserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		userService = new UserServiceImpl();
		logger.info("UserServiceImpl object created");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		AsyncContext asyncContext = req.startAsync();
		ServletOutputStream output = resp.getOutputStream();
		output.println("<!DOCTYPE html>");
        output.println("<html>");
        output.println("<head>");
        output.println("<title>Writing Asynchronously</title>");
        
        output.println("<meta charset=\"utf-8\"></meta>");
        output.println("<title>Servlet 3.1 WAS Classic tests</title>");
        output.println("<style>");
        output.println(".frm1{padding: 15px;background-color: #9666af; margin-bottom: 10px;}");
        output.println(".frm2{padding-left: 25px; font-family: Verdana; color: #440055;}");
        output.println(".big{font-size: 26px; color: white;}");
        output.println(".small{font-size: 12px;}");
        output.println("button, select{padding: 5px; padding-left: 20px; padding-right: 20px; margin:10px; width: 270px}");
        output.println("</style>");
        output.println("</head>");
        output.println("<body>");
        output.println("<body>");
        output.println("<div class=\"frm1\">");
        output.println("<div class=\"big\"> WAS Java EE 7 Sample - Non-blocking I/O using Servlet 3.1</div>");
        output.println("</div>");
        output.println("<div class=\"frm2\">"); 
        output.println("</div>");
        
        output.println("</head>");
        output.println("<body>");
        output.println("<h3>The following response data was written asynchronously using a Servelt 3.1 WriteListener</h3>");
        output.setWriteListener(new CustomWriteListener(output, asyncContext));
		logger.info("processRequest completed");
	}
}
