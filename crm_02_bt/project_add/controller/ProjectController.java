package com.crmproject02.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crmproject02.dao.MysqlConfig;

@WebServlet(urlPatterns = {"/project-add"})
public class ProjectController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/view/project-add.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String taskName = req.getParameter("taskName");
		String taskStart = req.getParameter("taskStart");
		String taskEnd = req.getParameter("taskEnd");
		java.util.Date begin = getDate(taskStart);
		java.util.Date end = getDate(taskEnd);
		java.sql.Date startedDate = new java.sql.Date(begin.getTime());
		java.sql.Date endedDate = new java.sql.Date(end.getTime());
		String sql = "INSERT INTO project (name, starteddate, endeddate) VALUES (?, ?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = MysqlConfig.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1, taskName);
			statement.setDate(2, startedDate);
			statement.setDate(3, endedDate);
			int count = statement.executeUpdate();
			connection.commit();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			if (count > 0) {
				System.out.println("success");
			}else {
				System.out.println("failed");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("/view/project-add.jsp");
		rd.forward(req, resp);
	}
	
	private java.util.Date getDate(String parameter) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		java.util.Date param = null;
		try {
			param = format.parse(parameter);
			return param;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
