package Main;

import example.AdditionService.Addition;
import example.HelloWorldService.HelloWorld;
import example.HelloWorldService.IHelloWorld;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String args[]){

        URL wsdlUrl = null;
        try {
            wsdlUrl = new URL("http://localhost:9001/HelloWorld?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //{http://HelloWorldService.example/}HelloWorldService -> found on url via Browser, names are listed there
        QName qname = new QName("http://HelloWorldService.example/", "HelloWorldService");
        Service service = Service.create(wsdlUrl, qname);
        IHelloWorld helloWorldService = service.getPort(IHelloWorld.class);
        System.out.println(helloWorldService.sayHelloWorldFrom("Fabian"));
    //  penAdditionService();

    }

    public static void testServices(){
        Addition.startService();
        HelloWorld.startService();
        helloCli helloClix = new helloCli("127.0.0.1");
        while (true){

            try{
                try {
                    Thread.sleep(1000);
                }catch (Exception exp){

                }
                helloClix.askService();

            }catch (Exception e){
                System.out.println("Exception in askServcie "+e);
            }

        }
    }

    public static void penAdditionService(){
        Addition.startService();
        // HelloWorld.startService();
        //  helloCli helloClix = new helloCli("127.0.0.1");
        int counter = 0;
        while (true){

            try{
                try {
                    Thread.sleep(100+counter);
                }catch (Exception exp){

                }

                if (counter % 25 == 0)
                System.out.println("Threads "+ counter);

                counter++;
                System.out.println(counter);
                Thread thread = new Thread(new PenAddition());
                thread.start();
                // helloClix.askService();

            }catch (Exception e){
                System.out.println("Exception in askServcie "+e);
            }

        }
    }
}
