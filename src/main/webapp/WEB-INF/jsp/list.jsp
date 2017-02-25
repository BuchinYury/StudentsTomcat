<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page session="false"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List</h1>
<a href="/lections">Лекции</a><br>
<a href="/addstudent">Добавить студента</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>FIO</th>
        <th>Email</th>
        <th>GroupID</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${studentList}" var="student">

        <tr>
            <td><c:out value="${student.id}"></c:out></td>
            <td><c:out value="${student.name}"></c:out></td>
            <td><c:out value="${student.email}"></c:out></td>
            <td><c:out value="${student.group_id}"></c:out></td>
            <td><a href="/edit?id=${student.id}">Edit</a></td>
            <td><a href="/delete?id=${student.id}">Delete</a></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
