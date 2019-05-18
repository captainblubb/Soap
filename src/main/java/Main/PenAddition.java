package Main;

import example.AdditionService.IAddition;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class PenAddition implements Runnable{

    @Override
    public void run() {

        while (true) {
            URL wsdlUrl2 = null;
            try {
                wsdlUrl2 = new URL("http://localhost:12012/swag/Addition");

            //{http://AdditionService.example/}AdditionService
            QName qname2 = new QName("http://AdditionService.example/", "AdditionService");
            Service service2 = Service.create(wsdlUrl2, qname2);
            IAddition additionService = service2.getPort(IAddition.class);
            additionService.addition(5, 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        }
    }
}
