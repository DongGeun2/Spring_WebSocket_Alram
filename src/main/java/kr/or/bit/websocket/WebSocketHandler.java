package kr.or.bit.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.bit.dto.Message;
import kr.or.bit.service.MemberService;


// @Configuration 빈 객체 자동생성 함수.
@Configuration 
public class WebSocketHandler extends TextWebSocketHandler{
	
	@Autowired
	private MemberService service;
	
	private static Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();

	private void log(String msg) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = simple.format(new Date());
		System.out.println(date + " : " + msg);
	}
	
	/*
	 TextWebSocketHandler 를 상속하면
	 
	 1. afterConnectionEstablished - 연결
	 2. afterConnectionClosed - 연결해제
	 3. handleTextMessage - 데이터 전송
	 4. handleTransportError - 예외시 발생 함수
	 
 	 4가지 기능이있는 함수를 재정의 할 수 있다.
	 
	 */
	
	
	//연결
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		log(session.getPrincipal()+ ""); // 현재 로그인된 session의 정보 값을 가져 올 수 있다
		log(session.getPrincipal().getName() + " 접속"); // session 의 정보 값 안에서 그 이름을 가지고 올 수 있다.
		log(session.toString());  // 웹소켓의 id와 주소값에 대한 정보를 확인 할 수 있다.
		users.put(session.getPrincipal().getName(), session); //userid 와 session 저장
		
	}	
	
	
	//연결해제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		if(session.getPrincipal() != null) {
			if(users.containsKey(session.getPrincipal().getName())) { // 해당 키(접속한 아이디)가 있으면 
				users.remove(session.getPrincipal().getName()); //연결해제된 id 삭제
				log(session.getPrincipal().getName() + " 해제");
			}
		}
	}
	
	//데이터 전송
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		
		
			String sendid = message.getPayload().split(",")[1];  // getPayload()를 호출하면 전송한 메시지를 확인할 수 잇습니다.
			
			Message savemsg = new Message(message.getPayload().split(",")[0], message.getPayload().split(",")[1], message.getPayload().split(",")[2]);
			// DB에 저장할 메세지를 만들어준다, 보낸이, 받는이, 메시지 순으로 split 해준다.
			
			service.insertMessage(savemsg);
			
			int result = service.getmsgcount(sendid);
			System.out.println(result);
			
			// 유저의 키값이 해당 아이디와 같다면 
			if(users.containsKey(sendid)) {
				TextMessage msg = new TextMessage("수신된 쪽지 : " + result + "건");
				users.get(sendid).sendMessage(msg);
				
				
				log(sendid + " / " + message.getPayload() + " / " + msg.getPayload());
			}
			
			System.out.println("fromid : " + sendid);
			System.out.println(message.getPayload());
		
	}
	
	//연결에 문제 발생시
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{
		log(session.getPrincipal().getName() + " / " + exception.getMessage());
	}

}
