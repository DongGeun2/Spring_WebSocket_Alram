package kr.or.bit.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.bit.dao.MemberDao;
import kr.or.bit.dto.Member;
import kr.or.bit.dto.Message;

@Service
public class MemberService {
	@Autowired
	private SqlSession sqlsession;
	
	//회원가입
	public int insertMember(Member member) {
		int result = 0;
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		try {
			result = dao.insertMember(member);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("회원가입DB : " + e.getMessage());
		}
		return result;
	}
	
	//회원리스트
	public List<Member> listMember() {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		List<Member> listmember = null;
		try {
			listmember = dao.listMember();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("회원리스트DB : " + e.getMessage());
		}
		return listmember;
	}
	
	//쪽지저장
	public int insertMessage(Message message) {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = 0;
		try {
			result = dao.insertMessage(message);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("쪽지저장DB : " + e.getMessage());
		}
		return result;
	}
	
	
	//쪽지건수
	public int getmsgcount(String userid) {
		int result = 0;
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		try {
			result = dao.getmsgcount(userid);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("쪽지건수DB : " + e.getMessage());
		}
		return result;
	}
	
	//쪽지보기
	public List<Message> listMessage(String userid){
		List<Message> msglist = null;
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		try {
			msglist = dao.listMessage(userid);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("쪽지보기DB : " + e.getMessage());
		}
		return msglist;
	}

	//쪽지확인
	public int updateMessage(String userid) {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = 0;
		try {
			result = dao.updateMessage(userid);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("쪽지확인DB : " + e.getMessage());
		}
		return result;
	}
}
