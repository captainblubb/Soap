package org.dhbw.ai;

public class Main {
    public static void main(String args[]){
        System.out.println("Start Receiver");

        try{

            Receiver receiver = new Receiver();
            Thread receiverThread = new Thread(receiver);
            receiverThread.start();

            System.out.println("Starting Sender");

            while (true){
                Sender.sendMessage();
                Thread.sleep(200);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
