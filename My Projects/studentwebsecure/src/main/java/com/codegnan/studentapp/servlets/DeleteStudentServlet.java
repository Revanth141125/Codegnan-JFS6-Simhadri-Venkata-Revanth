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

public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       HttpSession session = request.getSession();
	       String role = (String) session.getAttribute("role");
	       if (!"admin".equals(role)) {
	           response.sendRedirect("students");
	           return;
	       }
	       String id = request.getParameter("id");
	       try (Connection connection = DatabaseUtil.getConnection()) {
	           String sql = "DELETE FROM students WHERE id = ?";
	           PreparedStatement statement = connection.prepareStatement(sql);
	           statement.setInt(1, Integer.parseInt(id));
	           statement.executeUpdate();
	           response.sendRedirect("students");
	       } catch (SQLException e) {
	           throw new ServletException(e);
	       }
	   }

	}


