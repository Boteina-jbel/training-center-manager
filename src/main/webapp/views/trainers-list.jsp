<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.mql.jee.trainingcenter.models.Trainer"%>
<%@page import="org.mql.jee.trainingcenter.context.Model"%>

<%
    Model model = (Model) request.getAttribute("model");
    List<Trainer> trainers = (List<Trainer>) model.getModel("trainers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trainers List</title>

<style>

body{
    font-family: Arial, sans-serif;
    margin:40px;
}

table{
    border-collapse:collapse;
    width:100%;
}

th,td{
    border:1px solid #ccc;
    padding:10px;
    text-align:left;
}

th{
    background:#f2f2f2;
}

h1{
    color:#333;
}

</style>

</head>

<body>

<h1>Training Center - Trainers</h1>

<table>

<tr>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    <th>Specialization</th>
    <th>Created At</th>
</tr>

<%
for(Trainer trainer : trainers){
%>

<tr>

    <td><%=trainer.getId()%></td>

    <td><%=trainer.getFirstName()%></td>

    <td><%=trainer.getLastName()%></td>

    <td><%=trainer.getEmail()%></td>

    <td><%=trainer.getSpecialization()%></td>

    <td><%=trainer.getCreatedAt()%></td>

</tr>

<%
}
%>

</table>

</body>
</html>