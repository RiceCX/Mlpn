package cc.ricecx.packets.impl;

import cc.ricecx.packets.Packet;

public class MovePacket extends Packet<MovePacket> {

    @Override
    public MovePacket deserialize(byte[] data) {
        return null;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }
}
