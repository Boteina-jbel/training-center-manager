<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Training Center Manager</title>

	<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/dashboard.css">

</head>


<body>


    <!-- ================= HEADER ================= -->

    <header class="header">

        <div class="brand">
            Training Center <span>Manager</span>
        </div>

        <div class="header-text">
            Management System
        </div>

    </header>


    <!-- ================= MAIN ================= -->

    <main class="container">


        <div class="welcome">

            <h1>Welcome to Training Center Manager</h1>

            <p>
                Manage students, trainers, trainings and enrollments
                from one place.
            </p>

        </div>


        <!-- ================= MODULES ================= -->

        <div class="cards">


            <!-- Students -->

            <a
                class="card"
                href="<%= request.getContextPath() %>/training/students-list">

                <div class="icon">
                    👨‍🎓
                </div>

                <h2>Students</h2>

                <p>
                    Manage student information, contact details
                    and registrations.
                </p>

                <span class="card-link">
                    Manage Students →
                </span>

            </a>


            <!-- Trainers -->

            <a
                class="card"
                href="<%= request.getContextPath() %>/training/trainers-list">

                <div class="icon">
                    👨‍🏫
                </div>

                <h2>Trainers</h2>

                <p>
                    Manage trainers, their contact information
                    and specializations.
                </p>

                <span class="card-link">
                    Manage Trainers →
                </span>

            </a>


            <!-- Trainings -->

            <a
                class="card"
                href="<%= request.getContextPath() %>/training/trainings-list">

                <div class="icon">
                    📚
                </div>

                <h2>Trainings</h2>

                <p>
                    Create and manage training programs,
                    durations and assigned trainers.
                </p>

                <span class="card-link">
                    Manage Trainings →
                </span>

            </a>


            <!-- Enrollments -->

            <a
                class="card"
                href="<%= request.getContextPath() %>/training/enrollments-list">

                <div class="icon">
                    📝
                </div>

                <h2>Enrollments</h2>

                <p>
                    Manage student enrollments and their
                    assigned training programs.
                </p>

                <span class="card-link">
                    Manage Enrollments →
                </span>

            </a>


        </div>


        <!-- ================= QUICK ACCESS ================= -->

        <h2 class="section-title">
            Quick Access
        </h2>

        <div class="quick-links">

            <a
                class="quick-link"
                href="<%= request.getContextPath() %>/training/student-add-form">
                + Add Student
            </a>

            <a
                class="quick-link"
                href="<%= request.getContextPath() %>/training/trainer-add-form">
                + Add Trainer
            </a>

            <a
                class="quick-link"
                href="<%= request.getContextPath() %>/training/training-add-form">
                + Add Training
            </a>

            <a
                class="quick-link"
                href="<%= request.getContextPath() %>/training/enrollment-add-form">
                + Add Enrollment
            </a>

        </div>


    </main>


    <!-- ================= FOOTER ================= -->

    <footer class="footer">

        Training Center Manager · Jakarta EE Application

    </footer>


</body>

</html>