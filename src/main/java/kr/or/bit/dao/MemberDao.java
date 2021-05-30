package kr.or.bit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.bit.dto.Member;
import kr.or.bit.dto.Message;

public interface MemberDao {
	
	//회원가입
	public int insertMember(Member member) throws ClassNotFoundException, SQLException;
	
	//회원정보조회
	public List<Member> listMember() throws ClassNotFoundException, SQLException;
	
	//쪽지저장
	public int insertMessage(Message message) throws ClassNotFoundException, SQLException;
	
	//쪽지 건수 조회
	public int getmsgcount(String userid) throws ClassNotFoundException, SQLException;
	
	//쪽지 보기
	public List<Message> listMessage(String userid) throws ClassNotFoundException, SQLException;
	
	//쪽지확인update
	public int updateMessage(String userid) throws ClassNotFoundException, SQLException;
}
