package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.models.Student;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(int id);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int id);
}

