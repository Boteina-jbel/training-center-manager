package org.mql.jee.trainingcenter.dao;

import java.util.List;

import org.mql.jee.trainingcenter.models.Enrollment;

public interface EnrollmentDao {

    List<Enrollment> selectAll();

    Enrollment selectById(int id);

    void insert(Enrollment enrollment);

    void update(Enrollment enrollment);

    void delete(int id);
}