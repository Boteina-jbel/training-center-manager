package org.mql.jee.trainingcenter.business;

import java.util.List;

import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.models.Student;

public class StudentServiceDefault implements StudentService{
	
	// Dependency Inversion
    private StudentDao studentDao;

    // Dependency Injection
	public StudentServiceDefault(StudentDao studentDao){
		this.studentDao = studentDao;
	}

    @Override
    public List<Student> getAllStudents() {
        return studentDao.selectAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.selectById(id);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.update(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentDao.delete(id);
    }
}
