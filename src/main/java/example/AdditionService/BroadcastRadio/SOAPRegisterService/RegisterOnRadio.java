package example.AdditionService.BroadcastRadio.SOAPRegisterService;

import example.AdditionService.BroadcastRadio.Configuration;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterOnRadio {

    public RegisterOnRadio(String ip,String contentType, String url) throws MalformedURLException {
        URL wsdlUrl = new URL(Configuration.general_https+ip+Configuration.Radio_Registration_url+"?wsdl");
        //{http://HelloWorldService.example/}HelloWorldService -> found on url via Browser, names are listed there
        QName qname = new QName(Configuration.Radio_Registration_EP);
        Service service = Service.create(wsdlUrl, qname);
        IRegisterService registerService = service.getPort(IRegisterService.class);
        registerService.registerService(contentType,url);
    }
}
