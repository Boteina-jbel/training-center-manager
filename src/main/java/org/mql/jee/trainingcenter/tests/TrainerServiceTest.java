package org.mql.jee.trainingcenter.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mql.jee.trainingcenter.business.TrainerService;
import org.mql.jee.trainingcenter.business.TrainerServiceDefault;
import org.mql.jee.trainingcenter.dao.TrainerDao;
import org.mql.jee.trainingcenter.exceptions.TrainerException;
import org.mql.jee.trainingcenter.models.Trainer;

@DisplayName("Tests du TrainerService")
class TrainerServiceTest {

    private TrainerService service;
    private TrainerDao trainerDao;
    private Trainer defaultTrainer;


    @BeforeEach
    void setup() {

        // Arrange

        trainerDao = new TrainerDaoMock();

        service = new TrainerServiceDefault(trainerDao);

        defaultTrainer = new Trainer(
                1,
                "John",
                "Smith",
                "john@gmail.com",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        trainerDao.insert(defaultTrainer);
    }


    // =====================================================
    // GET ALL TRAINERS
    // =====================================================

    @Test
    @DisplayName("Récupérer tous les formateurs")
    void getAllTrainersSuccess() {

        // Act
        List<Trainer> trainers =
                service.getAllTrainers();

        // Assert
        assertNotNull(trainers);

        assertEquals(
                1,
                trainers.size()
        );

        assertEquals(
                defaultTrainer.getId(),
                trainers.get(0).getId()
        );
    }


    // =====================================================
    // GET TRAINER BY ID
    // =====================================================

    @Test
    @DisplayName("Récupérer un formateur avec un ID existant")
    void getTrainerByIdSuccess() {

        // Act
        Trainer trainer =
                service.getTrainerById(
                        defaultTrainer.getId()
                );

        // Assert
        assertNotNull(trainer);

        assertEquals(
                defaultTrainer.getId(),
                trainer.getId()
        );

        assertEquals(
                defaultTrainer.getFirstName(),
                trainer.getFirstName()
        );

        assertEquals(
                defaultTrainer.getLastName(),
                trainer.getLastName()
        );
    }


    @Test
    @DisplayName("Lever une exception si le formateur n'existe pas")
    void getTrainerByIdNotFound() {

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.getTrainerById(999)
        );
    }


    @Test
    @DisplayName("Refuser un ID invalide")
    void getTrainerByIdInvalidId() {

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.getTrainerById(0)
        );
    }


    // =====================================================
    // ADD TRAINER
    // =====================================================

    @Test
    @DisplayName("Ajouter un formateur avec des données valides")
    void addTrainerSuccess() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "Spring Boot",
                new Timestamp(System.currentTimeMillis())
        );

        // Act

        service.addTrainer(trainer);

        // Assert

        Trainer result =
                service.getTrainerById(2);

        assertNotNull(result);

        assertEquals(
                "Ahmed",
                result.getFirstName()
        );

        assertEquals(
                "Alami",
                result.getLastName()
        );

        assertEquals(
                "Spring Boot",
                result.getSpecialization()
        );
    }


    @Test
    @DisplayName("Refuser l'ajout d'un formateur null")
    void addTrainerNull() {

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.addTrainer(null)
        );
    }


    @Test
    @DisplayName("Refuser un formateur avec des champs obligatoires vides")
    void addTrainerWithEmptyFields() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "",
                "",
                "test@gmail.com",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.addTrainer(trainer)
        );
    }


    @Test
    @DisplayName("Refuser un email invalide")
    void addTrainerWithInvalidEmail() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "Ahmed",
                "Alami",
                "email-invalide",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.addTrainer(trainer)
        );
    }


    // =====================================================
    // UPDATE TRAINER
    // =====================================================

    @Test
    @DisplayName("Modifier un formateur existant")
    void updateTrainerSuccess() {

        // Arrange

        Trainer trainer = new Trainer(
                1,
                "JohnUpdated",
                "Smith",
                "updated@gmail.com",
                "Spring",
                defaultTrainer.getCreatedAt()
        );

        // Act

        service.updateTrainer(trainer);

        // Assert

        Trainer result =
                service.getTrainerById(1);

        assertEquals(
                "JohnUpdated",
                result.getFirstName()
        );

        assertEquals(
                "updated@gmail.com",
                result.getEmail()
        );

        assertEquals(
                "Spring",
                result.getSpecialization()
        );
    }


    @Test
    @DisplayName("Refuser la modification d'un formateur inexistant")
    void updateTrainerNotFound() {

        // Arrange

        Trainer trainer = new Trainer(
                999,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "Java",
                defaultTrainer.getCreatedAt()
        );

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.updateTrainer(trainer)
        );
    }


    @Test
    @DisplayName("Refuser la modification avec un ID invalide")
    void updateTrainerInvalidId() {

        // Arrange

        Trainer trainer = new Trainer(
                0,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "Java",
                defaultTrainer.getCreatedAt()
        );

        // Act & Assert

        TrainerException exception =
                assertThrows(
                        TrainerException.class,
                        () -> service.updateTrainer(trainer)
                );

        assertEquals(
                "Invalid trainer ID.",
                exception.getMessage()
        );
    }


    // =====================================================
    // DELETE TRAINER
    // =====================================================

    @Test
    @DisplayName("Supprimer un formateur existant")
    void deleteTrainerSuccess() {

        // Act

        service.deleteTrainer(1);

        // Assert

        assertThrows(
                TrainerException.class,
                () -> service.getTrainerById(1)
        );
    }


    @Test
    @DisplayName("Refuser la suppression d'un formateur inexistant")
    void deleteTrainerNotFound() {

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.deleteTrainer(999)
        );
    }


    @Test
    @DisplayName("Refuser la suppression avec un ID invalide")
    void deleteTrainerInvalidId() {

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.deleteTrainer(0)
        );
    }


    // =====================================================
    // VALIDATION
    // =====================================================

    @Test
    @DisplayName("Refuser un formateur sans nom")
    void addTrainerWithoutLastName() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "Ahmed",
                "",
                "ahmed@gmail.com",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert

        TrainerException exception =
                assertThrows(
                        TrainerException.class,
                        () -> service.addTrainer(trainer)
                );

        assertEquals(
                "Last name is required.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un formateur avec un nom null")
    void addTrainerWithNullLastName() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "Ahmed",
                null,
                "ahmed@gmail.com",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.addTrainer(trainer)
        );
    }


    @Test
    @DisplayName("Refuser un nom composé uniquement d'espaces")
    void addTrainerWithBlankLastName() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "Ahmed",
                "   ",
                "ahmed@gmail.com",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.addTrainer(trainer)
        );
    }


    @Test
    @DisplayName("Refuser un formateur sans email")
    void addTrainerWithoutEmail() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "Ahmed",
                "Alami",
                "",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert

        TrainerException exception =
                assertThrows(
                        TrainerException.class,
                        () -> service.addTrainer(trainer)
                );

        assertEquals(
                "Email is required.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un formateur avec un email null")
    void addTrainerWithNullEmail() {

        // Arrange

        Trainer trainer = new Trainer(
                2,
                "Ahmed",
                "Alami",
                null,
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert

        assertThrows(
                TrainerException.class,
                () -> service.addTrainer(trainer)
        );
    }
}