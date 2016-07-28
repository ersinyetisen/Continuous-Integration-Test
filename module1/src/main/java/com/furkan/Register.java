package com.furkan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.furkan.hib.User;

@WebServlet(urlPatterns = "/register")
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// simple forwarding
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get the parameters from the form elements
		String name = req.getParameter("name").trim();
		String surname = req.getParameter("surname").trim();
		String email = req.getParameter("email").trim();
		String password = req.getParameter("password").trim();

		// create a user with the given parameter
		User user = new User(name, surname, email, password);

		Session session = null;
		try {
			// creating session from HibernateUtility.java
			session = HibernateUtility.getSessionFactory().openSession();

			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			// redirect to index after registered a user
			req.getRequestDispatcher("index").forward(req,resp);
		}
	}
}
