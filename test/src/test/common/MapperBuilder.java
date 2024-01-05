package test.common;

public class MapperBuilder {
	
	public String buildMapper(String mapId, String action, String typeKey, String typeValue, String inputText) {
		
		StringBuilder mapperText = new StringBuilder();
		
		switch(action) {
		case "select" :
			mapperText.append("\t<select id=\"");
			mapperText.append(mapId);
			mapperText.append("\" ");
			mapperText.append(typeKey);
			mapperText.append("=\"");
			mapperText.append(typeValue);
			mapperText.append("\">\n");
			mapperText.append(inputText);
			mapperText.append("\n");
			mapperText.append("\t</select>\n");
			break;
		case "insert" : 
			mapperText.append("\t<insert id=\"");
			mapperText.append(mapId);
			mapperText.append("\">\n");
			mapperText.append(inputText);
			mapperText.append("\n");
			mapperText.append("\t</insert>\n");
			break;
		case "update" :
			mapperText.append("\t<update id=\"");
			mapperText.append(mapId);
			mapperText.append("\">\n");
			mapperText.append(inputText);
			mapperText.append("\n");
			mapperText.append("\t</update>\n");
			break;
		case "delete" :
			mapperText.append("\t<delete id=\"");
			mapperText.append(mapId);
			mapperText.append("\">\n");
			mapperText.append(inputText);
			mapperText.append("\n");
			mapperText.append("\t</delete>\n");
			break;
		}
		
		mapperText.append("</mapper>");
		
//		mapperText.append("\t<entry key=\"");
//		mapperText.append(mapId);
//		mapperText.append("\">\n");
//		mapperText.append(inputText);
//		mapperText.append("\n");
//		mapperText.append("\t</entry>\n");
//		
//		mapperText.append("</properties>");
		
		
		return mapperText.toString();
	}
}
