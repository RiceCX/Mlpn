package cc.ricecx.protocols.shared;

import cc.ricecx.packets.Packet;
import cc.ricecx.packets.Packets;
import cc.ricecx.protocols.udp.PacketBus;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public abstract class ProtocolBase {

    protected static final byte[] MAX_PACKET_SIZE = new byte[128];
    protected final DatagramSocket socket;

    public PacketBus packetBus = new PacketBus();

    protected ProtocolBase(DatagramSocket socket) {
        this.socket = socket;
    }

    public void listen() {
        while (true) {
            try {
                byte[] buf = new byte[MAX_PACKET_SIZE.length];
                DatagramPacket rawPacket = new DatagramPacket(buf, MAX_PACKET_SIZE.length);
                socket.receive(rawPacket); // packet is now written to with x amt of bytes
                byte[] data = rawPacket.getData();
                int packetId = data[0] & 0xFF;
                System.out.println("Received packet: " + Packets.getById(packetId));
                Packets packet = Packets.deserialize(packetId);

                if (packet == null) {
                    System.err.println("Received unknown packet id: " + packetId);
                    continue;
                }

                handlePacket(packet, data, rawPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendPacket(Packet<?> packet, InetSocketAddress addr) {
        System.out.println("Sending packet: " + packet.getClass().getName());
        try {
            byte[] bytes = packet.serialize();

            if (bytes.length < MAX_PACKET_SIZE.length) {
                Arrays.fill(MAX_PACKET_SIZE, (byte) 0);
                System.arraycopy(bytes, 0, MAX_PACKET_SIZE, 0, bytes.length);
                bytes = MAX_PACKET_SIZE;
            } else if (bytes.length > MAX_PACKET_SIZE.length) throw new RuntimeException("Packet is too large to send");

            DatagramPacket packetToSend = new DatagramPacket(bytes, bytes.length, addr);
            socket.send(packetToSend);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    protected abstract void handlePacket(Packets packet, byte[] bytes, DatagramPacket socket);

    public DatagramSocket getSocket() {
        return socket;
    }
}
