<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Enrollment" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%
    Model model = (Model) request.getAttribute("model");

    List<Enrollment> enrollments =
            (List<Enrollment>) model.getModel("enrollments");
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Enrollments List</title>

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

        .navigation {
            margin-bottom: 20px;
        }

        .navigation a {
            margin-right: 15px;
            text-decoration: none;
            color: #1976d2;
        }

    </style>
</head>

<body>

    <h1>Enrollments List</h1>

    <!-- Navigation -->
    <div class="navigation">

        <a href="<%= request.getContextPath() %>/training/students-list">
            Students
        </a>

        <a href="<%= request.getContextPath() %>/training/trainers-list">
            Trainers
        </a>

        <a href="<%= request.getContextPath() %>/training/trainings-list">
            Trainings
        </a>

        <a href="<%= request.getContextPath() %>/training/enrollments-list">
            Enrollments
        </a>

    </div>


    <!-- Add Enrollment -->
    <a class="add-button"
       href="<%= request.getContextPath() %>/training/enrollment-add-form">
        + Add Enrollment
    </a>


    <% if (enrollments != null && !enrollments.isEmpty()) { %>

        <table>

            <tr>
                <th>ID</th>
                <th>Student</th>
                <th>Training</th>
                <th>Enrollment Date</th>
                <th>Actions</th>
            </tr>


            <% for (Enrollment enrollment : enrollments) { %>

                <tr>

                    <td>
                        <%= enrollment.getId() %>
                    </td>


                    <td>

                        <% if (enrollment.getStudent() != null) { %>

                            <%= enrollment.getStudent().getFirstName() %>
                            <%= enrollment.getStudent().getLastName() %>

                        <% } else { %>

                            No student assigned

                        <% } %>

                    </td>


                    <td>

                        <% if (enrollment.getTraining() != null) { %>

                            <%= enrollment.getTraining().getTitle() %>

                        <% } else { %>

                            No training assigned

                        <% } %>

                    </td>


                    <td>
                        <%= enrollment.getEnrollmentDate() %>
                    </td>


                    <td>

                        <a class="edit-button"
                           href="<%= request.getContextPath() %>/training/enrollment-edit?id=<%= enrollment.getId() %>">
                            Edit
                        </a>


                        <form method="post"
                              action="<%= request.getContextPath() %>/training/enrollment-delete"
                              style="display:inline;"
                              onsubmit="return confirm('Are you sure you want to delete this enrollment?');">

                            <input type="hidden"
                                   name="id"
                                   value="<%= enrollment.getId() %>">

                            <button class="delete-button" type="submit">
                                🗑️ Delete
                            </button>

                        </form>

                    </td>

                </tr>

            <% } %>

        </table>

    <% } else { %>

        <p>No enrollments found.</p>

    <% } %>

</body>

</html>