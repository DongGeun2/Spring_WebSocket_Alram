<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림:로그인</title>
</head>
<body>
	<c:if test="${param.error != null}">
		로그인실패<br>
		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
			이유 : <c:out value="SPRING_SECURITY_LAST_EXCEPTION.message" />
		</c:if>
	</c:if>
	<!--  
		Spring security 인증 처리 프로세스는 자동화..
		1. action='/SpringMVC_Basic03_Sts_Mybatis_Security/login'
		2. action="${pageContext.request.contextPath}/login"
		3. c:url value="/login" var="loginurl" action="${loginurl}"
	-->
	<form action="${pageContext.request.contextPath}/login" method="post">
		<input type="text" name="username" placeholder="ID">
		<input type="text" name="password" placeholder="PASSWARD">
		<input type="submit" value="로그인">
	</form>
	
	<a href="${pageContext.request.contextPath}/join.do">회원가입</a>
</body>
</html>