package test.account.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import test.account.model.vo.Account;
import test.common.JDBCTemplate;

public class AccountDao {
	
	private static AccountDao instance;
	
	private AccountDao() {
		
	}
	
	public static AccountDao getInstance() {
		
		if(instance == null) {
			instance = new AccountDao();
		}
		
		return instance;
	}
	

	//계좌 조회
	public ArrayList<Account> selectAccount(SqlSession sqlSession, int memberNo) {
		
		return (ArrayList)sqlSession.selectList("mybatis.selectAccount", memberNo);
	}
	
	
	
	
//	private Properties prop;
//
//	public AccountDao() {
//		
//		prop = new Properties();
//		
//		String filePath = AccountDao.class.getResource("/resources/sql/member_mapper.xml").getPath();
//		
//		try {
//			prop.loadFromXML(new FileInputStream(filePath));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public ArrayList<Account> selectAccount(int memberNo, Connection conn) {
//		
//		ArrayList<Account> accounts = new ArrayList<>();
//		ResultSet rset = null;
//		PreparedStatement pstmt = null;
//		
//		String sql = prop.getProperty("selectAccount");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, memberNo);
//			
//			rset = pstmt.executeQuery();
//			
//			while(rset.next()) {
//				accounts.add(new Account(rset.getInt("MEMBER_NO"),
//										 rset.getInt("BANK_CODE"),
//										 rset.getString("ACCOUNT_NUMBER"),
//										 rset.getInt("BALANCE")));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//		
//		return accounts;
//	}
}
