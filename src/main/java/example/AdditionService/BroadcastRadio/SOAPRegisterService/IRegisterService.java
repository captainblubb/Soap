package example.AdditionService.BroadcastRadio.SOAPRegisterService;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IRegisterService {

    @WebMethod
    public void registerService(String serviceTyp, String url);
}
