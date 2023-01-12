<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<nav style="margin-left: 15px; margin-right: 15px" class="nav nav-pills nav-justified">
    <a class="nav-link" href="${pageContext.request.contextPath}/home">О нас</a>
    <a class="nav-link" href="${pageContext.request.contextPath}/journal">Журнал</a>
    <a class="nav-link active" href="${pageContext.request.contextPath}/students">Ученики</a>
    <a class="nav-link" href="${pageContext.request.contextPath}/themes">Предметы</a>
</nav>


<form:form style="margin-left: 15px;margin-right: 15px;width: 200px" action="/app/students/edit_save"
           method="put" modelAttribute="student">
    <div class="form-group">
        <form:input type="hidden" placeholder="${student.id}" path="id"/>
    </div>
    <div class="form-group">
        <label for="exampleInputEmail1">Имя</label>
        <form:input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                    placeholder="${student.name}" path="name"/>
    </div>
    <div class="form-group">
        <label for="exampleInputEmail11">Фамилия</label>
        <form:input type="text" class="form-control" id="exampleInputEmail11" aria-describedby="emailHelp"
                    placeholder="${student.secondSeName}" path="seName"/>
    </div>
    <div class="form-group">
        <label for="exampleInputEmail12">Отчество</label>
        <form:input type="text" class="form-control" id="exampleInputEmail12" aria-describedby="emailHelp"
                    placeholder="${student.secondSeName}" path="secondSeName"/>
    </div>
    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
</form:form>

</body>
</html>