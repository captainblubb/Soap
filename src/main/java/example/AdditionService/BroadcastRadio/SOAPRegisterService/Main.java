package example.AdditionService.BroadcastRadio.SOAPRegisterService;

import example.AdditionService.BroadcastRadio.Configuration;

public class Main {
    public static void main(String args[]){
        RegisterService.startService(null, Configuration.general_https+"0.0.0.0"+Configuration.Radio_Registration_url);
        while (true);
    }
}
