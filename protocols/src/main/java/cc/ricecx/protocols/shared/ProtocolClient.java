package cc.ricecx.protocols.shared;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public abstract class ProtocolClient extends ProtocolBase {



    protected final SocketAddress serverAddr;

    protected ProtocolClient(DatagramSocket socket, SocketAddress serverAddr) {
        super(socket);
        this.serverAddr = serverAddr;
    }

    public void connect() throws SocketException {
        socket.connect(serverAddr);
    }

}
