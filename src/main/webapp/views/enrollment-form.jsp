<%@ page import="java.util.List" %>
<%@ page import="org.mql.jee.trainingcenter.context.Model" %>
<%@ page import="org.mql.jee.trainingcenter.models.Enrollment" %>
<%@ page import="org.mql.jee.trainingcenter.models.Student" %>
<%@ page import="org.mql.jee.trainingcenter.models.Training" %>

<%
    Model model = (Model) request.getAttribute("model");

    Enrollment enrollment =
            (Enrollment) model.getModel("enrollment");

    List<Student> students =
            (List<Student>) model.getModel("students");

    List<Training> trainings =
            (List<Training>) model.getModel("trainings");

    boolean editMode = enrollment != null;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= editMode ? "Edit Enrollment" : "Add Enrollment" %></title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }

        h1 {
            margin-bottom: 25px;
        }

        form {
            width: 500px;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
            font-weight: bold;
        }

        select,
        input[type="date"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }

        button {
            margin-top: 20px;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            background-color: #28a745;
            color: white;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        .back-button {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 15px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .back-button:hover {
            background-color: #5a6268;
        }
    </style>
</head>

<body>

    <h1><%= editMode ? "Edit Enrollment" : "Add Enrollment" %></h1>

    <form
        action="${pageContext.request.contextPath}/training/<%= editMode ? "enrollment-update" : "enrollment-add" %>"
        method="post">

        <% if (editMode) { %>
            <input
                type="hidden"
                name="id"
                value="<%= enrollment.getId() %>">
        <% } %>

        <label for="studentId">Student</label>

        <select name="studentId" id="studentId" required>
            <option value="">-- Select a student --</option>

            <% if (students != null) {
                for (Student student : students) { %>

                    <option
                        value="<%= student.getId() %>"
                        <%
                            if (editMode
                                    && enrollment.getStudent() != null
                                    && enrollment.getStudent().getId() == student.getId()) {
                        %>
                            selected
                        <% } %>
                    >
                        <%= student.getFirstName() %>
                        <%= student.getLastName() %>
                    </option>

            <%  }
               } %>
        </select>


        <label for="trainingId">Training</label>

        <select name="trainingId" id="trainingId" required>
            <option value="">-- Select a training --</option>

            <% if (trainings != null) {
                for (Training training : trainings) { %>

                    <option
                        value="<%= training.getId() %>"
                        <%
                            if (editMode
                                    && enrollment.getTraining() != null
                                    && enrollment.getTraining().getId() == training.getId()) {
                        %>
                            selected
                        <% } %>
                    >
                        <%= training.getTitle() %>
                    </option>

            <%  }
               } %>
        </select>


        <label for="enrollmentDate">Enrollment Date</label>

        <input
            type="date"
            name="enrollmentDate"
            id="enrollmentDate"
            value="<%= editMode && enrollment.getEnrollmentDate() != null
                    ? enrollment.getEnrollmentDate()
                    : "" %>"
            required>


        <br>

        <button type="submit">
            <%= editMode ? "Update Enrollment" : "Add Enrollment" %>
        </button>

    </form>

    <a
        href="${pageContext.request.contextPath}/training/enrollments-list"
        class="back-button">
        Back to List
    </a>

</body>
</html>