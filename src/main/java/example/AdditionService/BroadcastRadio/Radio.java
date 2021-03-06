package example.AdditionService.BroadcastRadio;

import example.AdditionService.BroadcastRadio.BroadcastConnection.BroadcastPublisher;
import example.AdditionService.BroadcastRadio.BroadcastConnection.ServiceInformation;
import example.AdditionService.BroadcastRadio.SOAPRegisterService.IRegisterListener;
import example.AdditionService.BroadcastRadio.SOAPRegisterService.RegisterService;

import java.net.Inet4Address;
import java.util.ArrayList;

public class Radio implements Runnable, IRegisterListener {

    BroadcastPublisher radioPublish;
    Thread radioThread;
    BroadcastPublisher namePublish;
    Thread nameThread;

    public static volatile ArrayList<String> nameServices = new ArrayList<>();

    public Radio(){

    }

    @Override
    public void run() {

        //Initialize 2 Frequenz
        //      1   -> Radio own ip
        //      2   -> IP of Nameserver

        boolean initalized = false;

        while (initalized==false) {

            try {

                //Create SOAP Webservice for Registration of Service
                RegisterService.startService(this,Configuration.general_https+"0.0.0.0"+Configuration.Radio_Registration_url);


                //Start radioThread and publish local IP
                String localIp = Inet4Address.getLocalHost().getHostAddress();
                ServiceInformation serviceInformation = new ServiceInformation();
                serviceInformation.serviceTyp= Configuration.Radio_ContentType;
                serviceInformation.urls.add(Configuration.general_https+localIp+Configuration.Radio_Registration_url);
                radioPublish = new BroadcastPublisher(Configuration.Radio_multiCastAddress, Configuration.Radio_multiCastPort,serviceInformation,Configuration.Radio_ContentType,Configuration.Radio_Delay_Broadcast);
                radioThread = new Thread(radioPublish);
                radioThread.start();


                ServiceInformation serviceInformationName = new ServiceInformation();
                serviceInformationName.serviceTyp = Configuration.NameService_ContentType;
                //Start publish name Services
                namePublish = new BroadcastPublisher(Configuration.NameService_multiCastAddress, Configuration.NameService_multiCastPort,serviceInformationName,Configuration.NameService_ContentType,Configuration.NameService_Delay_Broadcast);
                nameThread = new Thread(namePublish);
                nameThread.start();

                initalized = true;
                System.out.println("__________Started Broadcast Channels succesfull___________");

            } catch (Exception exp) {

                System.out.println("Failed to start Radio "+exp);
            }

        }


            while (true) {

                //do nothing
                try {
                    Thread.sleep(0b111110100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    //GetTCP Messages via Oberserver Pattern
    @Override
    public void getNotified(String serviceTyp,String url){
        switch (serviceTyp){
            //Adds and sets new url to publisher as Message for UDP Port
            case Configuration.NameService_ContentType :
                nameServices.add(url);
                setNameServiceMessage();
        }
    }

    public void setNameServiceMessage(){

        ServiceInformation serviceInformation = new ServiceInformation();
        serviceInformation.serviceTyp = Configuration.NameService_ContentType;
        serviceInformation.urls.addAll(nameServices);
        this.namePublish.setMessage(serviceInformation);
    }
}
