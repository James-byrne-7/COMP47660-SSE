<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Springfield Student Management System</title>
    <link rel="stylesheet" type="text/css" href="/home.css" />
</head>
<body>
<div id="page">
    <div id="header">
        <p class='title'>Springfield Student Management System</p>
        <p class="current_user">You are currently logged in as : ${pageContext.request.userPrincipal.name}</p>
        <a href="/dropout">Cancel Registration</a>
    </div>
    <ul>
        <li><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/modules">Modules</a></li>
        <li><a href="${contextPath}/fees">Fees</a></li>
        <li>
            <form method="post" action="logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input id="logout" type="submit" value="Logout">
            </form>
        </li>
    </ul>
    <div id="pageContent">
        <p class="title">Currently Enrolled Modules</p>
        <table border="1" cellpadding="8">
            <tr>
                <th>Code</th>
                <th>Module Title</th>
                <th>Topics Covered</th>
<%--                <th>Coordinator_ID</th>--%>
<%--                <th>Finished</th>--%>
<%--                <th>Grade</th>--%>
                <th>Actions</th>
            </tr>
            <c:forEach var="module" items="${modules}">
<%--                <c:set var="found" value="0"/>--%>
                <tr>
                    <td><c:out value="${module.code}" /></td>
                    <td><c:out value="${module.name}" /></td>
                    <td><c:out value="${module.description}" /></td>
                    <td>
                        <form:form method="post" action="statistics" modelAttribute="selectedModule">
                            <form:input path="code" type="hidden" value="${module.code}"/>
                            <input class="edit" type="submit" value="Statistics">
                        </form:form>
                        <a class="edit" href="/edit/${module.id}">Edit Module</a>
                        <form:form method="post" action="enrol" modelAttribute="selectedModule">
                            <form:input path="code" type="hidden" value="${module.code}"/>
                            <input class="edit" type="submit" value="Enrol">
                        </form:form>
                        <form:form method="post" action="drop" modelAttribute="selectedModule">
                            <form:input path="code" type="hidden" value="${module.code}"/>
                            <input class="edit" type="submit" value="Drop">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/modules/all">View All Modules</a>
    </div>
</div>
</body>
</html>
