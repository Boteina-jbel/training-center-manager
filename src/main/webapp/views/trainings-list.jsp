<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Training" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%@ include file="./header.jsp" %>

<%
    Model model = (Model) request.getAttribute("model");

    List<Training> trainings =
            (List<Training>) model.getModel("trainings");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Trainings List</title>
    
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/lists.css">

</head>

<body>

    <h1>Trainings List</h1>

    <a class="add-button"
       href="<%= request.getContextPath() %>/training/training-add-form">

        + Add Training

    </a>

    <table>

        <tr>

            <th>ID</th>

            <th>Title</th>

            <th>Description</th>

            <th>Duration</th>

            <th>Trainer</th>

            <th>Actions</th>

        </tr>

        <%
            if (trainings != null && !trainings.isEmpty()) {

                for (Training training : trainings) {
        %>

        <tr>

            <td>
                <%= training.getId() %>
            </td>

            <td>
                <%= training.getTitle() %>
            </td>

            <td>
                <%= training.getDescription() %>
            </td>

            <td>
                <%= training.getDuration() %> hours
            </td>

            <td>

                <% if (training.getTrainer() != null) { %>

                    <%= training.getTrainer().getFirstName() %>
                    <%= training.getTrainer().getLastName() %>

                <% } else { %>

                    No trainer assigned

                <% } %>

            </td>

            <td>

                <a class="edit-button"
                   href="<%= request.getContextPath() %>/training/training-edit?id=<%= training.getId() %>">

                    ✏️ Edit

                </a>

                <form method="post"
                      action="<%= request.getContextPath() %>/training/training-delete"
                      style="display:inline;"
                      onsubmit="return confirm('Are you sure you want to delete this training?');">

                    <input type="hidden"
                           name="id"
                           value="<%= training.getId() %>">

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

            <td colspan="6">
                No trainings found.
            </td>

        </tr>

        <%
            }
        %>

    </table>

</body>

</html>