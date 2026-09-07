package org.mql.jee.trainingcenter.dao.mappers;

import java.sql.Date;
import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.models.Enrollment;
import org.mql.jee.trainingcenter.models.Student;
import org.mql.jee.trainingcenter.models.Training;

public class EnrollmentORM {

    public static Enrollment getEnrollment(String... row) {

        Enrollment enrollment = new Enrollment();

        enrollment.setId(getInt(row[0]));

        // Student
        if (row.length > 3 && row[1] != null) {

            Student student = new Student();

            student.setId(getInt(row[1]));
            student.setFirstName(row[2]);
            student.setLastName(row[3]);

            enrollment.setStudent(student);
        }

        // Training
        if (row.length > 5 && row[4] != null) {

            Training training = new Training();

            training.setId(getInt(row[4]));
            training.setTitle(row[5]);

            enrollment.setTraining(training);
        }

        // Enrollment date
        if (row.length > 6 && row[6] != null) {
            enrollment.setEnrollmentDate(Date.valueOf(row[6]));
        }

        return enrollment;
    }

    public static List<Enrollment> getEnrollmentsList(String[][] data) {

        List<Enrollment> enrollments =
                new Vector<Enrollment>();

        for (String[] row : data) {
            enrollments.add(getEnrollment(row));
        }

        return enrollments;
    }

    public static int getInt(String data) {

        try {
            return Integer.parseInt(data);

        } catch (Exception e) {

            System.out.println(
                "Mapping Error : " + e.getMessage()
            );

            return -1;
        }
    }
}