<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finish</title>
</head>
<body>

<p>Результат: <%= request.getAttribute("quest-result") %></p>
<h1>Статистика</h1>
<p>Ім'я гравця: <%= session.getAttribute("player-name") %></p>
<p>Разів зіграно: <%= session.getAttribute("games-played") %></p>
<p>Перемоги: <%= session.getAttribute("wins") %></p>
<p>Поразки: <%= session.getAttribute("losses") %></p>
<button onclick="window.location='/quest?newGame=true'">Розпочати знову</button>

</body>
</html>
