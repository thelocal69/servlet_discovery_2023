package com.cybersoft.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybersoft.controller.web.model.StudentModel;

@WebServlet(urlPatterns = {"/demojsp"})
public class DemoController extends HttpServlet{
	
	private List<StudentModel> listStudents;
	
	public DemoController() {
		listStudents = new ArrayList<StudentModel>();
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/demo2/home.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		StudentModel studentModel = new StudentModel();
		studentModel.setName(req.getParameter("name"));	
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		double price = Double.parseDouble(req.getParameter("price"));
		studentModel.setQuantity(quantity);
		studentModel.setPrice(price);
		listStudents.add(studentModel);
		req.setAttribute("listStudent", listStudents);
		RequestDispatcher rd = req.getRequestDispatcher("/views/demo2/home.jsp");
		rd.forward(req, resp);
	}
	
}
