<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="org.mql.jee.trainingcenter.models.Student" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%
    Model model = (Model) request.getAttribute("model");

    Student student = (Student) model.getModel("student");

    boolean editMode = (student != null);

    String formAction;

    if (editMode) {
        formAction = "student-update";
    } else {
        formAction = "student-add";
    }
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>
    <%= editMode ? "Edit Student" : "Add Student" %>
</title>

<style>

body {
    font-family: Arial, sans-serif;
    margin: 40px;
}

form {
    width: 400px;
}

label {
    display: block;
    margin-top: 15px;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
}

button {
    margin-top: 20px;
    padding: 10px 15px;
}

.back-button {
    display: inline-block;
    margin-top: 15px;
}

</style>

</head>

<body>

<h1>
    <%= editMode ? "Edit Student" : "Add Student" %>
</h1>

<form method="post"
      action="<%= request.getContextPath() %>/<%= formAction %>">

    <% if(editMode) { %>

        <input type="hidden"
               name="id"
               value="<%= student.getId() %>">

    <% } %>


    <label>First Name</label>

    <input type="text"
           name="firstName"
           value="<%= editMode ? student.getFirstName() : "" %>"
           required>


    <label>Last Name</label>

    <input type="text"
           name="lastName"
           value="<%= editMode ? student.getLastName() : "" %>"
           required>


    <label>Email</label>

    <input type="email"
           name="email"
           value="<%= editMode ? student.getEmail() : "" %>">


    <label>Phone</label>

    <input type="text"
           name="phone"
           value="<%= editMode ? student.getPhone() : "" %>">


    <button type="submit">

        <%= editMode ? "Update Student" : "Add Student" %>

    </button>

</form>

<a class="back-button"
   href="<%= request.getContextPath() %>/students-list">

    ← Back to Students List

</a>

</body>

</html>