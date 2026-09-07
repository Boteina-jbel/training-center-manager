package org.mql.jee.trainingcenter.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mql.jee.trainingcenter.business.EnrollmentService;
import org.mql.jee.trainingcenter.business.EnrollmentServiceDefault;
import org.mql.jee.trainingcenter.dao.EnrollmentDao;
import org.mql.jee.trainingcenter.dao.StudentDao;
import org.mql.jee.trainingcenter.dao.TrainingDao;
import org.mql.jee.trainingcenter.exceptions.EnrollmentException;
import org.mql.jee.trainingcenter.models.Enrollment;
import org.mql.jee.trainingcenter.models.Student;
import org.mql.jee.trainingcenter.models.Trainer;
import org.mql.jee.trainingcenter.models.Training;


@DisplayName("Tests du EnrollmentService")
class EnrollmentServiceTest {

    private EnrollmentService service;

    private EnrollmentDao enrollmentDao;
    private StudentDao studentDao;
    private TrainingDao trainingDao;

    private Student defaultStudent;
    private Training defaultTraining;
    private Enrollment defaultEnrollment;


    @BeforeEach
    void setup() {

        // Arrange

        enrollmentDao = new EnrollmentDaoMock();
        studentDao = new StudentDaoMock();
        trainingDao = new TrainingDaoMock();

        service = new EnrollmentServiceDefault(
                enrollmentDao,
                studentDao,
                trainingDao
        );


        // Student

        defaultStudent = new Student(
                1,
                "Boteina",
                "JBEL",
                "boteina@gmail.com",
                "0612345678",
                null
        );

        studentDao.insert(defaultStudent);


        // Trainer

        Trainer trainer = new Trainer(
                1,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "Java",
                null
        );


        // Training

        defaultTraining = new Training(
                1,
                "Java Programming",
                "Java fundamentals",
                40,
                trainer,
                null
        );

        trainingDao.insert(defaultTraining);


        // Enrollment

        defaultEnrollment = new Enrollment(
                1,
                defaultStudent,
                defaultTraining,
                Date.valueOf("2026-09-07")
        );

        enrollmentDao.insert(defaultEnrollment);
    }


    // =====================================================
    // GET ALL ENROLLMENTS
    // =====================================================

    @Test
    @DisplayName("Récupérer toutes les inscriptions")
    void getAllEnrollmentsSuccess() {

        // Act

        List<Enrollment> enrollments =
                service.getAllEnrollments();


        // Assert

        assertNotNull(enrollments);
        assertEquals(1, enrollments.size());

        assertEquals(
                defaultEnrollment.getId(),
                enrollments.get(0).getId()
        );
    }


    // =====================================================
    // GET ENROLLMENT BY ID
    // =====================================================

    @Test
    @DisplayName("Récupérer une inscription avec un ID existant")
    void getEnrollmentByIdSuccess() {

        // Act

        Enrollment enrollment =
                service.getEnrollmentById(1);


        // Assert

        assertNotNull(enrollment);

        assertEquals(
                defaultEnrollment.getId(),
                enrollment.getId()
        );
    }


    @Test
    @DisplayName("Refuser une inscription inexistante")
    void getEnrollmentByIdNotFound() {

        // Act & Assert

        assertThrows(
                EnrollmentException.class,
                () -> service.getEnrollmentById(999)
        );
    }


    @Test
    @DisplayName("Refuser un ID invalide")
    void getEnrollmentByIdInvalidId() {

        // Act & Assert

        assertThrows(
                EnrollmentException.class,
                () -> service.getEnrollmentById(0)
        );
    }


    // =====================================================
    // ADD ENROLLMENT
    // =====================================================

    @Test
    @DisplayName("Ajouter une inscription valide")
    void addEnrollmentSuccess() {

        // Arrange

        Enrollment enrollment = new Enrollment(
                2,
                defaultStudent,
                defaultTraining,
                Date.valueOf("2026-09-07")
        );


        // Act

        service.addEnrollment(enrollment);


        // Assert

        Enrollment result =
                service.getEnrollmentById(2);

        assertNotNull(result);

        assertEquals(
                defaultStudent.getId(),
                result.getStudent().getId()
        );

        assertEquals(
                defaultTraining.getId(),
                result.getTraining().getId()
        );
    }


    @Test
    @DisplayName("Refuser une inscription null")
    void addEnrollmentNull() {

        // Act & Assert

        assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(null)
        );
    }


    @Test
    @DisplayName("Refuser une inscription sans étudiant")
    void addEnrollmentWithoutStudent() {

        // Arrange

        Enrollment enrollment = new Enrollment(
                2,
                null,
                defaultTraining,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(enrollment)
        );

        assertEquals(
                "Student is required.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un étudiant avec un ID invalide")
    void addEnrollmentWithInvalidStudentId() {

        // Arrange

        Student student = new Student();
        student.setId(0);

        Enrollment enrollment = new Enrollment(
                2,
                student,
                defaultTraining,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(enrollment)
        );

        assertEquals(
                "Invalid student ID.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un étudiant inexistant")
    void addEnrollmentWithUnknownStudent() {

        // Arrange

        Student student = new Student();
        student.setId(999);

        Enrollment enrollment = new Enrollment(
                2,
                student,
                defaultTraining,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(enrollment)
        );

        assertEquals(
                "Student not found.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser une inscription sans formation")
    void addEnrollmentWithoutTraining() {

        // Arrange

        Enrollment enrollment = new Enrollment(
                2,
                defaultStudent,
                null,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(enrollment)
        );

        assertEquals(
                "Training is required.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser une formation avec un ID invalide")
    void addEnrollmentWithInvalidTrainingId() {

        // Arrange

        Training training = new Training();
        training.setId(0);

        Enrollment enrollment = new Enrollment(
                2,
                defaultStudent,
                training,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(enrollment)
        );

        assertEquals(
                "Invalid training ID.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser une formation inexistante")
    void addEnrollmentWithUnknownTraining() {

        // Arrange

        Training training = new Training();
        training.setId(999);

        Enrollment enrollment = new Enrollment(
                2,
                defaultStudent,
                training,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(enrollment)
        );

        assertEquals(
                "Training not found.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser une inscription sans date")
    void addEnrollmentWithoutDate() {

        // Arrange

        Enrollment enrollment = new Enrollment(
                2,
                defaultStudent,
                defaultTraining,
                null
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.addEnrollment(enrollment)
        );

        assertEquals(
                "Enrollment date is required.",
                exception.getMessage()
        );
    }


    // =====================================================
    // UPDATE ENROLLMENT
    // =====================================================

    @Test
    @DisplayName("Modifier une inscription existante")
    void updateEnrollmentSuccess() {

        // Arrange

        Enrollment enrollment = new Enrollment(
                1,
                defaultStudent,
                defaultTraining,
                Date.valueOf("2026-09-08")
        );


        // Act

        service.updateEnrollment(enrollment);


        // Assert

        Enrollment result =
                service.getEnrollmentById(1);

        assertEquals(
                Date.valueOf("2026-09-08"),
                result.getEnrollmentDate()
        );
    }


    @Test
    @DisplayName("Refuser la modification d'une inscription inexistante")
    void updateEnrollmentNotFound() {

        // Arrange

        Enrollment enrollment = new Enrollment(
                999,
                defaultStudent,
                defaultTraining,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        assertThrows(
                EnrollmentException.class,
                () -> service.updateEnrollment(enrollment)
        );
    }


    @Test
    @DisplayName("Refuser la modification avec un ID invalide")
    void updateEnrollmentInvalidId() {

        // Arrange

        Enrollment enrollment = new Enrollment(
                0,
                defaultStudent,
                defaultTraining,
                Date.valueOf("2026-09-07")
        );


        // Act & Assert

        EnrollmentException exception = assertThrows(
                EnrollmentException.class,
                () -> service.updateEnrollment(enrollment)
        );

        assertEquals(
                "Invalid enrollment ID.",
                exception.getMessage()
        );
    }


    // =====================================================
    // DELETE ENROLLMENT
    // =====================================================

    @Test
    @DisplayName("Supprimer une inscription existante")
    void deleteEnrollmentSuccess() {

        // Act

        service.deleteEnrollment(1);


        // Assert

        assertThrows(
                EnrollmentException.class,
                () -> service.getEnrollmentById(1)
        );
    }


    @Test
    @DisplayName("Refuser la suppression d'une inscription inexistante")
    void deleteEnrollmentNotFound() {

        // Act & Assert

        assertThrows(
                EnrollmentException.class,
                () -> service.deleteEnrollment(999)
        );
    }


    @Test
    @DisplayName("Refuser la suppression avec un ID invalide")
    void deleteEnrollmentInvalidId() {

        // Act & Assert

        assertThrows(
                EnrollmentException.class,
                () -> service.deleteEnrollment(0)
        );
    }
}