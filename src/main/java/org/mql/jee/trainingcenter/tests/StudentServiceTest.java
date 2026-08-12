package org.mql.jee.trainingcenter.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mql.jee.trainingcenter.business.StudentService;
import org.mql.jee.trainingcenter.business.StudentServiceDefault;
import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.exceptions.StudentException;
import org.mql.jee.trainingcenter.models.Student;

@DisplayName("Tests du StudentService")
class StudentServiceTest {

    private StudentService service;
    private StudentDao studentDao;
    private Student defaultStudent;

    @BeforeEach
    void setup() {

        // Arrange
        studentDao = new StudentDaoMock();

        service = new StudentServiceDefault(studentDao);

        defaultStudent = new Student(
                1,
                "Boteina",
                "JBEL",
                "boteina@gmail.com",
                "0612345678",
                new Timestamp(System.currentTimeMillis())
        );

        studentDao.insert(defaultStudent);
    }

    // =====================================================
    // GET ALL STUDENTS
    // =====================================================

    @Test
    @DisplayName("Récupérer tous les étudiants")
    void getAllStudentsSuccess() {

        // Arrange
        // déjà effectué dans setup()

        // Act
        List<Student> students = service.getAllStudents();

        // Assert
        assertNotNull(students);
        assertEquals(1, students.size());
        assertEquals(defaultStudent.getId(), students.get(0).getId());
    }


    // =====================================================
    // GET STUDENT BY ID
    // =====================================================

    @Test
    @DisplayName("Récupérer un étudiant avec un ID existant")
    void getStudentByIdSuccess() {

        // Act
        Student student = service.getStudentById(defaultStudent.getId());

        // Assert
        assertNotNull(student);
        assertEquals(defaultStudent.getId(), student.getId());
        assertEquals(
                defaultStudent.getFirstName(),
                student.getFirstName()
        );
    }


    @Test
    @DisplayName("Lever une exception si l'étudiant n'existe pas")
    void getStudentByIdNotFound() {

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.getStudentById(999)
        );
    }


    @Test
    @DisplayName("Refuser un ID invalide")
    void getStudentByIdInvalidId() {

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.getStudentById(0)
        );
    }


    // =====================================================
    // ADD STUDENT
    // =====================================================

    @Test
    @DisplayName("Ajouter un étudiant avec des données valides")
    void addStudentSuccess() {

        // Arrange
        Student student = new Student(
                2,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "0622222222",
                new Timestamp(System.currentTimeMillis())
        );

        // Act
        service.addStudent(student);

        // Assert
        Student result = service.getStudentById(2);

        assertNotNull(result);
        assertEquals("Ahmed", result.getFirstName());
        assertEquals("Alami", result.getLastName());
    }


    @Test
    @DisplayName("Refuser l'ajout d'un étudiant null")
    void addStudentNull() {

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.addStudent(null)
        );
    }


    @Test
    @DisplayName("Refuser un étudiant avec des champs obligatoires vides")
    void addStudentWithEmptyFields() {

        // Arrange
        Student student = new Student(
                2,
                "",
                "",
                "test@gmail.com",
                "0612345678",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.addStudent(student)
        );
    }


    @Test
    @DisplayName("Refuser un email invalide")
    void addStudentWithInvalidEmail() {

        // Arrange
        Student student = new Student(
                2,
                "Ahmed",
                "Alami",
                "email-invalide",
                "0612345678",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.addStudent(student)
        );
    }


    // =====================================================
    // UPDATE STUDENT
    // =====================================================

    @Test
    @DisplayName("Modifier un étudiant existant")
    void updateStudentSuccess() {

        // Arrange
        Student student = new Student(
                1,
                "BoteinaUpdated",
                "JBEL",
                "updated@gmail.com",
                "0699999999",
                defaultStudent.getCreatedAt()
        );

        // Act
        service.updateStudent(student);

        // Assert
        Student result =
                service.getStudentById(1);

        assertEquals(
                "BoteinaUpdated",
                result.getFirstName()
        );

        assertEquals(
                "updated@gmail.com",
                result.getEmail()
        );
    }


    @Test
    @DisplayName("Refuser la modification d'un étudiant inexistant")
    void updateStudentNotFound() {

        // Arrange
        Student student = new Student(
                999,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "0611111111",
                defaultStudent.getCreatedAt()
        );

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.updateStudent(student)
        );
    }


    // =====================================================
    // DELETE STUDENT
    // =====================================================

    @Test
    @DisplayName("Supprimer un étudiant existant")
    void deleteStudentSuccess() {

        // Act
        service.deleteStudent(1);

        // Assert
        assertThrows(
                StudentException.class,
                () -> service.getStudentById(1)
        );
    }


    @Test
    @DisplayName("Refuser la suppression d'un étudiant inexistant")
    void deleteStudentNotFound() {

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.deleteStudent(999)
        );
    }


    @Test
    @DisplayName("Refuser la suppression avec un ID invalide")
    void deleteStudentInvalidId() {

        // Act & Assert
        assertThrows(
                StudentException.class,
                () -> service.deleteStudent(0)
        );
    }
}