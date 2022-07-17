package cc.ricecx.mlpn;

import cc.ricecx.packets.impl.LoginPacket;
import cc.ricecx.protocols.udp.UdpClient;

import java.net.InetSocketAddress;
import java.net.SocketException;

import static cc.ricecx.mlpn.Constants.SERVER_PORT;

public class Application {

    public static void main(String[] args) throws SocketException {
        System.out.println("Hello World!");
        UdpClient client = new UdpClient(new InetSocketAddress("127.0.0.1", SERVER_PORT));
        client.connect();
        int port = client.getSocket().getPort();
        System.out.println("Client connected on port:" + port);
        client.start();

        for (int i = 0; i < 5; i++) {
            LoginPacket loginPacket = new LoginPacket();
            loginPacket.username("Andy " + i);
            client.packetBus.pushPacket(loginPacket);
        }
    }
}
