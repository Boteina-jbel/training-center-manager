# Training Center Manager

Application web de gestion d'un centre de formation développée avec **Jakarta EE**, **JDBC**, **MySQL** et **JSP**.

L'application permet de gérer les étudiants et les formateurs d'un centre de formation à travers une architecture organisée en plusieurs couches : présentation, métier, accès aux données et modèles.

---

## 📌 Fonctionnalités

### 👨‍🎓 Gestion des étudiants

L'application permet de :

- Afficher la liste des étudiants
- Ajouter un étudiant
- Modifier un étudiant
- Supprimer un étudiant
- Consulter un étudiant par son identifiant
- Valider les informations saisies

Informations d'un étudiant :

- ID
- Prénom
- Nom
- Email
- Téléphone
- Date de création

### 👨‍🏫 Gestion des formateurs

L'application permet de :

- Afficher la liste des formateurs
- Ajouter un formateur
- Modifier un formateur
- Supprimer un formateur
- Consulter un formateur par son identifiant
- Valider les informations saisies

Informations d'un formateur :

- ID
- Prénom
- Nom
- Email
- Spécialisation
- Date de création

---

## 🛠️ Technologies utilisées

- Java
- Jakarta EE
- JSP
- Servlets
- JDBC
- MySQL
- Apache Tomcat
- Maven
- JUnit 5
- HTML
- CSS
- Git / GitHub

---

# 🏗️ Architecture du projet

Le projet suit une architecture en couches afin de séparer les différentes responsabilités.

```
org.mql.jee.trainingcenter
│
├── business
│   ├── StudentService.java
│   ├── StudentServiceDefault.java
│   ├── TrainerService.java
│   └── TrainerServiceDefault.java
│
├── context
│   ├── ApplicationContext.java
│   └── Model.java
│
├── dao
│   ├── StudentDao.java
│   ├── StudentDaoJdbc.java
│   ├── TrainerDao.java
│   ├── TrainerDaoJdbc.java
│   │
│   └── mappers
│       ├── StudentORM.java
│       └── TrainerORM.java
│
├── exceptions
│   ├── StudentException.java
│   └── TrainerException.java
│
├── models
│   ├── Student.java
│   └── Trainer.java
│
├── tests
│   ├── StudentDaoMock.java
│   ├── StudentServiceTest.java
│   ├── TrainerDaoMock.java
│   └── TrainerServiceTest.java
│
└── web
    └── actions
        ├── StudentAction.java
        └── TrainerAction.java
```

---

# 🧩 Architecture en couches

Le projet utilise plusieurs couches.

```
                 ┌─────────────────────┐
                 │        JSP          │
                 │   Presentation      │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │       Action        │
                 │    Web / Controller │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │      Service        │
                 │   Business Layer    │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │        DAO          │
                 │    Data Access      │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │       JDBC          │
                 │      Database       │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │       MySQL         │
                 └─────────────────────┘
```

---

# 📂 Description des couches

## 1. Model

Le package `models` contient les objets métier de l'application.

Exemples :

```
Student
Trainer
```

Ces classes représentent les données manipulées par l'application.

---

## 2. DAO

Le package `dao` contient les interfaces et les implémentations permettant d'accéder à la base de données.

Exemple :

```java
public interface TrainerDao {

    List<Trainer> selectAll();

    Trainer selectById(int id);

    void insert(Trainer trainer);

    void update(Trainer trainer);

    void delete(int id);
}
```

L'implémentation JDBC est :

```
TrainerDaoJdbc
```

Elle utilise la classe `Database` pour communiquer avec MySQL.

---

## 3. ORM Mapper

Les classes `StudentORM` et `TrainerORM` permettent de transformer les résultats SQL en objets Java.

Exemple :

```
Résultat SQL
      ↓
   ORM Mapper
      ↓
Student / Trainer
```

Cela permet de séparer la récupération des données SQL de leur transformation en objets métier.

---

## 4. Business / Service

Le package `business` contient la logique métier.

Exemple :

```
TrainerService
TrainerServiceDefault
```

Le service :

- valide les données
- vérifie les identifiants
- vérifie l'existence des objets
- appelle le DAO
- applique les règles métier

Exemple :

```java
public void deleteTrainer(int id) {

    if (id <= 0) {
        throw new TrainerException("Invalid trainer ID.");
    }

    Trainer trainer = trainerDao.selectById(id);

    if (trainer == null) {
        throw new TrainerException("Trainer not found.");
    }

    trainerDao.delete(id);
}
```

---

# 🔌 Dependency Injection

Le projet utilise l'injection de dépendances par constructeur.

Exemple :

```java
public TrainerServiceDefault(TrainerDao trainerDao) {
    this.trainerDao = trainerDao;
}
```

Cela permet au service de dépendre de l'interface :

```
TrainerServiceDefault
        ↓
    TrainerDao
        ↓
 TrainerDaoJdbc
```

Grâce à cela, il est possible de remplacer l'implémentation réelle par une implémentation Mock lors des tests.

---

# 🧪 Tests unitaires

Le projet utilise **JUnit 5** pour tester la couche métier.

Les tests utilisent un DAO Mock :

```
TrainerServiceTest
        ↓
TrainerServiceDefault
        ↓
TrainerDaoMock
```

La base de données réelle n'est donc pas nécessaire pour les tests unitaires du service.

---

## Tests réalisés

Les tests couvrent notamment :

### Lecture

- Récupération de tous les formateurs
- Récupération d'un formateur par ID
- ID inexistant
- ID invalide

### Création

- Ajout d'un formateur valide
- Formateur `null`
- Champs obligatoires vides
- Email invalide
- Nom null
- Email null
- Spécialisation vide

### Modification

- Modification d'un formateur existant
- Modification d'un formateur inexistant
- ID invalide

### Suppression

- Suppression d'un formateur existant
- Suppression d'un formateur inexistant
- ID invalide

---

# 🖥️ Interface Web

L'application utilise des pages JSP pour l'interface utilisateur.

## Students

Les principales pages sont :

```
students-list.jsp
student-form.jsp
```

### Students List

La page affiche :

- ID
- First Name
- Last Name
- Email
- Phone
- Actions

Actions disponibles :

```
Edit
Delete
```

Un bouton permet également d'ajouter un étudiant.

---

### Student Form

Le même formulaire est utilisé pour :

```
Add Student
Edit Student
```

Le formulaire détecte automatiquement le mode :

```java
boolean editMode = (student != null);
```

Si un étudiant existe dans le modèle :

```
Edit Student
```

Sinon :

```
Add Student
```

---

# 👨‍🏫 Trainers

Les principales pages sont :

```
trainers-list.jsp
trainer-form.jsp
```

### Trainers List

La page affiche :

- ID
- First Name
- Last Name
- Email
- Specialization
- Created At
- Actions

Actions disponibles :

```
Edit
Delete
```

---

### Trainer Form

Le formulaire est utilisé pour :

```
Add Trainer
Edit Trainer
```

Les champs sont :

```
First Name
Last Name
Email
Specialization
```

---

# 🌐 URLs principales

Les routes utilisées par l'application sont organisées sous :

```
/training/
```

## Students

```
/training/students-list
/training/student-add-form
/training/student-add
/training/student-edit
/training/student-update
/training/student-delete
```

## Trainers

```
/training/trainers-list
/training/trainer-add-form
/training/trainer-add
/training/trainer-edit
/training/trainer-update
/training/trainer-delete
```

---

# 🗄️ Base de données

Nom de la base :

```
training_center
```

Tables :

```
students
trainers
trainings
enrollments
```

---

## Table students

```
students
--------------------------------
id
first_name
last_name
email
phone
created_at
```

---

## Table trainers

```
trainers
--------------------------------
id
first_name
last_name
email
specialization
created_at
```

---

## Table trainings

```
trainings
--------------------------------
id
title
description
duration
trainer_id
created_at
```

Le champ :

```
trainer_id
```

permet d'associer une formation à un formateur.

---

## Table enrollments

```
enrollments
--------------------------------
id
student_id
training_id
enrollment_date
```

Cette table représente l'inscription d'un étudiant à une formation.

---

# 🔗 Connexion à MySQL

La connexion à la base de données est centralisée grâce aux classes :

```
DataSource
MySQLDataSource
Database
```

Exemple :

```java
DataSource ds =
        new MySQLDataSource("training_center");

Database db =
        new Database(ds);
```

La classe `MySQLDataSource` configure automatiquement :

```
Driver
Host
Database
Username
Password
```

---

# 🗃️ Classe Database

La classe `Database` fournit des méthodes génériques pour communiquer avec la base de données.

Principales méthodes :

```java
executeQuery()
executeUpdate()
select()
selectById()
selectByKeyword()
```

Exemple :

```java
String[][] data = db.select("trainers");
```

Pour rechercher un élément :

```java
String[][] data =
        db.selectById("trainers", "id", id);
```

---

# 🔄 Fonctionnement d'une requête

Exemple pour récupérer tous les formateurs :

```
JSP
 ↓
TrainerAction
 ↓
TrainerService
 ↓
TrainerDao
 ↓
TrainerDaoJdbc
 ↓
Database
 ↓
MySQL
 ↓
TrainerORM
 ↓
List<Trainer>
 ↓
Model
 ↓
JSP
```

---

# 🛡️ Validation des données

La validation métier est effectuée dans :

```
TrainerServiceDefault
```

Exemples de règles :

### First Name

Le prénom est obligatoire.

```
null        → invalide
""          → invalide
"   "       → invalide
"Ahmed"     → valide
```

### Last Name

Le nom est obligatoire.

### Email

L'email doit respecter un format valide.

Exemple valide :

```
ahmed@gmail.com
```

Exemple invalide :

```
email-invalide
```

### Specialization

La spécialisation est obligatoire.

---

# ❗ Gestion des exceptions

Le projet possède des exceptions métier :

```
StudentException
TrainerException
```

Exemple :

```java
throw new TrainerException(
    "Trainer not found."
);
```

Cela permet de distinguer les erreurs métier des erreurs techniques.

---

# 🧪 Exécution des tests

Les tests peuvent être exécutés avec Maven :

```bash
mvn test
```

Pour nettoyer le projet :

```bash
mvn clean
```

Pour nettoyer puis exécuter les tests :

```bash
mvn clean test
```

---

# ▶️ Exécution du projet

## Prérequis

Avant de lancer le projet, il faut avoir :

- JDK installé
- Maven installé
- Apache Tomcat configuré
- MySQL installé
- Base de données `training_center` créée
- Driver JDBC MySQL disponible

---

## Configuration de la base de données

Créer la base :

```sql
CREATE DATABASE training_center;
```

Puis créer les tables nécessaires :

```
students
trainers
trainings
enrollments
```

La configuration de connexion doit correspondre aux paramètres utilisés dans :

```
MySQLDataSource
```

---

# 🚀 Lancer l'application

1. Démarrer MySQL.
2. Vérifier que la base `training_center` existe.
3. Configurer Apache Tomcat.
4. Déployer l'application.
5. Démarrer le serveur.
6. Ouvrir l'application dans le navigateur.

L'application utilise le contexte :

```
/training
```

---

# 📐 Principes appliqués

Le projet applique plusieurs principes de conception.

### Separation of Concerns

Chaque couche possède une responsabilité spécifique.

```
JSP       → Présentation
Action    → Gestion des requêtes
Service   → Logique métier
DAO       → Accès aux données
Model     → Données métier
```

### Dependency Inversion

Les services dépendent des interfaces DAO plutôt que des implémentations concrètes.

```java
private TrainerDao trainerDao;
```

### Dependency Injection

Les dépendances sont injectées par constructeur.

```java
public TrainerServiceDefault(TrainerDao trainerDao) {
    this.trainerDao = trainerDao;
}
```

### Testability

Grâce aux interfaces DAO, le DAO réel peut être remplacé par un Mock.

```
TrainerDaoJdbc
      ou
TrainerDaoMock
```

---

# 📊 CRUD

L'application implémente les opérations CRUD.

```
CREATE
   ↓
Add Student / Add Trainer

READ
   ↓
List Students / List Trainers

UPDATE
   ↓
Edit Student / Edit Trainer

DELETE
   ↓
Delete Student / Delete Trainer
```

---

# 📚 Concepts étudiés

Ce projet permet de mettre en pratique :

- Java
- Jakarta EE
- Servlets
- JSP
- MVC
- JDBC
- DAO
- Service Layer
- Dependency Injection
- Dependency Inversion
- ORM Mapping
- MySQL
- Maven
- JUnit 5
- Unit Testing
- Mocking
- CRUD
- Git

---

# 👤 Auteur

**Boteina JBEL**

Master Qualité du Logiciel

Projet académique Jakarta EE / JDBC
