# Simple UDP Server/Client implementation

The UDP Server is a simple echo server implementation that listens on a port and echoes back the packets it receives to the client that sent it.

This uses a packet bus system to ensure that packets are sent in the correct order alongside threading.

### Limitations

- Each packet is limited to 128 bytes, you can configure this through the `max_packet_size` parameter in ProtocolBase.
In an ideal world, you should be compressing the data before sending it.
- Packet deserialization doesn't strip out the packet id. This could be a good thing or a bad thing depending, but this means it's just more work on deserializing it.
- Packets are nulled out by 0 bytes. We just need to be cautious about this as if you end your byte array with 0s, it will strip it out. Work around is to end it with a NULL byte after your zeros to ensure it won't be erased.
- Unsafe in a production environment, there should be packet checks and encryption to ensure that the packets are secure.
