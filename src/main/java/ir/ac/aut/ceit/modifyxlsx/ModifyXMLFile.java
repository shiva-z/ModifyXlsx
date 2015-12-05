package ir.ac.aut.ceit.modifyxlsx; /**
 * @author Shiva Zamani
 * @author Parham Alvani
 */

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModifyXMLFile {

	public static void main(String argv[]) {
		try {
			String filepath = "C:\\Users\\Shiva\\Documents\\AUT\\C_TA\\ModifyXMLFile\\t\\xl\\sharedStrings.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			String filepath1 = "C:\\Users\\Shiva\\Documents\\AUT\\C_TA\\ModifyXMLFile\\std.xml";
			DocumentBuilderFactory docFactory1 = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder1 = docFactory1.newDocumentBuilder();
			Document docStudents = docBuilder1.parse(filepath1);

			NodeList users = doc.getElementsByTagName("t");
			NodeList std_username = docStudents.getElementsByTagName("username");
			NodeList std_ID = docStudents.getElementsByTagName("ID");

			Node usr;
			for (int i = 0; i < users.getLength(); i++) {
				usr = users.item(i);
				for (int j = 0; j < std_username.getLength(); j++) {
					if (std_username.item(j).getTextContent().equals(usr.getTextContent())) {
						usr.setTextContent(std_ID.item(j).getTextContent());
						break;
					}
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
