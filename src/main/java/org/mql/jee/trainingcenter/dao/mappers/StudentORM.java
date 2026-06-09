package org.mql.jee.trainingcenter.dao.mappers;

import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.models.Student;

public class StudentORM {

    public static Student getStudent(String[] row) {

        Student s = new Student();

        s.setId(Integer.parseInt(row[0]));
        s.setFirstName(row[1]);
        s.setLastName(row[2]);
        s.setEmail(row[3]);
        s.setPhone(row[4]);

        return s;
    }

    public static List<Student> getStudentsList(String[][] data) {

        List<Student> students = new Vector<>();

        for (String[] row : data) {
            students.add(getStudent(row));
        }

        return students;
    }
}