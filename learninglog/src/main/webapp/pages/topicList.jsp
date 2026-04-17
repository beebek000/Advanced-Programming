<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learninglog.learninglogproject.topic.model.Topic" %>
<jsp:include page="/components/header.jsp"/>

TopicList
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>UserId</th>
        <th>CreatedDate</th>
    </tr>
    <tbody>
    <c:forEach var="t" items="${topics}">
        <tr>
            <td>${t.getId()}</td>
            <td>${t.getName()}</td>
            <td>${t.getUser_Id()}</td>
            <td>${t.getCreated_Date()}</td>
            <td>
                <a href="topic?page=edit&id=${t.getId()}">Edit</a>
                <a href="topic?page=delete&id=${t.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/components/footer.jsp"/>