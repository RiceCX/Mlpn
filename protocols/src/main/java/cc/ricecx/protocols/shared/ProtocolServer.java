package cc.ricecx.protocols.shared;

import java.net.DatagramSocket;

public abstract class ProtocolServer extends ProtocolBase {

    protected ProtocolServer(DatagramSocket socket) {
        super(socket);
    }
}
