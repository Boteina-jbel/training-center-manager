<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="org.mql.jee.trainingcenter.models.Trainer" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%
    Model model = (Model) request.getAttribute("model");

    Trainer trainer = (Trainer) model.getModel("trainer");

    boolean edit = trainer != null;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <%= edit ? "Edit Trainer" : "Add Trainer" %>
    </title>

    <style>
        form {
            width: 400px;
            margin: 30px auto;
        }

        label {
            display: block;
            margin-top: 15px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        button {
            margin-top: 20px;
            padding: 10px 20px;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>

<body>

<h1>
    <%= edit ? "Edit Trainer" : "Add Trainer" %>
</h1>

<form method="post"
      action="<%= request.getContextPath() %>/training/<%= edit ? "trainer-update" : "trainer-add" %>">

    <% if (edit) { %>

        <input type="hidden"
               name="id"
               value="<%= trainer.getId() %>">

    <% } %>


    <label for="firstName">
        First Name:
    </label>

    <input type="text"
           id="firstName"
           name="firstName"
           value="<%= edit ? trainer.getFirstName() : "" %>"
           required>


    <label for="lastName">
        Last Name:
    </label>

    <input type="text"
           id="lastName"
           name="lastName"
           value="<%= edit ? trainer.getLastName() : "" %>"
           required>


    <label for="email">
        Email:
    </label>

    <input type="email"
           id="email"
           name="email"
           value="<%= edit ? trainer.getEmail() : "" %>">


    <label for="specialization">
        Specialization:
    </label>

    <input type="text"
           id="specialization"
           name="specialization"
           value="<%= edit ? trainer.getSpecialization() : "" %>"
           required>


    <button type="submit">
        <%= edit ? "Update Trainer" : "Add Trainer" %>
    </button>

    <a href="<%= request.getContextPath() %>/training/trainers-list">
        Cancel
    </a>

</form>

</body>
</html>