<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="jetty-dir.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<h1>Wokao world!</h1>
<c:if test="${ not empty name}">
hello : ${name}
</c:if>
hello : ${name}
</body>
</html>