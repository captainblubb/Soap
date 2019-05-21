package example.HelloWorldService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IHelloWorld {

    @WebMethod
    TestObject sayHelloWorldFrom(String from);

    @WebMethod
    ArrayList<String> bla();

}
