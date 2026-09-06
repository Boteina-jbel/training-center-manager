<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Training" %>
<%@ page import="org.mql.jee.trainingcenter.models.Trainer" %>

<%
    Object modelObject = request.getAttribute("model");

    org.mql.jee.trainingcenter.context.Model model =
            (org.mql.jee.trainingcenter.context.Model) modelObject;

    List<Training> trainings =
            (List<Training>) model.getModel("trainings");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Trainings</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        h1 {
            margin-bottom: 20px;
        }

        .actions {
            margin-bottom: 20px;
        }

        a {
            text-decoration: none;
            margin-right: 10px;
        }

        .btn {
            padding: 8px 12px;
            border: 1px solid #333;
            border-radius: 4px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

    </style>

</head>

<body>

    <h1>Liste des formations</h1>

    <div class="actions">

        <a class="btn"
           href="${pageContext.request.contextPath}/training/training-add-form">
            Ajouter une formation
        </a>

        <a class="btn"
           href="${pageContext.request.contextPath}/training/students-list">
            Étudiants
        </a>

        <a class="btn"
           href="${pageContext.request.contextPath}/training/trainers-list">
            Formateurs
        </a>

    </div>

    <table>

        <thead>

            <tr>

                <th>ID</th>
                <th>Titre</th>
                <th>Description</th>
                <th>Durée</th>
                <th>Formateur</th>
                <th>Actions</th>

            </tr>

        </thead>

        <tbody>

        <% for (Training training : trainings) { %>

            <tr>

                <td>
                    <%= training.getId() %>
                </td>

                <td>
                    <%= training.getTitle() %>
                </td>

                <td>
                    <%= training.getDescription() %>
                </td>

                <td>
                    <%= training.getDuration() %> h
                </td>

                <td>

                    <% if (training.getTrainer() != null) { %>

					    <%= training.getTrainer().getFirstName() %>
					    <%= training.getTrainer().getLastName() %>
					
					<% } else { %>
					
					    Aucun formateur
					
					<% } %>

                </td>

                <td>

                    <a href="${pageContext.request.contextPath}/training/training-edit?id=<%= training.getId() %>">
                        Modifier
                    </a>

                    <form
                        action="${pageContext.request.contextPath}/training/training-delete"
                        method="post"
                        style="display:inline;">

                        <input
                            type="hidden"
                            name="id"
                            value="<%= training.getId() %>">

                        <button
                            type="submit"
                            onclick="return confirm('Voulez-vous vraiment supprimer cette formation ?');">

                            Supprimer

                        </button>

                    </form>

                </td>

            </tr>

        <% } %>

        </tbody>

    </table>

</body>

</html>