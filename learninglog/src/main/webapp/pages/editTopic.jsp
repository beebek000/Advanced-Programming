<%--
  Created by IntelliJ IDEA.
  User: ig_beebek
  Date: 4/17/2026
  Time: 8:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/components/header.jsp"/>

   <form method="post" action="editTopic">
       <h2>Edit Form</h2>
       ID:
       <input type="text" name="id" value="${topicData.id}" readonly>
       <br>
       Name: <input type="text" name="name" value="${topicData.name}">
       <button>Edit</button>
   </form>
    <jsp:include page="/components/footer.jsp"/>
</head>
<body>

</body>
</html>
