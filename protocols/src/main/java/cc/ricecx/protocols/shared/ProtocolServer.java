package cc.ricecx.protocols.shared;

import cc.ricecx.packets.Packets;

import java.net.DatagramSocket;

public abstract class ProtocolServer extends ProtocolBase {

    protected ProtocolServer(DatagramSocket socket) {
        super(socket);
    }
}
