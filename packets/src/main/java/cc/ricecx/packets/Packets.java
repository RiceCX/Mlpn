package cc.ricecx.packets;

import cc.ricecx.packets.impl.LoginPacket;
import cc.ricecx.packets.impl.MovePacket;

import java.lang.reflect.InvocationTargetException;
public enum Packets {

    LOGIN(0x01, LoginPacket.class),
    MOVE(0x02, MovePacket.class);

    public int getId() {
        return id;
    }

    private final int id;
    private final Class<? extends Packet<?>> packetClass;
    Packets(int packetId, Class<? extends Packet<?>> clazz) {
        this.id = packetId;
        this.packetClass = clazz;
    }

    @SuppressWarnings("unchecked")
    public <T extends Packet<?>> T deserialize(byte[] bytes) {
        try {
            return (T) packetClass.getConstructor().newInstance().deserialize(bytes);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            System.err.println("Failed to create packet instance. Make sure there are a no args constructor.");
        }
        return null;
    }

    public static String getById(int packetId) {
        for (Packets packet : Packets.values()) {
            if (packet.getId() == packetId) {
                return packet.name();
            }
        }
        return null;
    }

    public static Packets deserialize(int id) {
        for (Packets packet : Packets.values()) {
            if (packet.id == id) return packet;
        }
        return null;
    }
}
