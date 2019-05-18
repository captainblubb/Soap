package example.AdditionService.BroadcastRadio.SOAPRegisterService;

import example.AdditionService.BroadcastRadio.Configuration;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;


@WebService(endpointInterface = Configuration.Radio_Registration_EP)
public class RegisterService implements IRegisterService,IRegisterProvider {

    private ArrayList<IRegisterListener> registerListeners= new ArrayList<>();

    @WebMethod
    @Override
    public void registerService(String serviceTyp, String url) {
        notifyListener(serviceTyp,url);
    }

    @Override
    public void notifyListener(String serviceTyp,String url){
        for (IRegisterListener reg:registerListeners) {
            reg.getNotified(serviceTyp,url);
        }
    }

    @Override
    public void addIRegisterListener(IRegisterListener iRegisterListener) {
        registerListeners.add(iRegisterListener);
    }

    //Add Listener and publish to URL
    public static void startService(IRegisterListener registerListener, String url){
        RegisterService registerService = new RegisterService();
        registerService.addIRegisterListener(registerListener);
        Object implementor = registerService;
        String address = url;
        Endpoint.publish(address, implementor);
    }
}
