<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Студенты</title>
    <%--    <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">--%>
    <link rel="stylesheet" href=
            "https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src=
                    "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>

    <script src=
                    "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js">
    </script>

    <script src=
                    "https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
    </script>
</head>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="https://www.shutterstock.com/image-vector/playgroup-preschool-kindergarten-logo-template-600w-352550573.jpg"
             width="120" height="120" class="d-inline-block align-top" alt="">

    </a>
    <div style="margin: auto"><h2>Журнал оценок</h2></div>
</nav>
<body>
<div style="margin: auto">
    <nav style="margin-left: 15px; margin-right: 15px" class="nav nav-pills nav-justified">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">О нас</a>
        <a class="nav-link active" href="${pageContext.request.contextPath}/journal">Журнал</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/students">Ученики</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/themes">Предметы</a>
    </nav>
    <div class="btn-group" style="margin: 15px">
        <div class="dropdown" style="margin: 15px">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                Предметы
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                <form:form action="/app/journal" method="get" modelAttribute="selectedTheme">
                    <c:forEach items="${themeList}" var="theme">
                        <form:input class="dropdown-item" type="submit" path="name" placeholder="${theme.name}"
                                    value="${theme.name}"/>
                    </c:forEach>
                </form:form>
            </div>
        </div>
        <div style="display: inherit;margin-left: 150px">
            <button type="button" class="btn btn-info px-3">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-caret-left-fill" viewBox="0 0 16 16">
                    <path d="m3.86 8.753 5.482 4.796c.646.566 1.658.106 1.658-.753V3.204a1 1 0 0 0-1.659-.753l-5.48 4.796a1 1 0 0 0 0 1.506z"/>
                </svg>
            </button>
            <p class="font-weight-normal" style="margin: 10px">${currentDate}</p>
            <button type="button" class="btn btn-info px-3">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-caret-right-fill" viewBox="0 0 16 16">
                    <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/>
                </svg>
                </i>
            </button>
        </div>
    </div>

    <table class="table" style="width:50%; margin: 15px">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Отчество</th>
            <th scope="col">Баллы</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${studentList}" var="list">
            <tr>
                <td>${list.student.name}</td>
                <td>${list.student.seName}</td>
                <td>${list.student.secondSeName}</td>
                <c:forEach items="${list.grades}" var="gList">
                    <td>
                        <p>${gList.value}</p>
                        <p>${gList.theme.name}</p>
                        <p>${gList.date}</p>
                    </td>
                </c:forEach>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>