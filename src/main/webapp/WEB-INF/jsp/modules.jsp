<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <title>Springfield Student Management System</title>
    <link rel="stylesheet" type="text/css" href="/home.css" />
</head>
<body>
<div id="page">
    <div id="header">
        <p class='title'>Springfield Student Management System</p>
        <p class="current_user">You are currently logged in as : <c:out value="${sessionScope.username}"/></p>
    </div>
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/modules">Modules</a></li>
        <li><a href="index.html">Fees</a></li>
        <li><a href="index.html">Contact us</a></li>
        <li><a href="logout">Logout</a></li>
    </ul>
    <div id="pageContent">
        <p class="title">Currently Enrolled Modules</p>
        <c:if test="${not empty errorMessage}"><p style="color:red; font-weight: bold;">${errorMessage}</p></c:if>
        <br/>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Module Title</th>
                <th>Topics Covered</th>
                <th>Coordinator_ID</th>
                <th>Finished</th>
<%--                <th>Grade</th>--%>
                <th>Actions</th>
            </tr>
            <c:forEach var="module" items="${modules}">
                <c:set var="found" value="0"/>
                <tr>
                    <td><c:out value="${module.id}" /></td>
                    <td><c:out value="${module.name}" /></td>
                    <td><c:out value="${module.topics}" /></td>
                    <td><c:out value="${module.coordinator_id}" /></td>
                    <td><c:out value="${module.isFinished}" /></td>
                    <td>
                        <a href="/statistics/${module.id}/${module.name}"/>View Statistics</a>
                            <c:forEach var="userModule" items="${userModules}">
                                <c:if test = "${userModule.id == module.id}">
                                    <c:set var="found" value="1"/>

                                </c:if>
                            </c:forEach>
                            <c:if test = "${found == 0}">
                                <a href="/enrol/${module.id}"  />Enrol Module</a>
                            </c:if>
                            <c:if test = "${found == 1}">
                                <a href="/drop/${module.id}"  />Drop Module</a>
                            </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/modules/all">View All Modules</a>
    </div>
</div>
</body>
</html>
