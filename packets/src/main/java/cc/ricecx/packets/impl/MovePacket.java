package cc.ricecx.packets.impl;

import cc.ricecx.packets.Packet;
import cc.ricecx.packets.Packets;

public class MovePacket extends Packet<MovePacket> {

    private int x;
    private int y;
    private int z;

    @Override
    public MovePacket deserialize(byte[] data) {
        this.x = data[1];
        this.y = data[2];
        this.z = data[3];
        return this;
    }

    @Override
    public byte[] serialize() {
        byte[] data = new byte[128];
        data[0] = (byte) Packets.MOVE.getId();
        data[1] = (byte) this.x;
        data[2] = (byte) this.y;
        data[3] = (byte) this.z;
        return data;
    }
}
