<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>쪽지확인</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			connect();
		})
		
		var wsocket;
		
		function connect() {
			wsocket = new WebSocket(
					"ws://192.168.0.33:8090/Alram/chat-ws");
			wsocket.onopen = onOpen;
		}
		
		function disconnect() {
			wsocket.close();
		}
		
		function onOpen(evt) {
			send();
		}
		
		function send() {
			wsocket.send("view");
		}

		
	</script>
</head>
<body>
	<a href="${pageContext.request.contextPath}/index.do">홈으로</a>
	<table>
		<thead>
			<tr>
				<td>보낸사람</td>
				<td>메시지</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="message" items="${msglist}">
				<tr>
					<td>${message.m_to}</td>
					<td>${message.msg}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>