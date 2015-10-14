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

<spring:url value="/task/save" var="saveTaskUrl" />
<form:form id="taskForm" action="${saveTaskUrl}" commandName="taskCommand">
    <form:hidden path="id" />
    <%--<form:hidden path="createdAt"/>--%>

    <table>
        <caption>
            <c:choose>
                <c:when test="${not empty taskCommand.id}">
                    Edit task
                </c:when>
                <c:otherwise>
                    Create task
                </c:otherwise>
            </c:choose>
        </caption>
        <tr>
            <td>
                Name
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                Story
            </td>
            <td>
                <form:select path="storyId" items="${stories}" itemValue="id" itemLabel="name" />
            </td>
        </tr>
        <tr>
            <td>
                Deadline
            </td>
            <td>
                <%--<form:input path="deadline"/>--%>
            </td>
        </tr>
    </table>
</form:form>

<spring:url value="/task/${taskCommand.id}/delete" var="deleteTaskUrl" />

<c:if test="${not empty taskCommand.id}">
    <a href="${deleteTaskUrl}" onclick="return confirm('DELETE ?');">Delete task</a>
    <spring:url value="/task/${taskCommand.id}/detach" var="detachFromStoryUrl" />
    <br/><a href="${detachFromStoryUrl}" onclick="return confirm('Are you sure you want to detach task?')">Detach from story</a>
</c:if>
<br/><a href="#" onclick="$('#taskForm').submit()">Save</a>
<spring:url value="/tasks" var="taskListUrl" />
<br/><a href="${taskListUrl}">Go to main menu</a>

</body>
</html>
