<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Training Center Manager</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f7f6;
            color: #263238;
        }

        /* ================= HEADER ================= */

        .header {
            background-color: #2e7d32;
            color: white;
            padding: 25px 50px;

            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .brand {
            font-size: 24px;
            font-weight: bold;
        }

        .brand span {
            font-weight: normal;
        }

        .header-text {
            font-size: 14px;
            opacity: 0.9;
        }


        /* ================= MAIN ================= */

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 50px 30px;
        }

        .welcome {
            margin-bottom: 40px;
        }

        .welcome h1 {
            margin: 0 0 10px 0;
            font-size: 34px;
            color: #263238;
        }

        .welcome p {
            margin: 0;
            color: #607d8b;
            font-size: 16px;
        }


        /* ================= CARDS ================= */

        .cards {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 25px;
        }

        .card {
            background-color: white;
            padding: 30px;

            border: 1px solid #e0e0e0;
            border-radius: 8px;

            text-decoration: none;
            color: inherit;

            transition: 0.2s;
        }

        .card:hover {
            border-color: #2e7d32;
            transform: translateY(-3px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
        }


        /* ================= ICON ================= */

        .icon {
            width: 55px;
            height: 55px;

            display: flex;
            align-items: center;
            justify-content: center;

            border-radius: 8px;

            background-color: #e8f5e9;

            font-size: 27px;

            margin-bottom: 20px;
        }


        /* ================= CARD TEXT ================= */

        .card h2 {
            margin: 0 0 10px 0;
            font-size: 21px;
            color: #263238;
        }

        .card p {
            margin: 0;
            color: #78909c;
            line-height: 1.5;
            font-size: 14px;
        }

        .card-link {
            display: inline-block;
            margin-top: 20px;

            color: #2e7d32;

            font-size: 14px;
            font-weight: bold;
        }


        /* ================= QUICK ACCESS ================= */

        .section-title {
            margin-top: 50px;
            margin-bottom: 20px;

            font-size: 20px;
        }

        .quick-links {
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
        }

        .quick-link {
            padding: 12px 18px;

            background-color: white;

            border: 1px solid #ddd;
            border-radius: 5px;

            text-decoration: none;
            color: #455a64;

            font-size: 14px;
        }

        .quick-link:hover {
            border-color: #2e7d32;
            color: #2e7d32;
        }


        /* ================= FOOTER ================= */

        .footer {
            text-align: center;

            padding: 25px;

            color: #90a4ae;

            font-size: 13px;
        }


        /* ================= RESPONSIVE ================= */

        @media (max-width: 700px) {

            .header {
                padding: 20px;
                flex-direction: column;
                align-items: flex-start;
                gap: 8px;
            }

            .container {
                padding: 30px 20px;
            }

            .cards {
                grid-template-columns: 1fr;
            }

            .welcome h1 {
                font-size: 28px;
            }

        }

    </style>

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