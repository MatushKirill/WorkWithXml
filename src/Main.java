import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        InputStream is = new FileInputStream(new File("Untitled1.xml"));
        SaxParser parser = new SaxParser();
        saxParser.parse(is, parser);
        List<Tariff> tariffs = parser.getTariffs();
        if (parser.validateXMLSchema("Untitled3.xsd","Untitled1.xml")==true) {
            parser.convertToHTML("Untitled1.xsl","Untitled1.xml");
            for (Tariff f : tariffs) {
                System.out.println(f.getName());
                System.out.println(f.callPrice.OntheLandlinPrice);
                System.out.println("_______________________________________");
            }
        }
    }
	}


