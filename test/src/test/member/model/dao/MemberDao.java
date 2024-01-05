package test.member.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import test.common.JDBCTemplate;
import test.member.model.vo.Member;

public class MemberDao {
	
	private static MemberDao instance;
	private Document doc;
	
	private MemberDao() {
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			
			doc = docBuilder.parse(new File("C:\\Users\\USER\\Desktop\\eGovFrameDev-3.10.0-64bit\\workspace\\test\\src\\resources\\sql\\sql.xml"));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//싱글턴
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDao();
		}
		
		return instance;
	}
	
	//쿼리문 가져오기
	private String getSqlQuery(String string) {
		
		String result = "";
		
		Element mappers = doc.getDocumentElement();
        NodeList mapperList = mappers.getElementsByTagName("mapper");

        for (int i = 0; i < mapperList.getLength(); i++) {
            Element mapper = (Element) mapperList.item(i);
            if (mapper.getAttribute("id").equals(string)) {
            	
            	result = mapper.getElementsByTagName("sql").item(0).getTextContent();
            	
                break;
            }
        }
		
		return result;
	}

	
	//회원 추가
	public int insertMember(Member m, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = getSqlQuery("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getName());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}


	public int selectIdCheck(String memberId, Connection conn) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = getSqlQuery("selectIdCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) { //쿼리결과가 나오면
				result++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	
	public Member selectMember(String memberId, Connection conn) {
		
		Member getMember = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = getSqlQuery("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				getMember = new Member(rset.getInt("MEMBER_NO"),
										rset.getString("MEMBER_ID"),
										rset.getString("MEMBER_PWD"),
										rset.getString("EMAIL"),
										rset.getString("NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return getMember;
	}
	
//	public int insertMember(SqlSession sqlSession, Member m) {
//	
//		return sqlSession.insert("mybatis.insertMember", m);
//	}
//
//	public int selectIdCheck(SqlSession sqlSession, String memberId) {
//	
//		return sqlSession.selectOne("mybatis.selectIdCheck", memberId);
//	}
//
//	public Member selectMember(SqlSession sqlSession, String memberId) {
//	
//		return sqlSession.selectOne("mybatis.selectMember", memberId);
//	}

}
