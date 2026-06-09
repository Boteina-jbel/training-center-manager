package org.mql.jee.trainingcenter.dao;

import java.util.List;

import org.mql.jee.jdbc.Database;
import org.mql.jee.trainingcenter.dao.mappers.StudentORM;
import org.mql.jee.trainingcenter.models.Student;

public class StudentDaoJdbc implements StudentDao {

    private String tableName = "students";
    private Database db;

    public StudentDaoJdbc(Database db) {
        this.db = db;
    }

    @Override
    public List<Student> selectAll() {
        String data[][] = db.select(tableName);
        return StudentORM.getStudentsList(data);
    }

    @Override
    public Student selectById(int id) {
        String data[][] = db.selectById(tableName, "id", id);
        return StudentORM.getStudent(data[0]);
    }

    @Override
    public void insert(Student s) {
        db.executeUpdate(
            "INSERT INTO students(first_name,last_name,email,phone) VALUES('"
            + s.getFirstName() + "','"
            + s.getLastName() + "','"
            + s.getEmail() + "','"
            + s.getPhone() + "')"
        );
    }

    @Override
    public void update(Student s) {
        db.executeUpdate(
            "UPDATE students SET "
            + "first_name='" + s.getFirstName() + "', "
            + "last_name='" + s.getLastName() + "', "
            + "email='" + s.getEmail() + "', "
            + "phone='" + s.getPhone() + "' "
            + "WHERE id=" + s.getId()
        );
    }

    @Override
    public void delete(int id) {
        db.executeUpdate(
            "DELETE FROM students WHERE id=" + id
        );
    }
}