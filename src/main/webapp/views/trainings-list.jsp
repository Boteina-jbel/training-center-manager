<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Training" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%@ include file="./header.jsp" %>

<%
    Model model = (Model) request.getAttribute("model");

    List<Training> trainings =
            (List<Training>) model.getModel("trainings");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Trainings List</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        h1 {
            margin-bottom: 20px;
        }

        .add-button {
            display: inline-block;
            padding: 10px 15px;
            background-color: #2e7d32;
            color: white;
            text-decoration: none;
            margin-bottom: 20px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .edit-button {
            color: #1976d2;
            text-decoration: none;
            margin-right: 10px;
        }

        .delete-button {
            color: #d32f2f;
            text-decoration: none;
            border: none;
            background: none;
            padding: 0;
            cursor: pointer;
            font-size: inherit;
        }

        .navigation {
            margin-bottom: 20px;
        }

        .navigation a {
            margin-right: 15px;
            text-decoration: none;
        }

    </style>

</head>

<body>

    <h1>Trainings List</h1>

    <a class="add-button"
       href="<%= request.getContextPath() %>/training/training-add-form">

        + Add Training

    </a>

    <div class="navigation">

        <a href="<%= request.getContextPath() %>/training/students-list">
            Students
        </a>

        <a href="<%= request.getContextPath() %>/training/trainers-list">
            Trainers
        </a>

    </div>

    <table>

        <tr>

            <th>ID</th>

            <th>Title</th>

            <th>Description</th>

            <th>Duration</th>

            <th>Trainer</th>

            <th>Actions</th>

        </tr>

        <%
            if (trainings != null && !trainings.isEmpty()) {

                for (Training training : trainings) {
        %>

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
                <%= training.getDuration() %> hours
            </td>

            <td>

                <% if (training.getTrainer() != null) { %>

                    <%= training.getTrainer().getFirstName() %>
                    <%= training.getTrainer().getLastName() %>

                <% } else { %>

                    No trainer assigned

                <% } %>

            </td>

            <td>

                <a class="edit-button"
                   href="<%= request.getContextPath() %>/training/training-edit?id=<%= training.getId() %>">

                    ✏️ Edit

                </a>

                <form method="post"
                      action="<%= request.getContextPath() %>/training/training-delete"
                      style="display:inline;"
                      onsubmit="return confirm('Are you sure you want to delete this training?');">

                    <input type="hidden"
                           name="id"
                           value="<%= training.getId() %>">

                    <button class="delete-button" type="submit">

                        🗑️ Delete

                    </button>

                </form>

            </td>

        </tr>

        <%
                }

            } else {
        %>

        <tr>

            <td colspan="6">
                No trainings found.
            </td>

        </tr>

        <%
            }
        %>

    </table>

</body>

</html>