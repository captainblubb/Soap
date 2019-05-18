package example.HelloWorldService;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;


@WebService(endpointInterface = "example.HelloWorldService.IHelloWorld")
public class HelloWorld implements IHelloWorld{

  @Override
  @WebMethod
  public String sayHelloWorldFrom(String from) {
    String result = "Hello, world, from " + from;
    //System.out.println(result);
    return result;
  }

  public static void startService(){
    Object implementor = new HelloWorld();
    String address = "http://localhost:9001/HelloWorld";
    Endpoint.publish(address, implementor);
  }

  public static void main(String args[]){
    startService();
  }
}
