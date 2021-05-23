import java.io.*;
import java.net.*;
import java.util.*;

public class UDPServer {
    public static void main(String args[]) throws Exception {
        InetAddress ipAddress = InetAddress.getLocalHost();
        DatagramSocket serverSocket = new DatagramSocket(5000);
        byte[] receiveData = new byte[1024];
        byte[] sendData;
        System.out.println("Authoer: Shikai Wang");
        System.out.println("ID: 103162660");
        System.out.println("Java FileServer " + serverSocket.getLocalPort());
        System.out.println("");
        File folder = new File("/Users/wangshikai/Desktop/COS70007A2/src/Server Files/");
        if (folder.length() == 0) {
            System.out.println("No File under current folder!!");
        }
        else {
            File[] fileList = folder.listFiles();
            System.out.println("The files now on the server: ");
            for (File file: fileList) {
                if (file.isFile()) {
                    System.out.println(file.getName() + " " + file.length() + " bytes");
                }
            }
        }
        System.out.println("");
        while (true) {
            System.out.println("The server is now starting on Local IP address " + ipAddress.getHostAddress()
                    + ", Port " + serverSocket.getLocalPort());
            System.out.println("Waiting for clients...");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            System.out.println("Incoming client: IP " + receivePacket.getAddress() + ", Port " + receivePacket.getPort());
            String sentence = new String(receivePacket.getData()).trim();
            System.out.println("File requested: " + sentence);
            System.out.println("Starting transfer...");
            InetAddress IPAddress = receivePacket.getAddress();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 5000);
            serverSocket.send(sendPacket);
            PrintWriter out = new PrintWriter("text.txt");
            out.println(sentence);
            System.out.println("File transferred successfully!");
            System.out.println("Connection terminated by client");
            System.out.println("");
        }
    }
}
