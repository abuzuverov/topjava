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
<form name="mealAddform" method="post" action="meals?action=addSubmit">
    <table>
        <tr>
            <td>Дата/Время</td>
            <td><input type="datetime-local" name="datetime" id="datetime"></td>
        </tr>
        <tr>
            <td>Описание</td>
            <td><input type="text" name="description" id="description"></td>
        </tr>
        <tr>
            <td>Калории</td>
            <td><input type="number" name="calories" id="calories"></td>
        </tr>
    </table>
    <input type="submit" value="Добавить">
</form>
</body>
</html>
