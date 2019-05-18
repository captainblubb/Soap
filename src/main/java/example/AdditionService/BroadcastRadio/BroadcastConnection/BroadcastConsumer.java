package example.AdditionService.BroadcastRadio.BroadcastConnection;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class BroadcastConsumer implements Runnable, IBroadcastConsumer {

    String multiCastAddress;
    int multiCastPort;

    ArrayList<IBroadcastListener> broadcastListeners = new ArrayList<>();

    public BroadcastConsumer( String multiCastAddress, int multiCastPort){
        this.multiCastAddress = multiCastAddress;
        this.multiCastPort = multiCastPort;
    }



    public void run() {

        try {
            //Address
            final int bufferSize = 1024 * 4; //Maximum size of transfer object

            //Create Socket
            System.out.println("Create socket on address " + multiCastAddress + " and port " + multiCastPort + ".");
            InetAddress group = InetAddress.getByName(multiCastAddress);
            MulticastSocket s = new MulticastSocket(multiCastPort);
            s.joinGroup(group);

            //Receive data
            while (true) {
                //System.out.println("Wating for datagram to be received...");

                //Create buffer
                byte[] buffer = new byte[bufferSize];
                s.receive(new DatagramPacket(buffer, bufferSize, group, multiCastPort));
                //  System.out.println("Datagram received!");

                //Deserialze object
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream ois = new ObjectInputStream(bais);
                try {
                    Object readObject = ois.readObject();
                    if (readObject instanceof String) {
                        String message = (String) readObject;
                        System.out.println("Message is: " + message);
                        notifyListener(message);
                    } else {
                        System.out.println("The received object is not of type String!");
                    }
                } catch (Exception e) {
                    System.out.println("No object could be read from the received UDP datagram.");
                }

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void notifyListener(String message) {
        for (IBroadcastListener broadcastListener:broadcastListeners) {
            broadcastListener.getNotfied(message);
        }
    }

    @Override
    public void addIBroadcastListener(IBroadcastListener broadcastListener) {

    }
}
