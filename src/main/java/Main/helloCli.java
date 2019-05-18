package Main;

import example.AdditionService.IAddition;
import example.HelloWorldService.IHelloWorld;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class helloCli {

    private String ipAdress;
    private final String serviceName = "HelloWorld";
    private URL url;

    public helloCli(String ip){

        this.ipAdress = ip;
    }


    public void askService() throws MalformedURLException {

        URL wsdlUrl = new URL("http://localhost:9001/HelloWorld?wsdl");
        //{http://HelloWorldService.example/}HelloWorldService -> found on url via Browser, names are listed there
        QName qname = new QName("http://HelloWorldService.example/", "HelloWorldService");
        Service service = Service.create(wsdlUrl, qname);
        IHelloWorld helloWorldService = service.getPort(IHelloWorld.class);
        System.out.println(helloWorldService.sayHelloWorldFrom("Khadija"));

        URL wsdlUrl2 = new URL("http://localhost:12012/swag/Addition");
        //{http://AdditionService.example/}AdditionService
        QName qname2 = new QName("http://AdditionService.example/", "AdditionService");
        Service service2 = Service.create(wsdlUrl2,qname2);
        IAddition additionService = service2.getPort(IAddition.class);
        System.out.println(additionService.addition(5,12));

        URL wsdlUrl3 = new URL("http://localhost:12012/foo/Addition");
        //{http://AdditionService.example/}AdditionService
        QName qname3 = new QName("http://AdditionService.example/", "AdditionService");
        Service service3 = Service.create(wsdlUrl3,qname3);
        IAddition additionService2 = service3.getPort(IAddition.class);
        System.out.println(additionService2.addition(5,12));


    }

}
