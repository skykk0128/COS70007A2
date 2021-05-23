import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String args[]) throws Exception {
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        byte[] sendData;
        byte[] receiveData = new byte[1024];
        System.out.println("Authoer: Shikai Wang");
        System.out.println("ID: 103162660");
        System.out.println("Java DownloadClient 4001 4000 " + ipAddress.getHostAddress());
        System.out.println("");
        File folder = new File("/Users/wangshikai/Desktop/assignment1/src/Server Files/");
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
            System.out.print("Please enter the file name you wish to download: ");
            String sentence = inFromUser.readLine();
            System.out.println("Connecting to server...");
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 4000);
            System.out.println("Connection established successfully");
            System.out.println("Requesting the file…");
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
            System.out.println("File " + modifiedSentence + " found on server. Starting the download");
            File downloadFolder = new File("/Users/wangshikai/Desktop/COS7007A2/src/Server Downloaded Files/");
            downloadFolder.createTempFile(sentence, ".pdf");
            System.out.println("File downloaded successfully. You can check the file now");
            System.out.println("Closing connection to server.");
            System.out.println("Successful.");
            System.out.println("");
        }
    }
}
