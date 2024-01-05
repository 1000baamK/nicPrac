package test.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

public class Crypto {
	
	public static void main(String[] args) {
		
		String a = "hell";
		
		//MD만
		String enco = encodeToBase(a, false);
		
		//MD와 base
		String enen = encodeToBase(a, true);
		
		//MD와 base 인코딩한거 다시 MD로
		String deco = decodeToBase(enen);
		
		System.out.println("hello를 MD5만 인코딩 : "+enco);
		System.out.println("hello를 MD5와 Base64 인코딩 : "+enen);
		System.out.println("MD와 Base인코딩한것 Base만 디코딩 : "+deco);
		
		
	}

	public static String encodeToBase(String pwd, boolean value) {
		
		MessageDigest md = null;
		String encoPwd = null;
		
		try {
			//MD5 알고리즘으로 단방향 암호화
			md = MessageDigest.getInstance("MD5");
			md.update(pwd.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if(value == true) {
			//base64까지
			//Base64로 인코딩
			encoPwd = Base64.getEncoder().encodeToString(md.digest());
		}else {
			//md5만
			byte[] byteData = md.digest();
			StringBuffer sb = new StringBuffer();
			for(int i=0; i< byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			encoPwd = sb.toString();
			
		}
		
		return encoPwd;
	}
	
	public static String decodeToBase(String pwd) {
		
		byte[] changedPwd = Base64.getDecoder().decode(pwd.getBytes());
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i< changedPwd.length; i++) {
			sb.append(Integer.toString((changedPwd[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		String decoPwd = sb.toString();
		
		return decoPwd;
	}
}
