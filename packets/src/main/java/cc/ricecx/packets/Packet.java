package cc.ricecx.packets;

import java.net.InetSocketAddress;
import java.util.Arrays;

public abstract class Packet<T extends Packet<T>> {

    public InetSocketAddress sendAddress;

    public abstract T deserialize(byte[] data);

    public abstract byte[] serialize();


    public static byte[] trim(byte[] bytes) {
        int i = bytes.length - 1;
        while (i >= 0 && bytes[i] == 0) {
            --i;
        }

        return Arrays.copyOf(bytes, i + 1);
    }

}
