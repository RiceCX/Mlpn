package cc.ricecx.packets;

import cc.ricecx.packets.impl.LoginPacket;

public class PacketHandler {


    public static void handle(Packets packet, byte[] data) {
        switch (packet) {
            case LOGIN -> {
                LoginPacket loginPacket = new LoginPacket().deserialize(data);
                System.out.println("Login packet received: " + loginPacket.username());
            }
            case MOVE -> System.out.println("Move packet received");
        }
    }
}
