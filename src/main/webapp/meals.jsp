<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Meals</title>
    <style>
        * {
            font-family: "Open Sans", Arial, Helvetica, sans-serif;
        }
        table, th, td {
            border: 1px solid #ccc;
            padding: 6px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th {
            font-size: 14px;
            background: #f0f0f0;
        }
        td {
            font-size: 14px;
        }
        .tbr:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Action</th>
    </tr>
    <c:if test="${!empty meals}" >
        <c:forEach var="meal" items="${meals}">
            <c:choose>
                <c:when test="${meal.isExceed()}">
                    <tr class="tbr" style="color: red">
                </c:when>
                <c:otherwise>
                    <tr class="tbr" style="color: green">
                </c:otherwise>
            </c:choose>
                <td>${meal.getDateTime().toLocalDate()} ${meal.getDateTime().toLocalTime()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>