package com.codegnan.studentapp.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codegnan.studentapp.model.Student;
import com.codegnan.studentapp.util.DatabaseUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
	       List<Student> students = new ArrayList<>();
	       try (Connection connection = DatabaseUtil.getConnection()) {
	           String sql = "SELECT * FROM students";
	           PreparedStatement statement = connection.prepareStatement(sql);
	           ResultSet resultSet = statement.executeQuery();
	           while (resultSet.next()) {
	               int id = resultSet.getInt("id");
	               String name = resultSet.getString("name");
	               int age = resultSet.getInt("age");
	               String course = resultSet.getString("course");
	               Student student = new Student(id, name, age, course);
	               students.add(student);
	           }
	       } catch (SQLException e) {
	           throw new ServletException(e);
	       }
	       request.setAttribute("students", students);
	       request.getRequestDispatcher("students.jsp").forward(request, response);
	   }


}
