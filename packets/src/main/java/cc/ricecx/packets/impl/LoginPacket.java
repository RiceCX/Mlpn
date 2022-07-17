package cc.ricecx.packets.impl;

import cc.ricecx.packets.Packet;
import cc.ricecx.packets.Packets;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(fluent = true)
public class LoginPacket extends Packet<LoginPacket> {
    private String username;

    @Override
    public LoginPacket deserialize(byte[] data) {
        byte[] bytes = new byte[data.length - 1];
        System.arraycopy(data, 1, bytes, 0, bytes.length);
        this.username = new String(trim(bytes));
        return this;
    }

    @Override
    public byte[] serialize() {
        byte[] bytes = this.username.getBytes();
        byte[] data = new byte[bytes.length + 1];
        data[0] = (byte) Packets.LOGIN.getId();
        System.arraycopy(bytes, 0, data, 1, bytes.length);
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginPacket that)) return false;

        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
