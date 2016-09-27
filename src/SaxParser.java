import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class SaxParser extends DefaultHandler{
	private List<Tariff> tariffs = new ArrayList<>();
    int current = 0;
    Tariff tariff;

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startDocument() throws SAXException {
        //super.startDocument();
        System.out.println("Start parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
        //super.endDocument();
        System.out.println("Parsed!");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       

        switch (qName){
            case "Tariffs": {
                current = 1; break;
            }
            case "Tariff": {
                tariff = new Tariff();
                tariff.setId(attributes.getValue(0));
                current = 2;
                break;
            }
            case "name":{
                current=3;
                break;
            }
            case "OperatorName": {
                current = 4;
                break;
            }
            case "Payroll": {
                current = 5;
                break;
            }
            case "CallPrice": {
                current = 6;
                tariff.callPrice = new Tariff.CallPrice();
                break;
            }
           
            case "myspace:WithinTheNetwork": {
                current = 7;
                break;
            }
            case "myspace:OutOfTheNetwork": {
                current = 8;
                break;
            }
            case "myspace:OnTheLandlinPhone": {
                current = 9;
                break;
            }
            case "Parameters": {
                current = 10;
                tariff.paraeters=new Tariff.Parameters();
                break;
            }
            case "ConnectinFee":{
                current=11;
                break;
            }
            case "Tariffication":{
                current=12;
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String(ch, start, length);
        switch(current){
            case 3: {
                tariff.setName(text);
                break;
            }
            case 4: {
                tariff.setOperatorName(text);
                break;

            }
            case 5: {
                tariff.setPayroll(Integer.valueOf(text));
                break;
            }
            case 7: {
                tariff.callPrice.withinTheNetwork=Integer.valueOf(text);
                break;
            }
            case 8: {
                tariff.callPrice.OutOfTheNetwork=Double.valueOf(text);
                break;
            }
            case 9: {
                tariff.callPrice.OntheLandlinPrice=Double.valueOf(text);
                break;
            }
            case 11:{
                tariff.paraeters.connectinFree=Integer.valueOf(text);
                break;
            }
            case 12:{
                tariff.paraeters.tarification=text;
                break;
            }
        }
        current = -1;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
            case "Tariff": {
                tariffs.add(tariff);
            }
    }
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
}
