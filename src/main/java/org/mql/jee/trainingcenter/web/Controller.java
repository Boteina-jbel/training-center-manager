package org.mql.jee.trainingcenter.web;

import java.io.IOException;

import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.web.actions.StudentAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String datasource;
	private String prefix;
	private String suffix;

	private StudentAction studentAction;

	public Controller() {
		System.out.println(">>> new Controller()");
	}

	@Override
	public void init() throws ServletException {

		datasource = getInitParameter("datasource");

		prefix = getServletContext().getInitParameter("prefix");
		suffix = getServletContext().getInitParameter("suffix");

		studentAction = new StudentAction();

		System.out.println("> datasource : " + datasource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(">> Controller.doGet()");
		String uri = request.getRequestURI();
		String view = "error";
		Model model = new Model();

		if (uri.endsWith("/students-list")) {
			view = studentAction.studentsList(model);
		}

		request.setAttribute("model", model);
		getServletContext().getRequestDispatcher(prefix + view + suffix).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}