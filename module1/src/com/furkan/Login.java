package com.furkan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("../../login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get email and password from the form elements
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// set email and password for success page
		req.setAttribute("email", email);
		req.setAttribute("passwd", password);

		// check the fields
		if (email.matches("q") && password.matches("1")) {
			req.getRequestDispatcher("success.jsp").forward(req, resp);
		} else {
			req.setAttribute("errorMsg", "email or password is wrong!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

}
