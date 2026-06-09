package org.mql.jee.trainingcenter.dao;

import java.util.List;
import org.mql.jee.trainingcenter.models.Student;

public interface StudentDao {

    List<Student> selectAll();

    Student selectById(int id);

    void insert(Student student);

    void update(Student student);

    void delete(int id);
}