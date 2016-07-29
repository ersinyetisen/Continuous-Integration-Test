package com.furkan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/property")
public class GetProperty extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PropertyUtility prop = new PropertyUtility();
		resp.getWriter().println("This file is reading from properties file at /home/spootrick/config/config.properties");
		resp.getWriter().println("User " + prop.getPropertyValues());
	}
}

