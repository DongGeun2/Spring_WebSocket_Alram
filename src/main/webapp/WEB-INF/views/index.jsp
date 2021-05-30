<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="se"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>알림</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			connect();
		})
		
		var wsocket;
		
		function connect() {
			wsocket = new WebSocket("ws://192.168.0.19:8090/Alram/chat-ws");
			wsocket.onopen = onOpen;
			wsocket.onmessage = onMessage;
			wsocket.onclose = onClose;
		}
		function disconnect() {
			wsocket.close();
		}
		
		function onOpen(evt) {
			console.log("onOpen(evt)");
		}
		
		function onMessage(evt) {
			var data = evt.data;
			appendMessage(data);
		}
		
		function onClose(evt) {
			
		}
		

		function appendMessage(msg) {
			console.log(msg)
			alert("msg : " + msg);
			
		}
		
	</script>
<style>

</style>
</head>
<body>
	<se:authentication property="name" var="loginuser"  />
	<a href = "${pageContext.request.contextPath}/message.do">쪽지보내기</a>
	<%-- <a href = "${pageContext.request.contextPath}/check.do?userid=${loginuser}">쪽지 확인하기</a> --%>
	
	<se:authorize access="!hasRole('ROLE_USER')">
		<a href="${pageContext.request.contextPath}/login.do">로그인</a>
		<a href = "${pageContext.request.contextPath}/join.do">회원가입</a>
	</se:authorize>
	
	<se:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
		<a href="${pageContext.request.contextPath}/logout">(${loginuser})로그아웃</a>
	</se:authorize>
	

	
</body>
</html>