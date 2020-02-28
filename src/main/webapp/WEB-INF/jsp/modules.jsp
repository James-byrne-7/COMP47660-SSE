<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html>
<head>
    <title>Springfield Student Management System</title>
    <link rel="stylesheet" type="text/css" href="home.css" />
</head>
<body>
<div id="page">
    <div id="header">
        <p class='title'>Springfield Student Management System</p>
    </div>
    <ul>
        <li><a href="index.html">Home</a></li>
        <li><a href="modules">Registration</a></li>
        <li><a href="index.html">Fees</a></li>
        <li><a href="index.html">Contact us</a></li>
        <li><a href="logout">Logout</a></li>
    </ul>
    <div id="pageContent">
        <p class="title">Currently Enrolled Modules</p>
        <br/>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Module Title</th>
                <th>Topics Covered</th>
                <th>Module Co-ordinator</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="module" items="${listModules}">
                <tr>
                    <td><c:out value="${module.id}" /></td>
                    <td><c:out value="${module.name}" /></td>
                    <td><c:out value="${module.topics}" /></td>
                    <td><c:out value="${module.coordinator}" /></td>
                    <td>
                        <a href="/statistic/${module.id}"/>View Statistics</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/drop/${module.id}"  />Drop Module</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
