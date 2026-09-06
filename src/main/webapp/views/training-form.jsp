<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Training" %>
<%@ page import="org.mql.jee.trainingcenter.models.Trainer" %>

<%
    org.mql.jee.trainingcenter.context.Model model =
            (org.mql.jee.trainingcenter.context.Model)
            request.getAttribute("model");

    Training training =
            (Training) model.getModel("training");

    List<Trainer> trainers =
            (List<Trainer>) model.getModel("trainers");

    boolean edit = training != null;

    String action;

    if (edit) {
        action = "training-update";
    } else {
        action = "training-add";
    }
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        <%= edit ? "Modifier une formation" : "Ajouter une formation" %>
    </title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        form {
            width: 500px;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
        }

        input,
        textarea,
        select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        textarea {
            height: 100px;
        }

        button {
            margin-top: 20px;
            padding: 10px 15px;
        }

        .back {
            display: inline-block;
            margin-top: 20px;
        }

    </style>

</head>

<body>

    <h1>
        <%= edit ? "Modifier une formation" : "Ajouter une formation" %>
    </h1>

    <form
        action="${pageContext.request.contextPath}/training/<%= action %>"
        method="post">

        <% if (edit) { %>

            <input
                type="hidden"
                name="id"
                value="<%= training.getId() %>">

        <% } %>


        <!-- TITLE -->

        <label for="title">
            Titre :
        </label>

        <input
            type="text"
            id="title"
            name="title"
            required
            value="<%= edit ? training.getTitle() : "" %>">


        <!-- DESCRIPTION -->

        <label for="description">
            Description :
        </label>

        <textarea
            id="description"
            name="description"
            required><%= edit ? training.getDescription() : "" %></textarea>


        <!-- DURATION -->

        <label for="duration">
            Durée (heures) :
        </label>

        <input
            type="number"
            id="duration"
            name="duration"
            min="1"
            required
            value="<%= edit ? training.getDuration() : "" %>">


        <!-- TRAINER -->

        <label for="trainerId">
            Formateur :
        </label>

        <select
            id="trainerId"
            name="trainerId"
            required>

            <option value="">
                -- Sélectionner un formateur --
            </option>

            <% for (Trainer trainer : trainers) { %>

                <option
                    value="<%= trainer.getId() %>"

                    <%
                        if (edit
                                && training.getTrainer() != null
                                && training.getTrainer().getId() == trainer.getId()) {
                    %>

                        selected

                    <%
                        }
                    %>
                >

                    <%= trainer.getFirstName() %>
                    <%= trainer.getLastName() %>

                </option>

            <% } %>

        </select>


        <button type="submit">

            <%= edit ? "Modifier" : "Ajouter" %>

        </button>

    </form>


    <a
        class="back"
        href="${pageContext.request.contextPath}/training/trainings-list">

        ← Retour à la liste

    </a>

</body>

</html>