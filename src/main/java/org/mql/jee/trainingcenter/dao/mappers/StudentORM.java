package org.mql.jee.trainingcenter.dao.mappers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.models.Student;

public class StudentORM {

    public static Student getStudent(String... row) {

        Student s = new Student();

        s.setId(getInt(row[0]));
        s.setFirstName(row[1]);
        s.setLastName(row[2]);
        s.setEmail(row[3]);
        s.setPhone(row[4]);

        if(row.length > 5 && row[5] != null) {
            s.setCreatedAt(Timestamp.valueOf(row[5]));
        }

        return s;
    }

    public static List<Student> getStudentsList(String[][] data) {

        List<Student> students = new Vector<Student>();

        for(String[] row : data) {
            students.add(getStudent(row));
        }

        return students;
    }

    public static int getInt(String data) {
        try {
            return Integer.parseInt(data);
        } catch (Exception e) {
            System.out.println("Mapping Error : " + e.getMessage());
            return -1;
        }
    }
}