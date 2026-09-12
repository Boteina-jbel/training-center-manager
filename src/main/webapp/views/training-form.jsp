<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.models.Training" %>
<%@ page import="org.mql.jee.trainingcenter.models.Trainer" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>

<%@ include file="./header.jsp" %>

<%
    Model model = (Model) request.getAttribute("model");

    Training training =
            (Training) model.getModel("training");

    List<Trainer> trainers =
            (List<Trainer>) model.getModel("trainers");

    boolean edit = training != null;

    String action;

    if (edit) {
        action = "training-update";
    } else {
        action = "training-add";
    }
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>
        <%= edit ? "Edit Training" : "Add Training" %>
    </title>
    
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/forms.css">

</head>

<body>

    <h1>
        <%= edit ? "Edit Training" : "Add Training" %>
    </h1>

    <form
        action="${pageContext.request.contextPath}/training/<%= action %>"
        method="post">

        <% if (edit) { %>

            <input
                type="hidden"
                name="id"
                value="<%= training.getId() %>">

        <% } %>


        <label for="title">
            Title:
        </label>

        <input
		    type="text"
		    id="title"
		    name="title"
		    class="form-control"
		    required
		    value="<%= edit ? training.getTitle() : "" %>">
		
		<label for="description">Description:</label>
		<textarea
		    id="description"
		    name="description"
		    class="form-control"
		    required><%= edit ? training.getDescription() : "" %></textarea>
		
		<label for="duration">Duration (hours):</label>
		<input
		    type="number"
		    id="duration"
		    name="duration"
		    class="form-control"
		    min="1"
		    required
		    value="<%= edit ? training.getDuration() : "" %>">
		
		<label for="trainerId">Trainer:</label>
		
		<select
		    id="trainerId"
		    name="trainerId"
		    class="form-control"
		    required>

            <option value="">
                -- Select a trainer --
            </option>

            <%
                for (Trainer trainer : trainers) {
            %>

                <option
                    value="<%= trainer.getId() %>"

                    <%
                        if (edit
                                && training.getTrainer() != null
                                && training.getTrainer().getId() == trainer.getId()) {
                    %>

                        selected

                    <%
                        }
                    %>
                >

                    <%= trainer.getFirstName() %>
                    <%= trainer.getLastName() %>

                </option>

            <%
                }
            %>

        </select>


        <button type="submit">

            <%= edit ? "Update Training" : "Add Training" %>

        </button>

    </form>


    <a
        class="back-button"
        href="<%= request.getContextPath() %>/training/trainings-list">

        ← Back to List

    </a>

</body>

</html>