<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
    <link rel="stylesheet" href="/portal.css">
    <title>Staff: Edit Module</title>
</head>
<body>

<div class="main" align="center">
    <p class="title">Edit Module</p>
    <c:if test="${not empty errorMessage}"><div style="color:red; font-weight: bold; margin: 0;">${errorMessage}</div></c:if>
    <form action="/edit/<c:out value='${module.id}'/>" method="post">
        <input type="hidden" name="id" value="<c:out value='${module.id}' />" />
        <table>
            <tr><td>Name</td><td><input class="field" name="name" value="<c:out value='${module.name}' />"  /></td></tr>
            <tr><td>Topics</td><td><input class="field" name="topics" value="<c:out value='${module.topics}' />"  /></td></tr>
            <tr><td>coordinator</td><td><input class="field" name="coordinator_id" value="<c:out value='${module.coordinator_id}' />" /></td></tr>
            <tr><td>Finished?</td><td><input class="field" name="isFinished" value="<c:out value='${module.isFinished}' />"  /></td></tr>
            <tr><td>#Places</td><td><input class="field" name="maxStudents" value="<c:out value='${module.maxStudents}' />" /></td></tr>
        </table>
        <input class="submit" type="submit" value="Post Changes">
    </form>
</div>
</body>

</html>
