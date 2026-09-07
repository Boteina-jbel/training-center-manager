package org.mql.jee.trainingcenter.dao;

import java.util.List;

import org.mql.jee.jdbc.Database;
import org.mql.jee.trainingcenter.dao.mappers.EnrollmentORM;
import org.mql.jee.trainingcenter.models.Enrollment;

public class EnrollmentDaoJdbc implements EnrollmentDao {

    private String tableName = "enrollments";
    private Database db;

    public EnrollmentDaoJdbc(Database db) {
        this.db = db;
    }

    @Override
    public List<Enrollment> selectAll() {

        String query =
            "SELECT e.id, " +
            "s.id, s.first_name, s.last_name, " +
            "t.id, t.title, " +
            "e.enrollment_date " +
            "FROM enrollments e " +
            "JOIN students s ON e.student_id = s.id " +
            "JOIN trainings t ON e.training_id = t.id";

        String[][] data = db.executeQuery(query);

        return EnrollmentORM.getEnrollmentsList(data);
    }

    @Override
    public Enrollment selectById(int id) {

        String query =
            "SELECT e.id, " +
            "s.id, s.first_name, s.last_name, " +
            "t.id, t.title, " +
            "e.enrollment_date " +
            "FROM enrollments e " +
            "JOIN students s ON e.student_id = s.id " +
            "JOIN trainings t ON e.training_id = t.id " +
            "WHERE e.id = " + id;

        String[][] data = db.executeQuery(query);

        if (data == null || data.length == 0) {
            return null;
        }

        return EnrollmentORM.getEnrollment(data[0]);
    }

    @Override
    public void insert(Enrollment enrollment) {

        db.executeUpdate(
            "INSERT INTO enrollments "
            + "(student_id, training_id, enrollment_date) VALUES("
            + enrollment.getStudent().getId() + ","
            + enrollment.getTraining().getId() + ",'"
            + enrollment.getEnrollmentDate()
            + "')"
        );
    }

    @Override
    public void update(Enrollment enrollment) {

        db.executeUpdate(
            "UPDATE enrollments SET "
            + "student_id=" + enrollment.getStudent().getId() + ", "
            + "training_id=" + enrollment.getTraining().getId() + ", "
            + "enrollment_date='" + enrollment.getEnrollmentDate() + "' "
            + "WHERE id=" + enrollment.getId()
        );
    }

    @Override
    public void delete(int id) {

        db.executeUpdate(
            "DELETE FROM enrollments WHERE id=" + id
        );
    }
}