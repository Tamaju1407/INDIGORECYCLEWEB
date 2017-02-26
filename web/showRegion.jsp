
<%--
  Created by IntelliJ IDEA.
  User: GrupoUTP
  Date: 18/02/2017
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Region Information</title>
</head>
<body>
    <h1>Region Information</h1>
    <p>Region Id: <c:out value="${region.id}"/> </p>
    <p>Region Name: <c:out value="${region.name}"/> </p>
    <jsp:include page="_regions_footer.jsp"/>
</body>
</html>
