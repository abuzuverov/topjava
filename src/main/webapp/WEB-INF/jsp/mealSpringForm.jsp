<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal (Spring Form)'}</h2>
    <hr>
<%--    <spring:form modelAttribute="meal" method="post" action="submit">
        <spring:hidden path="id"/>
        <dl>
            <dt><spring:label path="dateTime">DateTime:</spring:label></dt>
            <dd>
                &lt;%&ndash;<fmt:formatDate value="${fn:formatDateTime(meal.dateTime)}" var="dateTime" pattern="yyyy-MM-dd'T'hh:mm"/>&ndash;%&gt;
                <spring:input type="datetime-local" path="dateTime"/>
            </dd>
        </dl>
        <dl>
            <dt><spring:label path="description">Description:</spring:label></dt>
            <dd><spring:input path="description"/></dd>
        </dl>
        <dl>
            <dt><spring:label path="calories">Calories:</spring:label></dt>
            <dd><spring:input path="calories"/></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </spring:form>--%>
    <form method="post" action="submit">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime:</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${meal.description}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
