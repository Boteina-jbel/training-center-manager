<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Student" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%@ include file="./header.jsp" %>

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
	
	<link rel="stylesheet"
	      href="${pageContext.request.contextPath}/css/lists.css">


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
