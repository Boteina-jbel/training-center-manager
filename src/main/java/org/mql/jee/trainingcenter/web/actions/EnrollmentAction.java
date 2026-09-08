package org.mql.jee.trainingcenter.web.actions;

import java.util.List;

import org.mql.jee.trainingcenter.business.EnrollmentService;
import org.mql.jee.trainingcenter.business.StudentService;
import org.mql.jee.trainingcenter.business.TrainingService;
import org.mql.jee.trainingcenter.context.ApplicationContext;
import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Enrollment;
import org.mql.jee.trainingcenter.models.Student;
import org.mql.jee.trainingcenter.models.Training;

public class EnrollmentAction {

    private EnrollmentService service;
    private StudentService studentService;
    private TrainingService trainingService;

    public EnrollmentAction() {
        super();

        service = ApplicationContext.getEnrollmentService();
        studentService = ApplicationContext.getStudentService();
        trainingService = ApplicationContext.getTrainingService();
    }

    // =====================================================
    // READ - List all enrollments
    // =====================================================

    public String enrollmentsList(Model model) {

        System.out.println(">> Action : enrollmentsList()");

        List<Enrollment> enrollments =
                service.getAllEnrollments();

        model.setModel("enrollments", enrollments);

        return "enrollments-list";
    }

    // =====================================================
    // CREATE - Show add form
    // =====================================================

    public String enrollmentAddForm(Model model) {

        System.out.println(">> Action : enrollmentAddForm()");

        List<Student> students =
                studentService.getAllStudents();

        List<Training> trainings =
                trainingService.getAllTrainings();

        model.setModel("students", students);
        model.setModel("trainings", trainings);

        return "enrollment-form";
    }

    // =====================================================
    // CREATE - Add enrollment
    // =====================================================

    public String addEnrollment(
            Enrollment enrollment,
            Model model) {

        System.out.println(">> Action : addEnrollment()");

        service.addEnrollment(enrollment);

        return enrollmentsList(model);
    }

    // =====================================================
    // UPDATE - Show edit form
    // =====================================================

    public String enrollmentEditForm(
            int id,
            Model model) {

        System.out.println(">> Action : enrollmentEditForm()");

        Enrollment enrollment =
                service.getEnrollmentById(id);

        List<Student> students =
                studentService.getAllStudents();

        List<Training> trainings =
                trainingService.getAllTrainings();

        model.setModel("enrollment", enrollment);
        model.setModel("students", students);
        model.setModel("trainings", trainings);

        return "enrollment-form";
    }

    // =====================================================
    // UPDATE - Update enrollment
    // =====================================================

    public String updateEnrollment(
            Enrollment enrollment,
            Model model) {

        System.out.println(">> Action : updateEnrollment()");

        service.updateEnrollment(enrollment);

        return enrollmentsList(model);
    }

    // =====================================================
    // DELETE - Delete enrollment
    // =====================================================

    public String deleteEnrollment(
            int id,
            Model model) {

        System.out.println(">> Action : deleteEnrollment()");

        service.deleteEnrollment(id);

        return enrollmentsList(model);
    }
}