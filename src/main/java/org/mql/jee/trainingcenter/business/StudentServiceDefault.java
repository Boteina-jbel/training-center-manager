package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.exceptions.StudentException;
import org.mql.jee.trainingcenter.models.Student;

public class StudentServiceDefault implements StudentService {

    // Dependency Inversion
    private StudentDao studentDao;

    // Dependency Injection
    public StudentServiceDefault(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.selectAll();
    }

    @Override
    public Student getStudentById(int id) {

        if (id <= 0) {
            throw new StudentException("Invalid student ID.");
        }

        Student student = studentDao.selectById(id);

        if (student == null) {
            throw new StudentException("Student not found.");
        }

        return student;
    }

    @Override
    public void addStudent(Student student) {

        validateStudent(student);

        studentDao.insert(student);
    }

    @Override
    public void updateStudent(Student student) {

        validateStudent(student);

        if (student.getId() <= 0) {
            throw new StudentException("Invalid student ID.");
        }

        Student existingStudent =
                studentDao.selectById(student.getId());

        if (existingStudent == null) {
            throw new StudentException("Student not found.");
        }

        studentDao.update(student);
    }

    @Override
    public void deleteStudent(int id) {

        if (id <= 0) {
            throw new StudentException("Invalid student ID.");
        }

        Student student = studentDao.selectById(id);

        if (student == null) {
            throw new StudentException("Student not found.");
        }

        studentDao.delete(id);
    }

    // =========================
    // VALIDATION
    // =========================

    private void validateStudent(Student student) {

        if (student == null) {
            throw new StudentException("Student cannot be null.");
        }
        

        if (isEmpty(student.getFirstName())) {
            throw new StudentException("First name is required.");
        }

        if (isEmpty(student.getLastName())) {
            throw new StudentException("Last name is required.");
        }

        if (isEmpty(student.getEmail())) {
            throw new StudentException("Email is required.");
        }

        if (!isValidEmail(student.getEmail())) {
            throw new StudentException("Invalid email format.");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }
}
