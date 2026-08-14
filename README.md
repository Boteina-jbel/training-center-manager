# Training Center Manager

Application web de gestion d'un centre de formation développée avec **Jakarta EE**, dans le cadre du Master Qualité du Logiciel.

Le projet a été réalisé sous forme de **Dynamic Web Project** avec Eclipse et Apache Tomcat, en utilisant JDBC pour l'accès aux données.

---

## 📌 Présentation

**Training Center Manager** est une application web permettant de gérer les principales ressources d'un centre de formation :

- Étudiants
- Formateurs
- Formations
- Inscriptions

La gestion des étudiants et des formateurs comprend les opérations CRUD :

- Consultation
- Recherche par identifiant
- Ajout
- Modification
- Suppression

L'application intègre également des validations métier, une gestion des exceptions et des tests unitaires de la couche Business.

---

## 🏗️ Architecture

Le projet adopte une architecture en couches permettant de séparer clairement les responsabilités.

```text
                    ┌──────────────────────┐
                    │        Web           │
                    │ Controller / Actions │
                    │        JSP           │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Business        │
                    │      Services        │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │        DAO           │
                    │   JDBC / Mappers     │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │        MySQL         │
                    └──────────────────────┘
```

### Web Layer

La couche Web est responsable de la réception des requêtes, de leur traitement et de la présentation des données.

Elle comprend :

* Un **Controller** central
* Des classes **Action**
* Des vues **JSP**

Le Controller joue le rôle de **Front Controller** et dirige les requêtes vers les actions appropriées.

Les Actions servent d'intermédiaires entre la couche Web et la couche Business.

---

### Business Layer

La couche Business contient la logique métier de l'application.

Elle repose sur des interfaces de services et leurs implémentations :

```text
StudentService
StudentServiceDefault

TrainerService
TrainerServiceDefault
```

Cette couche est responsable notamment :

* Des validations
* De la vérification des identifiants
* De la vérification de l'existence des entités
* De la gestion des règles métier
* De la communication avec les DAO

La couche Web ne communique donc pas directement avec la base de données.

---

### DAO Layer

La couche DAO est responsable de l'accès aux données.

Elle sépare l'abstraction de l'accès aux données de son implémentation :

```text
StudentDao
StudentDaoJdbc

TrainerDao
TrainerDaoJdbc
```

Les interfaces DAO définissent les opérations disponibles tandis que les implémentations utilisent JDBC pour communiquer avec la base de données.

Les classes ORM Mapper assurent la transformation des résultats SQL en objets Java.

```text
Database
    ↓
ResultSet
    ↓
ORM Mapper
    ↓
Java Object
```

---

## 🎯 Design Patterns et principes utilisés

Le projet met en pratique plusieurs concepts d'architecture logicielle et de conception étudiés en Jakarta EE.

### MVC 2

L'application suit une architecture **MVC 2** :

```text
Model
   │
   ├── Student
   ├── Trainer
   └── autres modèles

View
   │
   └── JSP

Controller
   │
   └── Servlet
```

Le Controller reçoit les requêtes, les transmet aux Actions et prépare les données nécessaires aux JSP.

Cette organisation permet de séparer :

* La présentation
* Le traitement
* Les données

---

### Front Controller

Le Servlet `Controller` constitue le point d'entrée principal des requêtes web.

Au lieu d'avoir plusieurs Servlets indépendants, les requêtes passent par un Controller central qui détermine l'action à exécuter.

```text
Browser
   ↓
Controller
   ↓
Action
   ↓
Business
```

Cela centralise la gestion des requêtes et simplifie l'organisation de la couche Web.

---

### Facade Pattern

Les classes `StudentAction` et `TrainerAction` utilisent les interfaces de la couche Business.

Par exemple :

```text
TrainerAction
      ↓
TrainerService
      ↓
TrainerDao
```

La couche Business fournit ainsi une interface simplifiée à la couche Web.

La couche Web n'a pas besoin de connaître les détails liés à JDBC ou à la base de données.

---

### DAO Pattern

Le **DAO Pattern** permet d'isoler l'accès aux données du reste de l'application.

La couche Business travaille avec :

```text
StudentDao
TrainerDao
```

et ne dépend pas directement des classes JDBC.

Cela permet de changer la technologie d'accès aux données sans modifier la logique métier.

---

### Bridge Pattern

Le projet applique également le principe du **Bridge Pattern** en séparant l'abstraction de son implémentation.

Par exemple :

```text
StudentDao
    ▲
    │
StudentDaoJdbc
```

La Business Layer dépend de `StudentDao` et non directement de `StudentDaoJdbc`.

Cette séparation permet de faire évoluer l'implémentation indépendamment de l'abstraction.

---

### Dependency Inversion

La couche Business dépend d'abstractions plutôt que de classes concrètes.

```java
private TrainerDao trainerDao;
```

L'implémentation est ensuite fournie à travers le constructeur :

```java
public TrainerServiceDefault(TrainerDao trainerDao) {
    this.trainerDao = trainerDao;
}
```

Le service peut ainsi fonctionner avec différentes implémentations de `TrainerDao`.

---

### Dependency Injection

Les dépendances sont injectées au moment de la création des services.

Le câblage des composants est centralisé dans :

```text
ApplicationContext
```

On obtient ainsi une chaîne de dépendances claire :

```text
DataSource
    ↓
Database
    ↓
DAO
    ↓
Service
    ↓
Action
    ↓
Controller
```

---

## 🧩 ApplicationContext

`ApplicationContext` est responsable du câblage des différentes couches de l'application.

Il crée notamment :

* La connexion à la base de données
* Les DAO
* Les Services

Cela permet d'éviter de créer directement les implémentations dans les classes métier.

Le contexte joue donc un rôle central dans la configuration de l'application.

---

## 📦 Model

La classe `Model` permet de transporter les données entre les Actions et les JSP.

Une Action peut placer des données dans le modèle :

```java
model.setModel("students", students);
```

Le Controller transmet ensuite le modèle à la requête.

La JSP récupère les données nécessaires pour construire la page.

Cette approche permet de séparer les données utilisées pour l'affichage de la logique de traitement.

---

## 🗄️ Base de données

Le projet utilise **MySQL 5.1** avec une base de données appelée :

```text
training_center
```

Elle contient actuellement les tables :

```text
students
trainers
trainings
enrollments
```

### Students

La table `students` contient les informations principales des étudiants :

```text
id
first_name
last_name
email
phone
created_at
```

### Trainers

La table `trainers` contient les informations principales des formateurs :

```text
id
first_name
last_name
email
specialization
created_at
```

### Trainings

La table `trainings` représente les formations et leur formateur associé.

```text
id
title
description
duration
trainer_id
created_at
```

### Enrollments

La table `enrollments` représente les inscriptions des étudiants aux formations.

```text
id
student_id
training_id
enrollment_date
```

---

## 🧪 Tests

Le projet contient des tests unitaires de la couche Business avec **JUnit 5**.

Des DAO Mock sont utilisés afin de tester les services sans dépendre directement de la base de données.

```text
TrainerService
       ↓
TrainerDao
       ↓
TrainerDaoMock
```

Cette approche permet de tester la logique métier de manière isolée.

Les tests couvrent notamment :

* Récupération des données
* Recherche par identifiant
* Ajout
* Modification
* Suppression
* Identifiants invalides
* Entités inexistantes
* Champs obligatoires
* Emails invalides
* Gestion des valeurs nulles

---

## ⚠️ Gestion des exceptions

Le projet utilise des exceptions métier personnalisées :

```text
StudentException
TrainerException
```

Elles permettent à la couche Business de signaler les erreurs liées aux règles métier.

Exemples :

```text
Student not found.
Trainer not found.
Invalid trainer ID.
Email is required.
Specialization is required.
```

---

## 📁 Structure du projet

```text
training-center-manager/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── org/mql/jee/
│       │       │
│       │       ├── jdbc/
│       │       │   ├── Database.java
│       │       │   ├── DataSource.java
│       │       │   └── MySQLDataSource.java
│       │       │
│       │       └── trainingcenter/
│       │           │
│       │           ├── business/
│       │           │
│       │           ├── context/
│       │           │
│       │           ├── dao/
│       │           │
│       │           ├── dao/mappers/
│       │           │
│       │           ├── exceptions/
│       │           │
│       │           ├── models/
│       │           │
│       │           ├── tests/
│       │           │
│       │           └── web/
│       │               └── actions/
│       │
│       └── webapp/
│           ├── index.jsp
│           │
│           ├── views/
│           │
│           └── WEB-INF/
│               ├── lib/
│               │   ├── database.jar
│               │   └── mysql.jar
│               │
│               └── web.xml
│
└── .gitignore
```

---

## ⚙️ Technologies

* **Java**
* **Jakarta EE**
* **Servlet**
* **JSP**
* **JDBC**
* **MySQL 5.1**
* **Apache Tomcat 10**
* **JUnit 5**
* **Eclipse**
* **HTML / CSS**
* **Git / GitHub**

Le projet est réalisé sans Maven afin de travailler directement avec la structure et la configuration d'un **Dynamic Web Project**.

---

## 🚀 Installation et exécution

### Prérequis

* JDK
* Eclipse
* Apache Tomcat 10
* MySQL 5.1

### Base de données

Créer la base :

```sql
CREATE DATABASE training_center;
```

Puis créer les tables nécessaires :

```text
students
trainers
trainings
enrollments
```

### Configuration

La configuration de la connexion à la base de données est centralisée dans :

```text
ApplicationContext.java
```

Les paramètres de connexion doivent être adaptés à l'environnement local.

### Tomcat

Configurer Apache Tomcat 10 dans Eclipse puis démarrer le serveur.

L'application utilise le mapping :

```text
/training/*
```

Les principales routes sont notamment :

```text
/training/students-list
/training/student-add-form
/training/trainers-list
/training/trainer-add-form
```

---

## 🔄 Flux d'une requête

Exemple de consultation de la liste des formateurs :

```text
Browser
   ↓
Controller
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
```

Les données sont ensuite transformées en objets Java par le mapper ORM et transmises à la JSP pour l'affichage.

```text
MySQL
   ↓
Database
   ↓
TrainerDaoJdbc
   ↓
TrainerORM
   ↓
TrainerService
   ↓
TrainerAction
   ↓
Model
   ↓
JSP
   ↓
Browser
```

---

## 🎓 Objectifs pédagogiques

Ce projet permet de mettre en pratique les principaux concepts de développement d'une application web Java avec Jakarta EE :

* Architecture en couches
* MVC 2
* Front Controller
* DAO Pattern
* Facade Pattern
* Bridge Pattern
* Dependency Inversion
* Dependency Injection
* Servlet
* JSP
* JDBC
* Mapping objet-relationnel
* Validation métier
* Exceptions personnalisées
* Tests unitaires
* Mocking
* Configuration d'un Dynamic Web Project
* Déploiement avec Tomcat

L'objectif principal est de comprendre comment les différentes couches d'une application web collaborent tout en restant indépendantes les unes des autres.

---

## 👨‍💻 Auteur

**Boteina JBEL**

Master Qualité du Logiciel

GitHub :
[https://github.com/Boteina-jbel](https://github.com/Boteina-jbel)

---

## 📚 Références

* Jakarta EE / Servlet : [https://jakarta.ee/specifications/servlet/](https://jakarta.ee/specifications/servlet/)
* Apache Tomcat : [https://tomcat.apache.org/tomcat-10.0-doc/](https://tomcat.apache.org/tomcat-10.0-doc/)
* MySQL Documentation : [https://dev.mysql.com/doc/](https://dev.mysql.com/doc/)
* JUnit 5 : [https://junit.org/junit5/docs/current/user-guide/](https://junit.org/junit5/docs/current/user-guide/)
