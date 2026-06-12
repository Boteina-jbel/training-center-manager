<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Student" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%
	Model model = (Model) request.getAttribute("model");
	List<Student> students = (List<Student>) model.getModel("students");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students List</title>

<style>
	table{
		border-collapse:collapse;
		width:80%;
		margin:auto;
	}

	th,td{
		border:1px solid black;
		padding:10px;
		text-align:left;
	}

	h1{
		text-align:center;
	}
</style>

</head>
<body>

	<h1>Students List</h1>

	<table>

		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Phone</th>
		</tr>

		<%
		for(Student s : students){
		%>

		<tr>
			<td><%= s.getId() %></td>
			<td><%= s.getFirstName() %></td>
			<td><%= s.getLastName() %></td>
			<td><%= s.getEmail() %></td>
			<td><%= s.getPhone() %></td>
		</tr>

		<%
		}
		%>

	</table>

</body>
</html>