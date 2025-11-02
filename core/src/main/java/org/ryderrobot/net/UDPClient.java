package org.ryderrobot.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP client to robot.
 */
public class UDPClient implements NetClient {
    static public char EOR = 0x1E; // termination message

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    @Override
    public void init(String host, int port) throws Exception {
        socket = new DatagramSocket();
        serverAddress = InetAddress.getByName(host);
        serverPort = port;
    }

    @Override
    public void sendMessage(String message) throws Exception {
        message += EOR;

        byte[] data = message.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
        socket.send(packet);
    }

    // TODO: This will need to be more clever to allow for different size packets.
    // should read one character at a time till it hits termination.
    @Override
    public String receiveMessage() throws Exception {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    @Override
    public void close() {
        socket.close();
    }
}
