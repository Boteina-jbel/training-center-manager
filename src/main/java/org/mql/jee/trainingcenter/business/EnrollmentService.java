package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.models.Enrollment;

public interface EnrollmentService {

    List<Enrollment> getAllEnrollments();

    Enrollment getEnrollmentById(int id);

    void addEnrollment(Enrollment enrollment);

    void updateEnrollment(Enrollment enrollment);

    void deleteEnrollment(int id);
}