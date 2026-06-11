package org.mql.jee.trainingcenter.web.actions;

import java.util.List;

import org.mql.jee.trainingcenter.business.StudentService;
import org.mql.jee.trainingcenter.context.ApplicationContext;
import org.mql.jee.trainingcenter.context.Model;
import org.mql.jee.trainingcenter.models.Student;

public class StudentAction {

    private StudentService service;

    public StudentAction() {
        service = ApplicationContext.getStudentService();
    }

    public String studentsList(Model model) {

        System.out.println(">> Action : studentsList()");

        List<Student> students = service.getAllStudents();

        model.setModel("students", students);

        return "students-list";
    }
}