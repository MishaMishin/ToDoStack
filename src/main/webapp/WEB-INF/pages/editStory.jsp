<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>

<spring:url value="/story/save" var="saveStoryUrl" />
<form:form id="storyForm" action="${saveStoryUrl}" commandName="storyCommand">
    <form:hidden path="id"/>
    <form:input path="name"/>
</form:form>

<a href="#" onclick="$('#storyForm').submit()">Save</a>

</body>
</html>
