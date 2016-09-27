import org.jdom2.JDOMException;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Кирилл on 08.06.2016.
 */
public class Main1 {
    public static void main(String[] args) throws IOException, TransformerException, JDOMException {
        SaxParser parser = new SaxParser();
        DOMParser dom=new DOMParser();
        List<Tariff> tariffs=dom.DOM();


        if (parser.validateXMLSchema("Untitled3.xsd", "Untitled1.xml") == true) {
            parser.convertToHTML("Untitled1.xsl", "Untitled1.xml");
            for (Tariff b : tariffs) {
                System.out.println(b.getName());
                System.out.println(b.paraeters.tarification);
                System.out.println(b.callPrice.OntheLandlinPrice);
                System.out.println(b.paraeters.connectinFree);
                System.out.println("_______________________________________________");
            }
        }
    }
}
