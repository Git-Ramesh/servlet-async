package com.rs.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomReadListener implements ReadListener {
	private static Logger logger = LoggerFactory.getLogger(CustomReadListener.class);
	
	private ServletInputStream inputStream;
	private AsyncContext asyncContext;
    ArrayList<String> responseData = new ArrayList<String>();

	
	public CustomReadListener(ServletInputStream inputStream, AsyncContext asyncContext) {
		this.inputStream = inputStream;
		this.asyncContext = asyncContext;
	}

	@Override
	public void onDataAvailable() throws IOException {
		 asyncContext.getResponse().getWriter().println("onDataAvailable called to read post data on thread : " + Thread.currentThread().getName());
		System.out.println("onDataAvailable");
		Map<String, Object> map = new HashMap<>();
		try {
			int len = -1;
			byte[] buffer = new byte[1024];
			while(inputStream.isReady() && (len=inputStream.read(buffer)) != -1) {
				String data = new String(buffer, 0, len);
                responseData.add("<br>" + len + " bytes of data read: " + data);
			}
			//throw new RuntimeException("abc");
		} catch(IOException ex) {
			logger.error(ex.getMessage(), ex);
			
		}
	}

	@Override
	public void onAllDataRead() throws IOException {
		System.out.println("onAllDataRead");
		asyncContext.getResponse().getWriter().write(responseData.get(0));
		//asyncContext.complete();
		asyncContext.dispatch("/user_info");
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("onError");
		logger.error(t.getMessage(), t);
		System.out.println("Error: " + t);
		asyncContext.complete();
	}

}
