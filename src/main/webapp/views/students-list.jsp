<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Student" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%
    Model model = (Model) request.getAttribute("model");
    List<Student> students =
            (List<Student>) model.getModel("students");
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Students List</title>

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
}

</style>

</head>

<body>

<h1>Students List</h1>

<a class="add-button"
   href="<%= request.getContextPath() %>/training/student-add-form">
    + Add Student
</a>

<table>

    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Actions</th>
    </tr>

    <%
    for(Student s : students) {
    %>

    <tr>

        <td><%= s.getId() %></td>

        <td><%= s.getFirstName() %></td>

        <td><%= s.getLastName() %></td>

        <td><%= s.getEmail() %></td>

        <td><%= s.getPhone() %></td>

        <td>

            <a class="edit-button"
               href="<%= request.getContextPath() %>/training/student-edit?id=<%= s.getId() %>">
                Edit
            </a>

            
            <form method="post"
			      action="<%= request.getContextPath() %>/training/student-delete"
			      style="display:inline;"
			      onsubmit="return confirm('Are you sure you want to delete this student?');">
			
			    <input type="hidden"
			           name="id"
			           value="<%= s.getId() %>">
			
			    <button class="delete-button" type="submit">
			        🗑️ Delete
			    </button>
			
			</form>

        </td>

    </tr>

    <%
    }
    %>

</table>

</body>
</html>
