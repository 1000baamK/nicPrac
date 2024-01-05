package test.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	
	public static SqlSession getSqlSession() {
		// getSqlSession의 역할
		// XML 파일을 연결하여 XML 파일 정보를 바탕으로 DB와 연결하고, 해당 Connection(SqlSession)을 리턴하는 역할

		SqlSession session = null;
		String resource = "resources/sqlConfig.xml";

		try {
			InputStream is = Resources.getResourceAsStream(resource);

			// SqlSession을 생성하려면 Mybatis에서는 팩토리 패턴이라는 것을 사용해서 객체를 생성
			// 팩토리 패턴이라는 것은 바로 생성하는게 아니라 특정 객체를 셍성하기 위한 팩토리를 먼저 만들고,
			// 그 뒤에 해당 특정 객체를 생성하는 방법

			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);

			session = factory.openSession(false);
			// openSession 이라는메소드는 세션을 가져오는 메소드
			// 세션을 생성하고 가져올때,
			// 인자 값 true 혹은 빈 인자값 : AutoCommit이 활성화된 상태
			// 인자 값 false: AutoCommit을 비활성화된 상태

		} catch (IOException e) {
			e.printStackTrace();
		}

		return session;
	}

}
