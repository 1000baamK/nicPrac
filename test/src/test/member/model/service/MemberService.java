package test.member.model.service;


import java.sql.Connection;

//import org.apache.ibatis.session.SqlSession;

import test.common.JDBCTemplate;
//import test.common.SqlSessionTemplate;
import test.member.model.dao.MemberDao;
import test.member.model.vo.Member;

public class MemberService {
	
	private MemberDao memberDao = MemberDao.getInstance();
	
	
	//회원 추가
	public int insertMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = memberDao.insertMember(m, conn);
		
		if(result>0) { //작업수행이 완료되었다면 커밋
			JDBCTemplate.commit(conn);
		}else { //롤백
			JDBCTemplate.rollback(conn);
		}
		
		//conn객체 닫아주기
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	//회원가입 - 아이디 체크
	public int selectIdCheck(String memberId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = memberDao.selectIdCheck(memberId, conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	//id로 조회
	public Member selectMember(String memberId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member getMember = memberDao.selectMember(memberId, conn);
		
		JDBCTemplate.close(conn);
		
		return getMember;
	}
	

//	//회원추가 
//	public int insertMember(Member m) {
//		
//		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
//		
//		int result = memberDao.insertMember(sqlSession, m);
//		
//		if(result>0) {
//			sqlSession.commit();
//		}else {
//			sqlSession.rollback();
//		}
//		sqlSession.close();
//		
//		return result;
//	}
//	
//	//회원가입 - 아이디 체크
//	public int selectIdCheck(String memberId) {
//		
//		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
//		
//		int result = memberDao.selectIdCheck(sqlSession, memberId);
//		
//		sqlSession.close();
//	
//		return result;
//	}
//
//
//	//id로 조회
//	public Member selectMember(String memberId) {
//		
//		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
//		
//		Member loginMember = memberDao.selectMember(sqlSession, memberId);
//		
//		sqlSession.close();
//		
//		return loginMember;
//	}
}
