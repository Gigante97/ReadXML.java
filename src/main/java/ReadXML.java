import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.commons.io.output.TeeOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File file = new File("new2.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        NodeList nodeList = document.getElementsByTagName("product");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            list.add(element.getElementsByTagName("ki").item(0).getChildNodes().item(0).getNodeValue());

        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        OutputStream teeStream = new TeeOutputStream(System.out, buffer);
        System.setOut(new PrintStream(teeStream));
        System.out.println(list);
        try(OutputStream fileStream = new FileOutputStream("cis.txt")) {
            buffer.writeTo(fileStream);
        }


    }
}