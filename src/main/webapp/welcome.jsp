<%@ page import="com.javarush.model.QuestInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/main.css" rel="stylesheet">
    <title>Welcome</title>
</head>
<body>
<h1>Гра-квест</h1>
<p class="quest-description"><%= QuestInfo.getInstance().getQuestDescription() %></p>

<form action="quest" method="get" id="questForm">
    <input type="hidden" name="newGame" value="true">
    <div>
        <label for="playerName">Введіть ім'я:</label>
        <input type="text" id="playerName" name="playerName">
        <p id="nameError" class="error-message">Поле не може бути пустим.</p>
    </div>
    <div class="button-container">
        <button type="submit" id="startButton">Розпочати</button>
    </div>
</form>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        let form = document.getElementById("questForm");
        let playerNameInput = document.getElementById("playerName");
        let errorMessage = document.getElementById("nameError");

        form.addEventListener("submit", function(event) {
            if (playerNameInput.value.trim() === "") {
                event.preventDefault();
                playerNameInput.classList.add("input-error");
                errorMessage.style.display = "block";
            } else {
                errorMessage.style.display = "none";
                playerNameInput.classList.remove("input-error");
            }
        });
    });
</script>
</body>
</html>