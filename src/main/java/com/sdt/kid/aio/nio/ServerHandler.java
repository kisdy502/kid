package com.sdt.kid.aio.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface ServerHandler {
    void handleAccept(SelectionKey selectionKey) throws IOException;

    String handleRead(SelectionKey selectionKey) throws IOException;
}
