package org.dhbw.ai;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author uli
 */
public class Receiver implements Runnable {

    /**

     */
    public void run() {

        try {
            //Address
            String multiCastAddress = "224.0.0.1";
            final int multiCastPort = 52684;
            final int bufferSize = 1024 * 4; //Maximum size of transfer object

            //Create Socket
            System.out.println("Create socket on address " + multiCastAddress + " and port " + multiCastPort + ".");
            InetAddress group = InetAddress.getByName(multiCastAddress);
            MulticastSocket s = new MulticastSocket(multiCastPort);
            s.joinGroup(group);

            //Receive data
            while (true) {
                System.out.println("Wating for datagram to be received...");

                //Create buffer
                byte[] buffer = new byte[bufferSize];
                s.receive(new DatagramPacket(buffer, bufferSize, group, multiCastPort));
                System.out.println("Datagram received!");

                //Deserialze object
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream ois = new ObjectInputStream(bais);
                try {
                    Object readObject = ois.readObject();
                    if (readObject instanceof String) {
                        String message = (String) readObject;
                        System.out.println("Message is: " + message);
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
}