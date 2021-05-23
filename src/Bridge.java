import java.io.*;
import java.net.*;

public class Bridge {
    public static void main(String args[]) throws Exception {

        DatagramSocket bridgeSocketServer = new DatagramSocket(4000);
        DatagramSocket bridgeSocketClient = new DatagramSocket();
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        InetAddress ipAddress = InetAddress.getByName("localhost");
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            bridgeSocketServer.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            System.out.println("Data Received: " + sentence.trim());
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 5000);
            bridgeSocketClient.send(sendPacket);

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            bridgeSocketServer.receive(receivePacket);
            String capitalisedSentence = new String(receivePacket.getData());
            System.out.println("Capitalised Sentence in the Bridge Class: " + capitalisedSentence.trim());

            sendData = capitalisedSentence.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress,5000);
            bridgeSocketClient.send(sendPacket);
        }
    }
}
