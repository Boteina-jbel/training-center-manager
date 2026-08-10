<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Trainer" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%
    Model model = (Model) request.getAttribute("model");
    List<Trainer> trainers =
            (List<Trainer>) model.getModel("trainers");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trainers List</title>

    <style>
        table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
        }

        .actions {
            text-align: center;
            margin: 20px;
        }

        a {
            text-decoration: none;
            margin: 5px;
        }
    </style>
</head>

<body>

<h1>Trainers List</h1>

<div class="actions">
    <a href="<%= request.getContextPath() %>/training/trainer-add-form">
        ➕ Add Trainer
    </a>
</div>

<table>

    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Specialization</th>
        <th>Created At</th>
        <th>Actions</th>
    </tr>

    <%
        if (trainers != null && !trainers.isEmpty()) {

            for (Trainer trainer : trainers) {
    %>

    <tr>

        <td>
            <%= trainer.getId() %>
        </td>

        <td>
            <%= trainer.getFirstName() %>
        </td>

        <td>
            <%= trainer.getLastName() %>
        </td>

        <td>
            <%= trainer.getEmail() %>
        </td>

        <td>
            <%= trainer.getSpecialization() %>
        </td>

        <td>
            <%= trainer.getCreatedAt() %>
        </td>

        <td>

            <a href="<%= request.getContextPath() %>/training/trainer-edit?id=<%= trainer.getId() %>">
                ✏️ Edit
            </a>

            <a href="<%= request.getContextPath() %>/training/trainer-delete?id=<%= trainer.getId() %>"
               onclick="return confirm('Are you sure you want to delete this trainer?');">
                🗑️ Delete
            </a>

        </td>

    </tr>

    <%
            }

        } else {
    %>

    <tr>
        <td colspan="7">
            No trainers found.
        </td>
    </tr>

    <%
        }
    %>

</table>

</body>
</html>