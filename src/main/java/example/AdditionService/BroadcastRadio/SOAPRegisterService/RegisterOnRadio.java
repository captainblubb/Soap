package example.AdditionService.BroadcastRadio.SOAPRegisterService;

import example.AdditionService.BroadcastRadio.Configuration;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterOnRadio {
    String url;
    String contentType;

    public RegisterOnRadio(String url,String contentType) throws MalformedURLException {
        this.url=url;
        this.contentType =contentType;
    }

    public void registrateService() throws MalformedURLException {
        URL wsdlUrl = new URL(url+"?wsdl");
        System.out.println("Try To register nameService on: "+wsdlUrl.toString());

        QName qname = new QName(Configuration.Radio_Registration_IMPL_NameSpace,Configuration.Radio_Registration_Local_Part);
        Service service = Service.create(wsdlUrl, qname);
        IRegisterService registerService = service.getPort(IRegisterService.class);
        registerService.registerService(contentType,"blalblallala url zum Service");
    }
}
