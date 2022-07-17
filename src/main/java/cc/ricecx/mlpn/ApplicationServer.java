package cc.ricecx.mlpn;

import cc.ricecx.protocols.udp.UdpServer;

import java.net.InetSocketAddress;
import java.net.SocketException;

import static cc.ricecx.mlpn.Constants.SERVER_PORT;

public class ApplicationServer {

    public static void main(String[] args) throws SocketException {
        System.out.println("Hello Server!");

        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", SERVER_PORT);
        UdpServer server = new UdpServer(addr);

        System.out.println("Server connected on port:" + server.getSocket().getLocalPort());

        server.listen();
    }
}
