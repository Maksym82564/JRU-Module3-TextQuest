<%@ page import="com.javarush.model.OptionNode" %>
<%@ page import="com.javarush.model.QuestInfo" %>
<%@ page import="com.javarush.model.Option" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link href="css/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src=https://code.jquery.com/jquery-3.6.0.min.js></script>
    <title>Quest</title>
</head>

    <% int index = (int) session.getAttribute("node-index"); %>
    <% OptionNode optionNode = QuestInfo.getOptionNodes().get(index); %>
    <% List<Option> options = optionNode.getOptions(); %>

<body>
<h1><%= optionNode.getIssue() %></h1>
<form action="quest" method="post">
    <% for (int i = 0; i < options.size(); i++) { %>
    <input type = "radio" id ="<%= i %>" name = "option" value = "<%= i %>">
    <label for="<%= i %>"> <%= options.get(i).getText() %> </label><br>
    <% } %>
    <br><button type="submit">Відповісти</button>
</form>
</body>