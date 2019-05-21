package example.HelloWorldService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "example.HelloWorldService.IHelloWorld")
public class HelloWorld implements IHelloWorld{

  @WebMethod
  @Override
  public TestObject sayHelloWorldFrom(String from) {
    String result = "Hello, world, from " + from;
    //System.out.println(result);
    TestObject testObject= new TestObject();
    testObject.s=result;
    testObject.swag=new ArrayList<>();
    return testObject;
  }

  @WebMethod
  @Override
  public ArrayList<String> bla() {
    return new ArrayList<>();
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
