package kr.or.bit.dto;

public class Message {
	private String m_to; //발신자
	private String m_from; //수신자
	private String msg; //쪽지내용
	private String m_check; //read여부
	
	public Message() {
	}

	public Message(String m_to, String m_from, String msg) {
		super();
		this.m_to = m_to;
		this.m_from = m_from;
		this.msg = msg;
	}
	
	public String getM_to() {
		return m_to;
	}
	public void setM_to(String m_to) {
		this.m_to = m_to;
	}
	public String getM_from() {
		return m_from;
	}
	public void setM_from(String m_from) {
		this.m_from = m_from;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getM_check() {
		return m_check;
	}
	public void setM_check(String m_check) {
		this.m_check = m_check;
	}
	
	@Override
	public String toString() {
		return "Message [m_to=" + m_to + ", m_from=" + m_from + ", msg=" + msg + ", m_check=" + m_check + "]";
	}
}
