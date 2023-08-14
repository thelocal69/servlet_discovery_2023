package com.crmproject02.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crmproject02.dao.MysqlConfig;
import com.crmproject02.model.RoleModel;

@WebServlet(urlPatterns = {"/role-add", "/role"})
public class RoleController extends HttpServlet{
	
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "";
		String action = req.getServletPath();
		String issucess = "";
		if (action.equals("/role-add")) {
			req.setAttribute("issucess", issucess);
			view = "/view/role-add.jsp";
		}else if(action.equals("/role")){
			List<RoleModel> listRole = findAll();
			req.setAttribute("listRole", listRole);
			view = "/view/role-table.jsp";
		}
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String roleName = req.getParameter("roleName");
		String description = req.getParameter("description");
		String sql = "INSERT INTO role (name, description) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = MysqlConfig.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			statement.setString(1, roleName);
			statement.setString(2, description);
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
				}else if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("/view/role-add.jsp");
		rd.forward(req, resp);
	}
	
	List<RoleModel> findAll(){
		String sql = "SELECT * FROM role";
		List<RoleModel> result = new ArrayList<RoleModel>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			
			connection = MysqlConfig.getConnection();
			statement = connection.prepareStatement(sql);
			resultset = statement.executeQuery();
			while (resultset.next()) {
				RoleModel roleModel = new RoleModel();
				roleModel.setId(resultset.getInt("id"));
				roleModel.setName(resultset.getString("name"));
				roleModel.setDescription(resultset.getString("description"));
				result.add(roleModel);
			}
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}
	
}
