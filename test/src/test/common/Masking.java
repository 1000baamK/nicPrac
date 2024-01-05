package test.common;

public class Masking {

	
	//계좌번호 마스킹
	public static String AccountMasking(String accountNumber) {

		String[] parts = accountNumber.split("-");
		
		String lastPart = parts[parts.length-1];
		
		String mask = "";
		
		//붙일 마스크
		for(int i=0; i<lastPart.length(); i++) {
			mask += "*";
		}
		
		StringBuffer sb = new StringBuffer();
		
		//마스킹영역아닌부분 붙이고
		for(int i=0; i<parts.length-1; i++) {
			sb.append(parts[i])
			  .append("-");
		}
		//끝나면 마스크도 붙이기
		sb.append(mask);
		
		return sb.toString();
	}
}
