package test.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileManager {
	
	private Logger log = LogManager.getLogger(FileManager.class);
	
	public void updateQuery (String addStr) throws IOException {
		
		if(addStr != null) {
			
			String text ="";
			String line;
			
			File file = new File("C:\\\\Users\\\\USER\\\\Desktop\\\\eGovFrameDev-3.10.0-64bit\\\\workspace\\\\test\\\\src\\\\resources\\\\sql\\\\query.xml");
//			File file = new File("C:\\\\Users\\\\USER\\\\Desktop\\\\eGovFrameDev-3.10.0-64bit\\\\workspace\\\\test\\\\src\\\\resources\\\\sql\\\\mapper.xml");
			
			
			BufferedReader bfr = new BufferedReader(new FileReader(file));
			
			while((line = bfr.readLine()) != null) {
				
				text += line+"\n";
			}
			
			String changedText = text.replace("</mapper>", addStr);
//			String changedText = text.replace("</properties>", addStr);
			
			log.debug("바뀐파일 - {}", changedText);
			
			bfr.close();
			
			//업데이트하고 저장수행
			BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
			bfw.write(changedText);
			
			bfw.close();
			
			log.debug("파일 등록 완료");
		
		}else {
			log.debug("hh");
		}
		
	}
	
}
