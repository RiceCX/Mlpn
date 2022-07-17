package cc.ricecx.packets.impl;

import cc.ricecx.packets.Packet;
import cc.ricecx.packets.Packets;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovePacket that)) return false;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return z == that.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
