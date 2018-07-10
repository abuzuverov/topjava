<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добавление еды</title>
    <style>
        * {
            font-family: "Open Sans", Arial, Helvetica, sans-serif;
        }
        td {
            font-size: 14px;
            padding: 6px;
        }
    </style>
</head>
<body>
<h2>Добавление еды</h2>
<a href="meals">Назад</a>
<form method="post" action="meals">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td>Дата/Время</td>
            <td><input type="datetime-local" name="dateTime" value="${meal.dateTime}" required></td>
        </tr>
        <tr>
            <td>Описание</td>
            <td><input type="text" name="description" id="description" value="${meal.description}" required></td>
        </tr>
        <tr>
            <td>Калории</td>
            <td><input type="number" name="calories" id="calories" value="${meal.calories}" required></td>
        </tr>
    </table>
    <button onclick="window.history.back()" type="button">Отмена</button>
    <button type="submit">Сохранить</button>
</form>
</body>
</html>
