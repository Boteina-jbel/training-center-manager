<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Enrollment" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%@ include file="./header.jsp" %>

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
    
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/lists.css">

</head>

<body>

    <h1>Enrollments List</h1>


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