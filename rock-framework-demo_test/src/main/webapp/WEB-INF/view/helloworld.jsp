<%--
  Created by IntelliJ IDEA.
  User: rocklct
  Date: 2017/9/20
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<h1>
    Hello World <b><%= request.getAttribute("name") %>!!</b>
    <p>
        <%=request.getAttribute("content")%>
    </p>
</h1>
</body>
</html>
