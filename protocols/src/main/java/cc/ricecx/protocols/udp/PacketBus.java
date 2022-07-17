package cc.ricecx.protocols.udp;

import cc.ricecx.packets.Packet;

import java.util.LinkedList;

public class PacketBus {

    private final LinkedList<Packet<?>> packetsToSend = new LinkedList<>();

    public static final long POLL_TIME = 1000;

    public void pushPacket(Packet<?> packet) {
        packetsToSend.add(packet);
    }

    public Packet<?> popPacket() {
        return packetsToSend.poll();
    }

    public LinkedList<Packet<?>> getPacketsToSend() {
        return packetsToSend;
    }

}
