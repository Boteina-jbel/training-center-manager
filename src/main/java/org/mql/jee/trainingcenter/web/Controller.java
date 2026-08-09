package org.mql.jee.trainingcenter.web;

import java.io.IOException;

import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Student;
import org.mql.jee.trainingcenter.web.actions.StudentAction;
import org.mql.jee.trainingcenter.web.actions.TrainerAction;

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
    private TrainerAction trainerAction;

    public Controller() {
        System.out.println(">>> new Controller()");
    }

    @Override
    public void init() throws ServletException {

        datasource = getInitParameter("datasource");

        prefix = getServletContext().getInitParameter("prefix");
        suffix = getServletContext().getInitParameter("suffix");

        studentAction = new StudentAction();
        trainerAction = new TrainerAction();

        System.out.println("> datasource : " + datasource);
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">> Controller.doGet()");

        String uri = request.getRequestURI();

        String view = "error";

        Model model = new Model();

        // =========================
        // STUDENTS
        // =========================

        // READ - list
        if (uri.endsWith("/students-list")) {

            view = studentAction.studentsList(model);
        }

        // CREATE - show form
        else if (uri.endsWith("/student-add-form")) {

            view = studentAction.studentAddForm(model);
        }

        // CREATE - add student
        else if (uri.endsWith("/student-add")) {

            Student student = new Student();

            student.setFirstName(
                request.getParameter("firstName")
            );

            student.setLastName(
                request.getParameter("lastName")
            );

            student.setEmail(
                request.getParameter("email")
            );

            student.setPhone(
                request.getParameter("phone")
            );

            view = studentAction.addStudent(student, model);
        }

        // UPDATE - show edit form
        else if (uri.endsWith("/student-edit")) {

            int id = Integer.parseInt(
                request.getParameter("id")
            );

            view = studentAction.studentEditForm(id, model);
        }

        // UPDATE - update student
        else if (uri.endsWith("/student-update")) {

            Student student = new Student();

            student.setId(
                Integer.parseInt(
                    request.getParameter("id")
                )
            );

            student.setFirstName(
                request.getParameter("firstName")
            );

            student.setLastName(
                request.getParameter("lastName")
            );

            student.setEmail(
                request.getParameter("email")
            );

            student.setPhone(
                request.getParameter("phone")
            );

            view = studentAction.updateStudent(student, model);
        }

        // DELETE
        else if (uri.endsWith("/student-delete")) {

            int id = Integer.parseInt(
                request.getParameter("id")
            );

            view = studentAction.deleteStudent(id, model);
        }

        // =========================
        // TRAINERS
        // =========================

        else if (uri.endsWith("/trainers-list")) {

            view = trainerAction.trainersList(model);
        }

        // =========================
        // FORWARD TO JSP
        // =========================

        request.setAttribute("model", model);

        getServletContext()
                .getRequestDispatcher(
                    prefix + view + suffix
                )
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}