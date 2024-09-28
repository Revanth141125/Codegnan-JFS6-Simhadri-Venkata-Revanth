package com.codegnan.studentapp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.codegnan.studentapp.util.DatabaseUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
		String role = (String) session.getAttribute("role");
		if (session == null || loggedIn == null || !loggedIn) {
			response.sendRedirect("login.jsp");
			return;
		}
		else if ("user".equals(role)) {
			response.sendRedirect("students");
			return;
		} else {
			request.getRequestDispatcher("add-student.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
		String role = (String) session.getAttribute("role");
		if (session == null || loggedIn == null || !loggedIn) {
			response.sendRedirect("login.jsp");
			return;
		}
		if (!"admin".equals(role)) {
			response.sendRedirect("students");
			return;
		}
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String course = request.getParameter("course");
		try (Connection connection = DatabaseUtil.getConnection()) {
			String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, Integer.parseInt(age));
			statement.setString(3, course);
			statement.executeUpdate();
			response.sendRedirect("students");
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
