<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>

<body>

    <h1>Une erreur est survenue</h1>

    <p>
        ${errorMessage}
    </p>

    <a href="${pageContext.request.contextPath}/training/students-list">
        Retour à la liste des étudiants
    </a>

</body>
</html>