<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
<form method="POST" action="controller">
    <input type="hidden" name="command" value="setlanguage"/>
    <input type="submit" name="language" value="US" id="EnLangButton"/>
    <input type="submit" name="language" value="RU" id="RuLangButton"/>
</form>

</body>
</html>
