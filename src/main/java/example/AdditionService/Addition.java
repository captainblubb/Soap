package example.AdditionService;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;



@WebService(endpointInterface = "example.AdditionService.IAddition")
public class Addition implements IAddition{


    @Override
    @WebMethod
    public int addition(int a, int b) {
       return a+b;
    }

    public static void startService(){
        Object implementor = new Addition();
        String address = "http://localhost:12012/swag/Addition";
        Endpoint.publish(address, implementor);

        Object implementor2 = new Addition();
        String address2 = "http://localhost:12012/foo/Addition";
        Endpoint.publish(address2, implementor2);
    }

    public static void main(String args[]){
        startService();
    }
}
