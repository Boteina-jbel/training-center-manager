<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/header.css">
    
<header class="main-header">

    <div class="header-content">

        <div class="logo">
            🎓 Training Center Manager
        </div>

        <nav class="navigation">

            <a href="${pageContext.request.contextPath}/training/dashboard">
                Dashboard
            </a>

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

        </nav>

    </div>

</header>