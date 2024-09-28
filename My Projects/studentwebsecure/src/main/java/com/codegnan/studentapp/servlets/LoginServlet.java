package com.codegnan.studentapp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codegnan.studentapp.util.DatabaseUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean loggedIn = false;
		try (Connection connection = DatabaseUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String role = resultSet.getString("role");
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("role", role);

				// Set loggedIn to true since the user has successfully logged in
				loggedIn = true;
				session.setAttribute("loggedIn", loggedIn);

				response.sendRedirect("students");
			} else {
				response.sendRedirect("login.jsp?error=Invalid username or password");
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
