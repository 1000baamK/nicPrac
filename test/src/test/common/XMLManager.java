package test.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
//import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import com.sun.org.apache.xerces.internal.dom.DocumentTypeImpl;

import test.query.model.vo.Query;

public class XMLManager {
	
	private static Logger log = LogManager.getLogger(XMLManager.class);
	
	
	
	//조회 메소드
	public static ArrayList<Query> selectXML(){
		
		ArrayList<Query> qList = new ArrayList<>();
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("C:\\Users\\USER\\Desktop\\eGovFrameDev-3.10.0-64bit\\workspace\\test\\src\\resources\\sql\\sql.xml"));
			
			Element mappers = doc.getDocumentElement();
			
			NodeList mapperList = mappers.getChildNodes();
			
			System.out.println(mapperList.getLength());
			
			for(int i=0; i<mapperList.getLength(); i++) {
				Node node = mapperList.item(i);
				
				//줄바꿈이 노드로 인식되어서 걸러주기 위함
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					//node상태론 속성값을 뽑지 못해 Element로 캐스팅하여 뽑아낸다
					Element mapper = (Element) node;
					String mappingId = mapper.getAttribute("id");
//					String sql = unwrapCdata(mapper.getElementsByTagName("sql").item(0).getTextContent());
//					String description = unwrapCdata(mapper.getElementsByTagName("description").item(0).getTextContent());
					String sql = mapper.getElementsByTagName("sql").item(0).getTextContent();
					String description = mapper.getElementsByTagName("description").item(0).getTextContent();
					
					
					
//					log.debug("매핑아이디 : {}", mappingId);
//					log.debug("sql문 : {}", sql);
//					log.debug("설명 : {}", description);
					
					qList.add(new Query(mappingId,sql,description));
				}
				
				
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return qList;
	}
	
	//query문 하나 조회하기
	public static Query selectOne(String mappingId) {
		
		Query query = null;
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("C:\\Users\\USER\\Desktop\\eGovFrameDev-3.10.0-64bit\\workspace\\test\\src\\resources\\sql\\sql.xml"));
			
			Element mappers = doc.getDocumentElement();
			
			NodeList mapperList = mappers.getElementsByTagName("mapper");

	        for (int i = 0; i < mapperList.getLength(); i++) {
	            Element mapper = (Element) mapperList.item(i);
	            if (mapper.getAttribute("id").equals(mappingId)) {
	                
	            	//찾았으면 데이터 Query객체에 담아 꺼내오기
//	            	query = new Query(mappingId,
//	            					  unwrapCdata(mapper.getElementsByTagName("sql").item(0).getTextContent()),
//	            					  unwrapCdata(mapper.getElementsByTagName("description").item(0).getTextContent()));
	            	query = new Query(mappingId,
	            			mapper.getElementsByTagName("sql").item(0).getTextContent(),
	            			mapper.getElementsByTagName("description").item(0).getTextContent());
	            	
	            	
	            	
	            	
	                break;
	            }
	        } 
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		return query;
	}

	//초기 xml파일 생성 메소드
	public static int createXML() {
		
		int result = 0;
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
	        Document doc = docBuilder.newDocument();
	        
	        //내부서브셋 추가 -> 안됨..
//	        String internalSubset = "<!ELEMENT mappers (mapper+)>\r\n" + 
//	        		"	<!ELEMENT mapper (sql, description)>\r\n" + 
//	        		"	<!ATTLIST mapper id CDATA #REQUIRED>\r\n" + 
//	        		"	<!ELEMENT sql (#PCDATA)>\r\n" + 
//	        		"	<!ELEMENT description (#PCDATA)>";
//	        
//	        DocumentType doctype = docBuilder.getDOMImplementation().createDocumentType("mappers", null, null);
//	        doc.appendChild(doctype);
//	        
//	        ((DocumentTypeImpl)doctype).setInternalSubset(internalSubset);
	        
	        Element mappers = doc.createElement("mappers");
	        doc.appendChild(mappers);
	        

	        // XML 파일로 쓰기
	        saveXML(doc);
	        System.out.println("=========XML파일 생성 완료=========");
	        result = 1;
	        
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
			
		}
		
		return result;
	}
	
	//생성 메소드
	public static int insertXML(Query query) {
		
		int result = 0;
		
//		String wrapSql = query.getSql();
//		String wrapDescription = wrapCdata(query.getDescription());
		//라이브러리 내부 래핑하는 메소드가 있다.
		
		try {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	        Document doc = docBuilder.parse(new File("C:\\Users\\USER\\Desktop\\eGovFrameDev-3.10.0-64bit\\workspace\\test\\src\\resources\\sql\\sql.xml"));

	        Element mappers = doc.getDocumentElement();

	        Element mapper = doc.createElement("mapper");
	        mapper.setAttribute("id", query.getMappingId());
	        
	        Element sql = doc.createElement("sql");
//	        sql.appendChild(doc.createTextNode(wrapSql));
//	        mapper.appendChild(sql);
	        // sql에 대한 cdata 섹션 생성
	        CDATASection sqlCData = doc.createCDATASection(query.getSql());
	        sql.appendChild(sqlCData);
	        mapper.appendChild(sql);
	        
	        
            Element description = doc.createElement("description");
            // description에 대한 cdata 섹션 생성
//	        description.appendChild(doc.createTextNode(wrapDescription));
//          mapper.appendChild(description);
            CDATASection descCData = doc.createCDATASection(query.getDescription());
            description.appendChild(descCData);
            mapper.appendChild(description);

	        mappers.appendChild(mapper);

	        saveXML(doc);

	        System.out.println("=========XML 삽입 성공=========");
	        
	        result = 1;
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	    
		return result;
	}

	//삭제 메소드
	public static int deleteXML(String mappingId) {
		int result = 0;
		
	    try {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	        Document doc = docBuilder.parse(new File("C:\\Users\\USER\\Desktop\\eGovFrameDev-3.10.0-64bit\\workspace\\test\\src\\resources\\sql\\sql.xml"));

	        Element mappers = doc.getDocumentElement();
	        NodeList mapperList = mappers.getElementsByTagName("mapper");

	        for (int i = 0; i < mapperList.getLength(); i++) {
	            Element mapper = (Element) mapperList.item(i);
	            if (mapper.getAttribute("id").equals(mappingId)) {
	                mappers.removeChild(mapper);
	                System.out.println("=========XML 삭제 성공=========");
	                result = 1;
	                break;
	            }
	        }

	        saveXML(doc);

	    } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
	        e.printStackTrace();
	    }
	    
	    return result;
	}

	//수정 메소드
	public static int updateXML(Query query) {
		
		int result = 0;
		
	    try {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	        Document doc = docBuilder.parse(new File("C:\\Users\\USER\\Desktop\\eGovFrameDev-3.10.0-64bit\\workspace\\test\\src\\resources\\sql\\sql.xml"));

	        Element mappers = doc.getDocumentElement();
	        NodeList mapperList = mappers.getElementsByTagName("mapper");

	        for (int i = 0; i < mapperList.getLength(); i++) {
	            Element mapper = (Element) mapperList.item(i);
	            if (mapper.getAttribute("id").equals(query.getMappingId())) {
	            	
//	            	mapper.getElementsByTagName("sql").item(0).setTextContent(wrapCdata(query.getSql()));
	            	Node sqlNode = mapper.getElementsByTagName("sql").item(0);
	            	//node -> element형변환
	            	Element sqlEl = (Element)sqlNode;
	            	CDATASection sqlCData = doc.createCDATASection(query.getSql()); //cdata입히기
	            	sqlEl.setTextContent(""); //기존 text날리고
	            	sqlEl.appendChild(sqlCData); //cdata 넣기
	            	
	            	
//	            	mapper.getElementsByTagName("description").item(0).setTextContent(wrapCdata(query.getDescription()));
	            	Node descNode = mapper.getElementsByTagName("description").item(0);
	            	Element descEl = (Element)descNode;
	            	CDATASection descCData = doc.createCDATASection(query.getDescription());
	            	descEl.setTextContent("");
	            	descEl.appendChild(descCData);
	            	
	            	System.out.println("=========XML 수정 성공=========");
	            	result = 1;
	                break;
	            }
	        }

	        saveXML(doc);

	        
	    } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
	        e.printStackTrace();
	    }
	    
	    return result;
	}

	//최종 저장 메소드
	private static void saveXML(Document doc) throws TransformerException {
		
		try {
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//		    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
		    transformer.setOutputProperty("doctype-system", "root.dtd");
		    
		    doc.setXmlStandalone(true); // -> standalone(yes) -> 외부문서 참고 x 한다는뜻 
	
		    DOMSource source = new DOMSource(doc);
		    StreamResult result = new StreamResult(new FileOutputStream(new File("C:\\Users\\USER\\Desktop\\eGovFrameDev-3.10.0-64bit\\workspace\\test\\src\\resources\\sql\\sql.xml")));
	
		    transformer.transform(source, result);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
//	//cdata감싸기
//	private static String wrapCdata(String str) {
//		
//		StringBuffer sb = new StringBuffer();
//		
//		sb.append("<![CDATA[");
//		sb.append(str);
//		sb.append("]]>");
//		
//		return sb.toString();
//	}
//	
//	//cdata풀기
//	private static String unwrapCdata(String str) {
//		
//		String result = "";
////		System.out.println("들어온 문자열 :"+str);
//		if(str.charAt(0) == '<' && str.charAt(1) == '!') {
//		
//			String subStr = str.substring(9);
//		
//			result = subStr.substring(0, subStr.length()-3);
////			System.out.println("1번 통과");
//		}else {
//			//아니면 그대로
//			result = str;
////			System.out.println("2번 통과");
//		}
//		
//		return result;
//	}

}
