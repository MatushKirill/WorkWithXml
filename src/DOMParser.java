import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//Dom parser
public class DOMParser {

    public List<Tariff> DOM() throws JDOMException, IOException {
        StaxParser parser=new StaxParser();
        SAXBuilder builder=new SAXBuilder();
        File xmlFile=new File("Untitled1.xml");
        List<Tariff> tariffs    =new ArrayList<>();
        Document document=(Document) builder.build(xmlFile);
        Element rootNode=document.getRootElement();
        List<Element> listTariffs=rootNode.getChildren("Tariff");
        for(Element e:listTariffs){
            Tariff tariff=new Tariff();
            String id=e.getAttributeValue("id");
            tariff.setId(id);

            Element name=e.getChild("name");
            String nameValue=name.getText();
            tariff.setName(nameValue);

            Element operatorName=e.getChild("OperatorName");
            String operatorNameValue=operatorName.getText();
            tariff.setOperatorName(operatorNameValue);

            Element payRoll=e.getChild("Payroll");
            String payRollValue=payRoll.getText();
            tariff.setPayroll(Integer.valueOf(payRollValue));

            Element callPrice=e.getChild("CallPrice");
            tariff.callPrice=new Tariff.CallPrice();

            Element withinTheNetwork=callPrice.getChild("WithinTheNetwork", Namespace.getNamespace("http://ddd.com/blabla.xsd"));
            String withinTheNetworkValue=withinTheNetwork.getText();
            tariff.callPrice.withinTheNetwork=Integer.valueOf(withinTheNetworkValue);

            Element OutOfTheNetwork =callPrice.getChild("OutOfTheNetwork", Namespace.getNamespace("http://ddd.com/blabla.xsd"));
            String outOfTheNetworkValue=OutOfTheNetwork.getText();
            tariff.callPrice.OutOfTheNetwork=Double.valueOf(outOfTheNetworkValue);

            Element OntheLandlinPrice =callPrice.getChild("OnTheLandlinPhone", Namespace.getNamespace("http://ddd.com/blabla.xsd"));
            String OnTheLandlinPhoneValue=OntheLandlinPrice.getText();
            tariff.callPrice.OntheLandlinPrice=Double.valueOf(OnTheLandlinPhoneValue);

            Element parameters=e.getChild("Parameters");
            tariff.paraeters=new Tariff.Parameters();

            Element ConnectinFee =parameters.getChild("ConnectinFee");
            String ConnectinFeeValue=ConnectinFee.getText();
            tariff.paraeters.connectinFree=Integer.valueOf(ConnectinFeeValue);

            Element Tariffication=parameters.getChild("Tariffication");
            String TarifficationValue=Tariffication.getText();
            tariff.paraeters.tarification=TarifficationValue;

            tariffs.add(tariff);
        }

        return tariffs;
    }
}
