package org.mql.jee.trainingcenter.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mql.jee.trainingcenter.business.TrainingService;
import org.mql.jee.trainingcenter.business.TrainingServiceDefault;
import org.mql.jee.trainingcenter.dao.TrainingDao;
import org.mql.jee.trainingcenter.exceptions.TrainingException;
import org.mql.jee.trainingcenter.models.Trainer;
import org.mql.jee.trainingcenter.models.Training;

@DisplayName("Tests du TrainingService")
class TrainingServiceTest {

    private TrainingService service;
    private TrainingDao trainingDao;
    private Training defaultTraining;
    private Trainer defaultTrainer;

    @BeforeEach
    void setup() {

        // Arrange
        trainingDao = new TrainingDaoMock();

        service = new TrainingServiceDefault(trainingDao);

        defaultTrainer = new Trainer(
                1,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        defaultTraining = new Training(
                1,
                "Java Programming",
                "Formation Java",
                40,
                defaultTrainer,
                new Timestamp(System.currentTimeMillis())
        );

        trainingDao.insert(defaultTraining);
    }


    // =====================================================
    // GET ALL TRAININGS
    // =====================================================

    @Test
    @DisplayName("Récupérer tous les trainings")
    void getAllTrainingsSuccess() {

        // Act
        List<Training> trainings = service.getAllTrainings();

        // Assert
        assertNotNull(trainings);
        assertEquals(1, trainings.size());
        assertEquals(
                defaultTraining.getId(),
                trainings.get(0).getId()
        );
    }


    // =====================================================
    // GET TRAINING BY ID
    // =====================================================

    @Test
    @DisplayName("Récupérer un training avec un ID existant")
    void getTrainingByIdSuccess() {

        // Act
        Training training =
                service.getTrainingById(defaultTraining.getId());

        // Assert
        assertNotNull(training);

        assertEquals(
                defaultTraining.getId(),
                training.getId()
        );

        assertEquals(
                defaultTraining.getTitle(),
                training.getTitle()
        );
    }


    @Test
    @DisplayName("Lever une exception si le training n'existe pas")
    void getTrainingByIdNotFound() {

        // Act & Assert
        assertThrows(
                TrainingException.class,
                () -> service.getTrainingById(999)
        );
    }


    @Test
    @DisplayName("Refuser un ID invalide")
    void getTrainingByIdInvalidId() {

        // Act & Assert
        assertThrows(
                TrainingException.class,
                () -> service.getTrainingById(0)
        );
    }


    // =====================================================
    // ADD TRAINING
    // =====================================================

    @Test
    @DisplayName("Ajouter un training avec des données valides")
    void addTrainingSuccess() {

        // Arrange
        Training training = new Training(
                2,
                "Spring Boot",
                "Formation Spring Boot",
                30,
                defaultTrainer,
                new Timestamp(System.currentTimeMillis())
        );

        // Act
        service.addTraining(training);

        // Assert
        Training result =
                service.getTrainingById(2);

        assertNotNull(result);

        assertEquals(
                "Spring Boot",
                result.getTitle()
        );

        assertEquals(
                30,
                result.getDuration()
        );
    }


    @Test
    @DisplayName("Refuser l'ajout d'un training null")
    void addTrainingNull() {

        // Act & Assert
        assertThrows(
                TrainingException.class,
                () -> service.addTraining(null)
        );
    }


    @Test
    @DisplayName("Refuser un training sans titre")
    void addTrainingWithoutTitle() {

        // Arrange
        Training training = new Training(
                2,
                "",
                "Formation Java",
                40,
                defaultTrainer,
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert
        TrainingException exception =
                assertThrows(
                    TrainingException.class,
                    () -> service.addTraining(training)
                );

        assertEquals(
                "Title is required.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un training sans description")
    void addTrainingWithoutDescription() {

        // Arrange
        Training training = new Training(
                2,
                "Java",
                "",
                40,
                defaultTrainer,
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert
        TrainingException exception =
                assertThrows(
                    TrainingException.class,
                    () -> service.addTraining(training)
                );

        assertEquals(
                "Description is required.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un training avec une durée invalide")
    void addTrainingWithInvalidDuration() {

        // Arrange
        Training training = new Training(
                2,
                "Java",
                "Formation Java",
                0,
                defaultTrainer,
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert
        TrainingException exception =
                assertThrows(
                    TrainingException.class,
                    () -> service.addTraining(training)
                );

        assertEquals(
                "Duration must be greater than 0.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un training sans trainer")
    void addTrainingWithoutTrainer() {

        // Arrange
        Training training = new Training(
                2,
                "Java",
                "Formation Java",
                40,
                null,
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert
        TrainingException exception =
                assertThrows(
                    TrainingException.class,
                    () -> service.addTraining(training)
                );

        assertEquals(
                "Trainer is required.",
                exception.getMessage()
        );
    }


    @Test
    @DisplayName("Refuser un training avec un trainer invalide")
    void addTrainingWithInvalidTrainerId() {

        // Arrange
        Trainer trainer = new Trainer(
                0,
                "Ahmed",
                "Alami",
                "ahmed@gmail.com",
                "Java",
                new Timestamp(System.currentTimeMillis())
        );

        Training training = new Training(
                2,
                "Java",
                "Formation Java",
                40,
                trainer,
                new Timestamp(System.currentTimeMillis())
        );

        // Act & Assert
        TrainingException exception =
                assertThrows(
                    TrainingException.class,
                    () -> service.addTraining(training)
                );

        assertEquals(
                "Invalid trainer ID.",
                exception.getMessage()
        );
    }


    // =====================================================
    // UPDATE TRAINING
    // =====================================================

    @Test
    @DisplayName("Modifier un training existant")
    void updateTrainingSuccess() {

        // Arrange
        Training training = new Training(
                1,
                "Java Updated",
                "Formation Java avancée",
                50,
                defaultTrainer,
                defaultTraining.getCreatedAt()
        );

        // Act
        service.updateTraining(training);

        // Assert
        Training result =
                service.getTrainingById(1);

        assertEquals(
                "Java Updated",
                result.getTitle()
        );

        assertEquals(
                50,
                result.getDuration()
        );
    }


    @Test
    @DisplayName("Refuser la modification d'un training inexistant")
    void updateTrainingNotFound() {

        // Arrange
        Training training = new Training(
                999,
                "Java",
                "Formation Java",
                40,
                defaultTrainer,
                defaultTraining.getCreatedAt()
        );

        // Act & Assert
        assertThrows(
                TrainingException.class,
                () -> service.updateTraining(training)
        );
    }


    @Test
    @DisplayName("Refuser la modification avec un ID invalide")
    void updateTrainingInvalidId() {

        // Arrange
        Training training = new Training(
                0,
                "Java",
                "Formation Java",
                40,
                defaultTrainer,
                defaultTraining.getCreatedAt()
        );

        // Act & Assert
        TrainingException exception =
                assertThrows(
                    TrainingException.class,
                    () -> service.updateTraining(training)
                );

        assertEquals(
                "Invalid training ID.",
                exception.getMessage()
        );
    }


    // =====================================================
    // DELETE TRAINING
    // =====================================================

    @Test
    @DisplayName("Supprimer un training existant")
    void deleteTrainingSuccess() {

        // Act
        service.deleteTraining(1);

        // Assert
        assertThrows(
                TrainingException.class,
                () -> service.getTrainingById(1)
        );
    }


    @Test
    @DisplayName("Refuser la suppression d'un training inexistant")
    void deleteTrainingNotFound() {

        // Act & Assert
        assertThrows(
                TrainingException.class,
                () -> service.deleteTraining(999)
        );
    }


    @Test
    @DisplayName("Refuser la suppression avec un ID invalide")
    void deleteTrainingInvalidId() {

        // Act & Assert
        assertThrows(
                TrainingException.class,
                () -> service.deleteTraining(0)
        );
    }
}