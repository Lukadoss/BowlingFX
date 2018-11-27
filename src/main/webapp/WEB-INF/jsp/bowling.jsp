<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>

<div class="container">

    <div class="starter-template">
        <h1>Bowling</h1>
        <c:forEach items="${bowlings}" var="bowling">
            <h2>Welcome to Bowling building (id=${bowling.id}) </h2>
            <c:forEach items="${bowling.lines}" var="line">
                <span>Choose line <a href="/line/${line.id}">${line.id}</a></span><br>
            </c:forEach>
        </c:forEach>
    </div>

</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>