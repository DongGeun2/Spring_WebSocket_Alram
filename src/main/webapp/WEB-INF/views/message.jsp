<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="se"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>쪽지보내기</title>
	<style>
	#chatArea {
		width: 600px; height: 400px; overflow-y: auto; border: 1px solid black;
	}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<script type="text/javascript">
		
		var wsocket;
		function connect() {
			// 웹 소켓 주소연결
			wsocket = new WebSocket("wss://192.168.0.19:8090/Alram/chat-ws");
			wsocket.onopen = onOpen; //웹소켓 오픈
			wsocket.onmessage = onMessage;  // 웹소켓상 메시지 받아오기
			
			
			wsocket.onclose = onClose; // 웹 소켓 종료시 발생 함수
		}
		
		// 연결종료
		function disconnect() {
			wsocket.close(); // 웹소켓 연결 끊기
		}
		
		// 웹소켓 오픈시 발생할 함수
		function onOpen(evt) {
			console.log("onOpen(evt)");
			
		}
		
		// 웹소켓 오픈후 메시지를 받아올 함수
		function onMessage(evt) {
			console.log("evt :" + evt);
			var data = evt.data;
			
			// 받아온 정보를 appendMessage 라는 함수로 전달
			appendMessage(data);
		}
		
		// 받아온 정보를 받아 alert 으로 띄어줌
		function appendMessage(msg) {
			console.log(msg);
			alert("msg : " + msg);
		}
		
		
		// 웹소켓 종료시 실행 함수
		function onClose(evt) {
			console.log("onClose(evt)");
		}
		
		
		// 웹소켓 메시지 보내기
		function send() {
			let m_to = $('#m_to').val(); // 받는 사람
			
			let m_from = $('#m_from').val(); // 보내는 사람
			
			let msg = $('#msg').val(); // 메시지
			
			
			// 보내는사람,받는사람,메시지로 웹소켓에 전송
			wsocket.send(m_from + "," + m_to + "," + msg);    
			
			$('#msg').val("");
		}
		
		
		
		// 로딩이 끝나면 함수 실행
		$(document).ready(function() {
			connect();
			$('#sendBtn').click(function() { send(); 		});
			
		})
		
		
		
		
	</script>
</head>
<body>
	<se:authentication property="name" var="loginuser"  />
		보내는사람 : ${loginuser} <input type="hidden" name="m_from" id="m_from" value="${loginuser}">
		받는사람 :
		<select name="m_to" id="m_to">
			<c:forEach var="member" items="${listmember}">
				<option value="${member.userid}">${member.name}</option>
			</c:forEach>
		</select> 
		<input type="text" id="msg" name="msg">
		<input type="button" id="sendBtn" value="전송">
		<br>
		
	 	<div style="border: 1px solid black; width: 50%; padding: 10px; margin: 10px;">
	 		<p>받은 쪽지</p>
	 		<hr>
	 		<c:forEach var="message" items="${msglist}">
				<tr>
					<td>${message.m_to}</td> 
					<td>_ ${message.msg}</td>
					<br>
				</tr>
			</c:forEach>
	 	</div>
		
		<a href="${pageContext.request.contextPath}/index.do">홈으로</a>
		<button onclick="disconnect()">웹소켓 종료</button>
		
</body>

<script type="text/javascript">
	

</script>
</html>