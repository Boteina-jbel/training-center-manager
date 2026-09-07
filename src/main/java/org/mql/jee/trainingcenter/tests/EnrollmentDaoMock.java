package org.mql.jee.trainingcenter.tests;

import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.dao.EnrollmentDao;
import org.mql.jee.trainingcenter.models.Enrollment;

public class EnrollmentDaoMock implements EnrollmentDao {

    private List<Enrollment> enrollments = new Vector<>();

    @Override
    public List<Enrollment> selectAll() {
        return new Vector<>(enrollments);
    }

    @Override
    public Enrollment selectById(int id) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getId() == id) {
                return enrollment;
            }
        }

        return null;
    }

    @Override
    public void insert(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public void update(Enrollment enrollment) {

        for (int i = 0; i < enrollments.size(); i++) {

            if (enrollments.get(i).getId() == enrollment.getId()) {

                enrollments.set(i, enrollment);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {

        enrollments.removeIf(
            enrollment -> enrollment.getId() == id
        );
    }
}