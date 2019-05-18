package example.AdditionService.BroadcastRadio;

import example.AdditionService.BroadcastRadio.BroadcastConnection.BroadcastConsumer;
import example.AdditionService.BroadcastRadio.BroadcastConnection.IBroadcastListener;

public class RadioClient implements IBroadcastListener,Runnable{


    Thread radioConsumerThread;

    boolean radioIpknown = false;
    String radioIP;

    public RadioClient(){

    }


    @Override
    public void run() {


        //1. Generate BroadcastConsumer on Radio

        BroadcastConsumer radioConsumer = new BroadcastConsumer(Configuration.Radio_multiCastAddress,Configuration.Radio_multiCastPort);
        radioConsumer.addIBroadcastListener(this::getNotfied);
        radioConsumerThread = new Thread(radioConsumer);
        radioConsumerThread.start();

        //Wait until Ip published
        while (radioIpknown == false){

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void getNotfied(String message) {

        System.out.println(message);
    }

}
