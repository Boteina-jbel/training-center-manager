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

    // =====================================================
    // GET
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">> Controller.doGet()");

        String uri = request.getRequestURI();

        String view = "error";

        Model model = new Model();

        // =================================================
        // STUDENT
        // =================================================

        // READ - afficher la liste
        if (uri.endsWith("/students-list")) {

            view = studentAction.studentsList(model);
        }

        // CREATE - afficher le formulaire
        else if (uri.endsWith("/student-add-form")) {

            view = studentAction.studentAddForm(model);
        }

        // UPDATE - afficher le formulaire de modification
        else if (uri.endsWith("/student-edit")) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            view = studentAction.studentEditForm(id, model);
        }

        // =================================================
        // TRAINER
        // =================================================

        // READ - afficher la liste
        else if (uri.endsWith("/trainers-list")) {

            view = trainerAction.trainersList(model);
        }

        // CREATE - afficher le formulaire
        else if (uri.endsWith("/trainer-add-form")) {

            view = trainerAction.trainerAddForm(model);
        }

        // UPDATE - afficher le formulaire de modification
        else if (uri.endsWith("/trainer-edit")) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            view = trainerAction.trainerEditForm(id, model);
        }

        // =================================================
        // FORWARD
        // =================================================

        request.setAttribute("model", model);

        getServletContext()
                .getRequestDispatcher(
                        prefix + view + suffix
                )
                .forward(request, response);
    }

    // =====================================================
    // POST
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">> Controller.doPost()");

        String uri = request.getRequestURI();

        String view = "error";

        Model model = new Model();

        // =================================================
        // STUDENT
        // =================================================

        // CREATE - ajouter un étudiant
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
        }

        // UPDATE - modifier un étudiant
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

            view = studentAction.updateStudent(
                    student,
                    model
            );
        }

        // DELETE - supprimer un étudiant
        else if (uri.endsWith("/student-delete")) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            view = studentAction.deleteStudent(
                    id,
                    model
            );
        }

        // =================================================
        // TRAINER
        // =================================================

        // CREATE - ajouter un formateur
        else if (uri.endsWith("/trainer-add")) {

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
        }

        // UPDATE - modifier un formateur
        else if (uri.endsWith("/trainer-update")) {

            Trainer trainer = new Trainer();

            trainer.setId(
                    Integer.parseInt(
                            request.getParameter("id")
                    )
            );

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
        }

        // DELETE - supprimer un formateur
        else if (uri.endsWith("/trainer-delete")) {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            view = trainerAction.deleteTrainer(
                    id,
                    model
            );
        }

        // =================================================
        // FORWARD
        // =================================================

        request.setAttribute("model", model);

        getServletContext()
                .getRequestDispatcher(
                        prefix + view + suffix
                )
                .forward(request, response);
    }
}