package example.AdditionService;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IAddition {

    @WebMethod
    int addition(int a, int b);


}
