package org.mql.jee.trainingcenter.web.actions;

import java.util.List;

import org.mql.jee.trainingcenter.business.StudentService;
import org.mql.jee.trainingcenter.context.ApplicationContext;
import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Student;

public class StudentAction {

    private StudentService service;

    public StudentAction() {
        super();
        service = ApplicationContext.getStudentService();
    }

    // READ - List all students
    public String studentsList(Model model) {

        System.out.println(">> Action : studentsList()");

        List<Student> students = service.getAllStudents();

        model.setModel("students", students);

        return "students-list";
    }

    // CREATE - Show add form
    public String studentAddForm(Model model) {

        System.out.println(">> Action : studentAddForm()");

        return "student-form";
    }

    // CREATE - Add student
    public String addStudent(Student student, Model model) {

        System.out.println(">> Action : addStudent()");

        service.addStudent(student);

        return studentsList(model);
    }

    // UPDATE - Show edit form
    public String studentEditForm(int id, Model model) {

        System.out.println(">> Action : studentEditForm()");

        Student student = service.getStudentById(id);

        model.setModel("student", student);

        return "student-form";
    }

    // UPDATE - Update student
    public String updateStudent(Student student, Model model) {

        System.out.println(">> Action : updateStudent()");

        service.updateStudent(student);

        return studentsList(model);
    }

    // DELETE - Delete student
    public String deleteStudent(int id, Model model) {

        System.out.println(">> Action : deleteStudent()");

        service.deleteStudent(id);

        return studentsList(model);
    }
}
