package example.AdditionService.BroadcastRadio;

import example.AdditionService.BroadcastRadio.BroadcastConnection.BroadcastConsumer;
import example.AdditionService.BroadcastRadio.BroadcastConnection.IBroadcastListener;
import example.AdditionService.BroadcastRadio.BroadcastConnection.ServiceInformation;
import example.AdditionService.BroadcastRadio.SOAPRegisterService.RegisterOnRadio;

import java.net.MalformedURLException;

public class RadioClient implements IBroadcastListener,Runnable{


    Thread radioConsumerThread;

    volatile boolean radioIpknown = false;
    volatile String radioIP;

    public RadioClient(){

    }


    @Override
    public void run() {


        //1. Generate BroadcastConsumer on Radio

        BroadcastConsumer radioConsumer = new BroadcastConsumer(Configuration.Radio_multiCastAddress,Configuration.Radio_multiCastPort);
        radioConsumer.addIBroadcastListener(this);
        radioConsumerThread = new Thread(radioConsumer);
        radioConsumerThread.start();

        //Wait until Ip published
        while (radioIpknown == false){

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("waiting for radioIpKnown");

        }

        System.out.println("_______________>RADIO IP IS SET +:"+radioIP);

        boolean registered=false;
        while (registered==false) {
            //Register at SOAP
            try {
                RegisterOnRadio registerOnRadio = new RegisterOnRadio(radioIP, Configuration.NameService_ContentType);
                registerOnRadio.registrateService();
                registered = true;
                System.out.println("Registered Service Succesfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("____LISTEN TO NAMESERVICE BROADCAST______");

        //Listen to NameService Broadcast
        BroadcastConsumer nameServiceConsumer = new BroadcastConsumer(Configuration.NameService_multiCastAddress,Configuration.NameService_multiCastPort);
        nameServiceConsumer.addIBroadcastListener(this);
        radioConsumerThread = new Thread(nameServiceConsumer);
        radioConsumerThread.start();
    }

    @Override
    public void getNotfied(ServiceInformation serviceInformation) {


        switch (serviceInformation.serviceTyp){

            case Configuration.Radio_ContentType:

                System.out.println("RADIO IP IS "+serviceInformation.urls.get(0));
                radioIpknown=true;
                radioIP = serviceInformation.urls.get(0);
                break;

            case Configuration.NameService_ContentType:

                System.out.println(serviceInformation.urls.get(0));

            }

    }

}
