package com.furkan;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.furkan.hib.User;

@WebServlet(urlPatterns = "/list")
public class ListUsers extends HttpServlet {

	private SessionFactory factory;
	private Session session;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Hibernate version: " + org.hibernate.Version.getVersionString());
		resp.getWriter().println("Data retrieven from the database:");
		resp.getWriter().println("NAME\t\tSURNAME\t\tEMAIL\t\tPASSWORD");

		factory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
		session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List users = session.createQuery("From User").list();
			for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				resp.getWriter().println(user.getName() + "\t\t" + user.getSurname() + "\t\t" + user.getEmail() + "\t\t"
						+ user.getPassword());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
