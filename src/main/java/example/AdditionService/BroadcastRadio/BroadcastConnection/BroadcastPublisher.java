package example.AdditionService.BroadcastRadio.BroadcastConnection;

import example.AdditionService.BroadcastRadio.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BroadcastPublisher implements Runnable{


    String ContentType;
    String multiCastAddress;
    int multiCastPort;
    volatile String message;
    InetAddress group;
    MulticastSocket s;

    private volatile boolean stop=false;

    public void stop(){
        this.stop=true;
    }

    public BroadcastPublisher(String multiCastAddress, int multiCastPort, String message, String ContentType, int DelayInMs) throws IOException {
        this.multiCastAddress = multiCastAddress;
        this.multiCastPort = multiCastPort;
        this.message = message;
        this.ContentType = ContentType;

        //Create Socket
        System.out.println("Create socket on address " + multiCastAddress + " and port " + multiCastPort + ".");
        group = InetAddress.getByName(multiCastAddress);
        s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);
    }

    @Override
    public void run() {

        while (stop==false) {
            try {
                Thread.sleep(1000);
                sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage() throws IOException {
        //Address
        //String multiCastAddress = "224.0.0.1";
        //final int multiCastPort = 52684;
        //Prepare Data
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(ContentType+ Configuration.general_Seperation +message);
        byte[] data = baos.toByteArray();

        //Send data
        s.send(new DatagramPacket(data, data.length, group, multiCastPort));
    }

    public void setMessage(String message){
        this.message = message;
    }

}
