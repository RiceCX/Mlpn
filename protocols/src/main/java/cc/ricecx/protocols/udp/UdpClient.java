package cc.ricecx.protocols.udp;

import cc.ricecx.packets.Packet;
import cc.ricecx.packets.PacketHandler;
import cc.ricecx.packets.Packets;
import cc.ricecx.packets.impl.LoginPacket;
import cc.ricecx.protocols.shared.ProtocolClient;

import java.net.*;

public class UdpClient extends ProtocolClient {

    private final InetSocketAddress serverAddr;

    public UdpClient(SocketAddress serverAddr) throws SocketException {
        super(new DatagramSocket(new InetSocketAddress("127.0.0.1", 0)), serverAddr);
        this.serverAddr = (InetSocketAddress) serverAddr;
    }

    public void start() throws InterruptedException {
        try {
            connect();
            Thread listenThread = new Thread(this::listen);
            Thread sendThread = new Thread(this::send);

            listenThread.start();
            sendThread.start();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private void send() {
        while (true) {
            try {
                if (packetBus.getPacketsToSend().isEmpty()) continue;
                Packet<?> packet = packetBus.popPacket();
                System.out.println("Sending packet: " + packet.getClass().getSimpleName());
                if (packet.sendAddress == null) {
                    packet.sendAddress = serverAddr; // we are on client, we know what the server ip is
                }
                sendPacket(packet, packet.sendAddress);

                Thread.sleep(PacketBus.POLL_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    protected void handlePacket(Packets packet, byte[] data, DatagramPacket socket) {
        PacketHandler.handle(packet, data);
    }
}
