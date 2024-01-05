package test.common;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Regex {
	
	static Logger log = LogManager.getLogger(Regex.class);
	//정규표현식
	private static String idPatt = "^[a-zA-Z0-9]{5,14}$"; //영문, 숫자 5~14자리
	private static String pwdPatt = "^[a-zA-Z0-9!@#$%^&*()]{8,16}$"; //영문,숫자,특수문자 8~16자리
	private static String emailPatt = "^[a-zA-Z0-9]([-_\\.]?[a-zA-Z0-9])*@[a-zA-Z0-9]([-_\\.]?[a-zA-Z0-9])*\\.[a-zA-Z]{2,3}$";
	private static String namePatt = "^[가-힣]{2,4}$"; //한글 2~4자리;
	
	
	//인풋값들어오면 확인
	public static boolean regPass(String id, String pwd, String email, String name) {
	
		boolean result = false;
		
		
		if(Pattern.matches(idPatt, id) &&
		   Pattern.matches(pwdPatt, pwd) &&
		   Pattern.matches(emailPatt, email) &&
		   Pattern.matches(namePatt, name)) {
			result = true;
			
		}
		
//		Pattern p = Pattern.compile(idPatt);
//		Matcher m = p.matcher(id);
//		
//		log.debug("{}", m.matches());
		return result;
	}
	

}
