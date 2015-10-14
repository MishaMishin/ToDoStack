<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<body>

<spring:url value="/tasks" var="mainMenuUrl" />
<a href="${mainMenuUrl}">Go to main menu</a>
<table>
    <caption>${story.name}</caption>
    <th>Task name</th>
    <th>Created</th>

    <c:forEach items="${story.tasks}" var="task" varStatus="index">

        <tr>
            <td>
                    ${task.name}
            </td>
            <td>
                <fmt:formatDate value="${task.createdAt.time}" type="date" pattern="yyyy-MM-dd" />
            </td>
        </tr>

    </c:forEach>

</table>

<spring:url value="/task/create" var="createTaskUrl">
    <spring:param name="storyId" value="${story.id}" />
</spring:url>
<br/><a href="${createTaskUrl}">Create task</a>

</body>
<head>
    <title></title>
</head>
</html>
