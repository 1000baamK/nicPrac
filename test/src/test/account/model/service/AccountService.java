package test.account.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import test.account.model.dao.AccountDao;
import test.account.model.vo.Account;
import test.common.SqlSessionTemplate;

public class AccountService {
	
	private AccountDao accountDao = AccountDao.getInstance();

	//계좌 조회
	public ArrayList<Account> selectAccount(int memberNo) {
		
		SqlSession sqlSession = SqlSessionTemplate.getSqlSession();
		
		ArrayList<Account> accounts = accountDao.selectAccount(sqlSession, memberNo);
		
		sqlSession.close();
		
		return accounts;
	}
}
