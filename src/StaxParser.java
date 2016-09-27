import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.XMLConstants;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;


public class StaxParser {
    public List<Tariff> parseee(String File) {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(File);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Tariff tariff = null;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startelement = event.asStartElement();
                    if (startelement.getName().getLocalPart() == "Tariff") {
                        tariff = new Tariff();
                        Iterator<Attribute> attributes = startelement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals("id")) {
                                tariff.setId(attribute.getValue());
                            }
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals("name")) {
                            event = eventReader.nextEvent();
                            tariff.setName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart().equals("OperatorName")) {
                        event = eventReader.nextEvent();
                        tariff.setOperatorName(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("Payroll")) {
                        event = eventReader.nextEvent();
                        tariff.setPayroll(new Integer(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("CallPrice")) {
                        event = eventReader.nextEvent();
                        tariff.callPrice = new Tariff.CallPrice();
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("WithinTheNetwork")) {
                        event = eventReader.nextEvent();
                        tariff.getCallPrice().setOntheLandlinPrice(Integer.valueOf(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("OutOfTheNetwork")) {
                        event = eventReader.nextEvent();
                        tariff.getCallPrice().setOutOfTheNetwork(Double.valueOf(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("OnTheLandlinPhone")) {
                        event = eventReader.nextEvent();
                        tariff.getCallPrice().setOntheLandlinPrice(Double.valueOf(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("Parameters")) {
                        event = eventReader.nextEvent();
                        tariff.paraeters = new Tariff.Parameters();
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("ConnectinFee")) {
                        event = eventReader.nextEvent();
                        tariff.paraeters.connectinFree = Integer.valueOf(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals("Tariffication")) {
                        event = eventReader.nextEvent();
                        tariff.paraeters.tarification = event.asCharacters().getData();
                        continue;
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart() == ("Tariff")) {
                        tariffs.add(tariff);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return tariffs;
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        boolean b=true;
        try {
            SchemaFactory factory
                    = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            b=false;
            System.out.println("Exception: " + e.getMessage());
            return b;
        } catch (SAXException e1) {
            b=false;
            System.out.println("SAX Exception: " + e1.getMessage());
            return b;
        }
        return b;

    }

    public void convertToHTML(String stylesheet, String sourceId) throws IOException, TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        File pricesHTML = new File("Tariff.html");
        FileOutputStream os = new FileOutputStream(pricesHTML);
        Transformer transformer = tFactory.newTransformer(new StreamSource(stylesheet));
        transformer.transform(new StreamSource(sourceId), new StreamResult(os));
        File htmlFile = new File("Tariff.html");
        Desktop.getDesktop().browse(htmlFile.toURI());
    }


    public static void main(String[] args) throws IOException, TransformerException {
        StaxParser staxParser=new StaxParser();
        List<Tariff> list;
        list=staxParser.parseee("Untitled1.xml");
        if (staxParser.validateXMLSchema("Untitled3.xsd","Untitled1.xml")==true) {
            staxParser.convertToHTML("Untitled1.xsl","Untitled1.xml");
            System.out.println();
            for (Tariff t : list) {
                System.out.println(t.getName());
                System.out.println(t.callPrice.getOutOfTheNetwork());
                System.out.println(t.paraeters.connectinFree);
                System.out.println("_________________");
            }
        }



    }

}