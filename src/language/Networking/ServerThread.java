//david carley
package language.Networking;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class ServerThread extends Thread {

    private DatagramSocket socket;




    @Override
    public void run(){
        System.out.println("Server Started...");

        try{
            DatagramSocket socket = new DatagramSocket(5000);
            while(true) {
                for (int i = 1; i <= 4; i++) {
                    byte[] buffer = new byte[50];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    int num = Integer.parseInt(new String(buffer).trim());
                    System.out.println(">>Client No:" + i +" started!");
                    System.out.println("From Client-"+i+": Number is : "+ new String(buffer, 0, packet.getLength()));
                    System.out.println("Client IP: " + packet.getAddress().getHostAddress());
                    System.out.println("Client host: " + packet.getPort());


                    String returnString = "From Server to Client-" +i+ " Thrice of " + num + " is " + (num * 3);
                    byte[] buffer2 = returnString.getBytes();
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();
                    packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                    socket.send(packet);

                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
