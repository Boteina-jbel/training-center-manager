package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.dao.EnrollmentDao;
import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.dao.TrainingDao;
import org.mql.jee.trainingcenter.exceptions.EnrollmentException;
import org.mql.jee.trainingcenter.models.Enrollment;

public class EnrollmentServiceDefault implements EnrollmentService {

    private EnrollmentDao enrollmentDao;
    private StudentDao studentDao;
    private TrainingDao trainingDao;

    public EnrollmentServiceDefault(
            EnrollmentDao enrollmentDao,
            StudentDao studentDao,
            TrainingDao trainingDao) {

        this.enrollmentDao = enrollmentDao;
        this.studentDao = studentDao;
        this.trainingDao = trainingDao;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDao.selectAll();
    }

    @Override
    public Enrollment getEnrollmentById(int id) {

        if (id <= 0) {
            throw new EnrollmentException("Invalid enrollment ID.");
        }

        Enrollment enrollment = enrollmentDao.selectById(id);

        if (enrollment == null) {
            throw new EnrollmentException("Enrollment not found.");
        }

        return enrollment;
    }

    @Override
    public void addEnrollment(Enrollment enrollment) {

        validateEnrollment(enrollment);

        enrollmentDao.insert(enrollment);
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {

        validateEnrollment(enrollment);

        if (enrollment.getId() <= 0) {
            throw new EnrollmentException("Invalid enrollment ID.");
        }

        Enrollment existingEnrollment =
                enrollmentDao.selectById(enrollment.getId());

        if (existingEnrollment == null) {
            throw new EnrollmentException("Enrollment not found.");
        }

        enrollmentDao.update(enrollment);
    }

    @Override
    public void deleteEnrollment(int id) {

        if (id <= 0) {
            throw new EnrollmentException("Invalid enrollment ID.");
        }

        Enrollment enrollment = enrollmentDao.selectById(id);

        if (enrollment == null) {
            throw new EnrollmentException("Enrollment not found.");
        }

        enrollmentDao.delete(id);
    }

    private void validateEnrollment(Enrollment enrollment) {

        if (enrollment == null) {
            throw new EnrollmentException("Enrollment cannot be null.");
        }

        if (enrollment.getStudent() == null) {
            throw new EnrollmentException("Student is required.");
        }

        if (enrollment.getStudent().getId() <= 0) {
            throw new EnrollmentException("Invalid student ID.");
        }

        if (studentDao.selectById(enrollment.getStudent().getId()) == null) {
            throw new EnrollmentException("Student not found.");
        }

        if (enrollment.getTraining() == null) {
            throw new EnrollmentException("Training is required.");
        }

        if (enrollment.getTraining().getId() <= 0) {
            throw new EnrollmentException("Invalid training ID.");
        }

        if (trainingDao.selectById(enrollment.getTraining().getId()) == null) {
            throw new EnrollmentException("Training not found.");
        }

        if (enrollment.getEnrollmentDate() == null) {
            throw new EnrollmentException("Enrollment date is required.");
        }
    }
}