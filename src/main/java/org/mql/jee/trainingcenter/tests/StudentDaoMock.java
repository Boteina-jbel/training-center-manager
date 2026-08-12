package org.mql.jee.trainingcenter.tests;

import java.util.List;
import java.util.Vector;

import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.models.Student;

public class StudentDaoMock implements StudentDao {

    private List<Student> students = new Vector<>();

    @Override
    public List<Student> selectAll() {
        return new Vector<>(students);
    }

    @Override
    public Student selectById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    @Override
    public void insert(Student student) {
        students.add(student);
    }

    @Override
    public void update(Student student) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == student.getId()) {

                students.set(i, student);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {

        students.removeIf(
            student -> student.getId() == id
        );
    }
}