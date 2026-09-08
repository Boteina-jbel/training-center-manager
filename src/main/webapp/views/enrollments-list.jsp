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
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-bottom: 20px;
        }

        .add-button:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .edit-button {
            background-color: #007bff;
            color: white;
            padding: 6px 10px;
            text-decoration: none;
            border-radius: 4px;
        }

        .delete-button {
            background-color: #dc3545;
            color: white;
            padding: 6px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .navigation {
            margin-bottom: 20px;
        }

        .navigation a {
            margin-right: 15px;
            text-decoration: none;
            color: #007bff;
        }

    </style>

</head>

<body>

    <h1>Enrollments List</h1>


    <!-- Navigation -->

    <div class="navigation">

        <a href="${pageContext.request.contextPath}/training/students-list">
            Students
        </a>

        <a href="${pageContext.request.contextPath}/training/trainers-list">
            Trainers
        </a>

        <a href="${pageContext.request.contextPath}/training/trainings-list">
            Trainings
        </a>

        <a href="${pageContext.request.contextPath}/training/enrollments-list">
            Enrollments
        </a>

    </div>


    <!-- Add Enrollment -->

    <a
        href="${pageContext.request.contextPath}/training/enrollment-add-form"
        class="add-button">

        + Add Enrollment

    </a>


    <!-- Enrollments Table -->

    <% if (enrollments != null && !enrollments.isEmpty()) { %>

        <table>

            <thead>

                <tr>
                    <th>ID</th>
                    <th>Student</th>
                    <th>Training</th>
                    <th>Enrollment Date</th>
                    <th>Actions</th>
                </tr>

            </thead>

            <tbody>

                <% for (Enrollment enrollment : enrollments) { %>

                    <tr>

                        <td>
                            <%= enrollment.getId() %>
                        </td>


                        <td>

                            <%
                                if (enrollment.getStudent() != null) {
                            %>

                                <%= enrollment.getStudent().getFirstName() %>
                                <%= enrollment.getStudent().getLastName() %>

                            <%
                                } else {
                            %>

                                No student assigned

                            <%
                                }
                            %>

                        </td>


                        <td>

                            <%
                                if (enrollment.getTraining() != null) {
                            %>

                                <%= enrollment.getTraining().getTitle() %>

                            <%
                                } else {
                            %>

                                No training assigned

                            <%
                                }
                            %>

                        </td>


                        <td>
                            <%= enrollment.getEnrollmentDate() %>
                        </td>


                        <td>

                            <a
                                href="${pageContext.request.contextPath}/training/enrollment-edit?id=<%= enrollment.getId() %>"
                                class="edit-button">

                                Edit

                            </a>


                            <form
                                action="${pageContext.request.contextPath}/training/enrollment-delete"
                                method="post"
                                style="display:inline;">

                                <input
                                    type="hidden"
                                    name="id"
                                    value="<%= enrollment.getId() %>">

                                <button
                                    type="submit"
                                    class="delete-button"
                                    onclick="return confirm('Are you sure you want to delete this enrollment?');">

                                    Delete

                                </button>

                            </form>

                        </td>

                    </tr>

                <% } %>

            </tbody>

        </table>

    <% } else { %>

        <p>No enrollments found.</p>

    <% } %>

</body>
</html>