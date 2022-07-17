package cc.ricecx.protocols.udp;

import cc.ricecx.packets.Packet;
import cc.ricecx.packets.Packets;
import cc.ricecx.protocols.shared.ProtocolServer;

import java.net.*;

public class UdpServer extends ProtocolServer {


    public UdpServer(InetSocketAddress addr) throws SocketException {
        super(new DatagramSocket(addr));
    }

    @Override
    protected void handlePacket(Packets packet, byte[] bytes, DatagramPacket datagramPacket) {
        Packet<?> createdPacket = packet.deserialize(bytes);
        if (createdPacket == null) {
            System.err.println("Failed to deserialize packet: " + packet.getClass().getName());
            return;
        }
        InetAddress remoteAddr = datagramPacket.getAddress();

        sendPacket(createdPacket, new InetSocketAddress(remoteAddr, datagramPacket.getPort()));
    }
}
