package example.AdditionService.BroadcastRadio.SOAPRegisterService;

import example.AdditionService.BroadcastRadio.Configuration;

import java.util.Arrays;

public class Main {
    public static void main(String args[]){

        byte[] bytes = new byte[]{2,3,4};
        byte[] bytes2 = new byte[]{2,3,4};

        byte[] bytez = Arrays.copyOfRange(bytes, 0, 3);

        System.out.println(bytez);



        /*
        RegisterService.startService(null, Configuration.general_https+"0.0.0.0"+Configuration.Radio_Registration_url);
        while (true);
        */
    }
}
