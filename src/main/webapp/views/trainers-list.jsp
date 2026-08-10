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

<h1>Trainers List</h1>


<a class="add-button" href="<%= request.getContextPath() %>/training/trainer-add-form">
     + Add Trainer
</a>


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

            <a class="edit-button" 
            	href="<%= request.getContextPath() %>/training/trainer-edit?id=<%= trainer.getId() %>">
                ✏️ Edit
            </a>

            <form method="post"
			      action="<%= request.getContextPath() %>/training/trainer-delete"
			      style="display:inline;"
			      onsubmit="return confirm('Are you sure you want to delete this trainer?');">
			
			    <input type="hidden"
			           name="id"
			           value="<%= trainer.getId() %>">
			
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