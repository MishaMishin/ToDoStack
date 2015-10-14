<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title></title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <style>
        table, th, td {
            border: 1px solid gray;
            border-collapse: collapse;
        }
        table {
            width: 100%;
        }
    </style>
</head>
<body>

<script>
    function highlightStory(obj) {
        $('[id$="-story"]').each(function() {
            if ($(this).text() == $(obj).text()) {
                var thisStoryId = $(this).attr('id');
                var numericPartOfId = thisStoryId.substring(0, thisStoryId.indexOf('-'));
                $('#' + numericPartOfId + '-task-row').css('background-color', '#d2d1d1');
            }
        });
    }
    function removeStoriesHighlight() {
        $('[id$="-task-row"]').each(function() {
            $(this).css('background', 'transparent');
        });
    }
</script>

<spring:url value="/task/create" var="createTaskUrl" />
<a href="${createTaskUrl}">Create task</a>
<spring:url value="/story/create" var="createStoryUrl"/>
<a href="${createStoryUrl}">Create story</a>

<table border="1">
    <tr>
        <th colspan="2">Name</th>
        <th>
            Created
            <spring:url value="/tasks/sort" var="createdAtAscSortUrl">
                <spring:param name="sortParam" value="createdAt"/>
                <spring:param name="sortOrder" value="asc"/>
            </spring:url>
            <spring:url value="/tasks/sort" var="createdAtDescSortUrl">
                <spring:param name="sortParam" value="createdAt"/>
                <spring:param name="sortOrder" value="desc"/>
            </spring:url>
            <a href="${createdAtAscSortUrl}">ASC</a>&NonBreakingSpace;/&NonBreakingSpace;
            <a href="${createdAtDescSortUrl}">DESC</a>
        </th>
        <th>
            Deadline
            <spring:url value="/tasks/sort" var="deadlineAscSortUrl">
                <spring:param name="sortParam" value="deadline"/>
                <spring:param name="sortOrder" value="asc"/>
            </spring:url>
            <spring:url value="/tasks/sort" var="deadlineDescSortUrl">
                <spring:param name="sortParam" value="deadline"/>
                <spring:param name="sortOrder" value="desc"/>
            </spring:url>
            <a href="${deadlineAscSortUrl}">ASC</a>&NonBreakingSpace;/&NonBreakingSpace;
            <a href="${deadlineDescSortUrl}">DESC</a>
        </th>
        <th>Story</th>
    </tr>

    <c:forEach items="${tasks}" var="task" varStatus="index">
        <tr id="${index.count}-task-row">
            <spring:url value="/task/${task.id}/edit" var="editTaskUrl"/>
            <td><a href="${editTaskUrl}">Edit</a></td>
            <%--Name--%>
            <td id="${index.count}-task">
                ${task.name}
            </td>
            <%--Created at--%>
            <td align="center">
                <fmt:formatDate value="${task.createdAt.time}" type="date" pattern="yyyy-MM-dd" />
            </td>
            <%--Deadline--%>
            <td align="center">
                <fmt:formatDate value="${task.deadline.time}" type="date" pattern="yyyy-MM-dd" />
            </td>
            <%--Story--%>
            <td id="${index.count}-story" onmouseover="highlightStory(this)" onmouseout="removeStoriesHighlight()">
                <spring:url value="/story/${task.story.id}" var="storyUrl" />
                <c:if test="${task.story.name ne 'defaultStory'}" >
                    <a href="${storyUrl}">${task.story}</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
