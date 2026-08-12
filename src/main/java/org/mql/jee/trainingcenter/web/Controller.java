package org.mql.jee.trainingcenter.web;

import java.io.IOException;

import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Student;
import org.mql.jee.trainingcenter.models.Trainer;
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

    @Override
    public void init() throws ServletException {

        datasource = getInitParameter("datasource");

        prefix = getServletContext().getInitParameter("prefix");
        suffix = getServletContext().getInitParameter("suffix");

        studentAction = new StudentAction();
        trainerAction = new TrainerAction();
    }

    // =====================================================
    // GET
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        String view = "error";

        Model model = new Model();

        // =================================================
        // STUDENT
        // =================================================

        if (uri.endsWith("/students-list")) {

            view = studentAction.studentsList(model);

        } else if (uri.endsWith("/student-add-form")) {

            view = studentAction.studentAddForm(model);

        } else if (uri.endsWith("/student-edit")) {

            int id = getId(request);

            view = studentAction.studentEditForm(
                    id,
                    model
            );

        // =================================================
        // TRAINER
        // =================================================

        } else if (uri.endsWith("/trainers-list")) {

            view = trainerAction.trainersList(model);

        } else if (uri.endsWith("/trainer-add-form")) {

            view = trainerAction.trainerAddForm(model);

        } else if (uri.endsWith("/trainer-edit")) {

            int id = getId(request);

            view = trainerAction.trainerEditForm(
                    id,
                    model
            );
        }

        forward(request, response, view, model);
    }

    // =====================================================
    // POST
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        String view = "error";

        Model model = new Model();

        // =================================================
        // STUDENT
        // =================================================

        if (uri.endsWith("/student-add")) {

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

            view = studentAction.addStudent(
                    student,
                    model
            );

        } else if (uri.endsWith("/student-update")) {

            Student student = new Student();

            student.setId(getId(request));

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

            view = studentAction.updateStudent(
                    student,
                    model
            );

        } else if (uri.endsWith("/student-delete")) {

            int id = getId(request);

            view = studentAction.deleteStudent(
                    id,
                    model
            );

        // =================================================
        // TRAINER
        // =================================================

        } else if (uri.endsWith("/trainer-add")) {

            Trainer trainer = new Trainer();

            trainer.setFirstName(
                    request.getParameter("firstName")
            );

            trainer.setLastName(
                    request.getParameter("lastName")
            );

            trainer.setEmail(
                    request.getParameter("email")
            );

            trainer.setSpecialization(
                    request.getParameter("specialization")
            );

            view = trainerAction.addTrainer(
                    trainer,
                    model
            );

        } else if (uri.endsWith("/trainer-update")) {

            Trainer trainer = new Trainer();

            trainer.setId(getId(request));

            trainer.setFirstName(
                    request.getParameter("firstName")
            );

            trainer.setLastName(
                    request.getParameter("lastName")
            );

            trainer.setEmail(
                    request.getParameter("email")
            );

            trainer.setSpecialization(
                    request.getParameter("specialization")
            );

            view = trainerAction.updateTrainer(
                    trainer,
                    model
            );

        } else if (uri.endsWith("/trainer-delete")) {

            int id = getId(request);

            view = trainerAction.deleteTrainer(
                    id,
                    model
            );
        }

        forward(request, response, view, model);
    }

    // =====================================================
    // GET ID
    // =====================================================

    private int getId(HttpServletRequest request) {

        return Integer.parseInt(
                request.getParameter("id")
        );
    }

    // =====================================================
    // FORWARD
    // =====================================================

    private void forward(
            HttpServletRequest request,
            HttpServletResponse response,
            String view,
            Model model)
            throws ServletException, IOException {

        request.setAttribute("model", model);

        getServletContext()
                .getRequestDispatcher(
                        prefix + view + suffix
                )
                .forward(request, response);
    }
}