package com.rs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author ramesh
 */
/*
 * Note: This DynamicServlet not register with web.xml and @WebServlet
 */
public class DynamicServlet extends HttpServlet {
	private static final long serialVersionUID = 678616435464405648L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicServlet.class);

	public DynamicServlet() {
		System.out.println("DynamicServlet: 0-param constr");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("Get request...");
		processRequest(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("Post request...");
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		LOGGER.info("Processing request...");
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");

		pw.print("<h1>DynamicServlet Successfully processed the request..<h1>");
		pw.close();
	}
}
