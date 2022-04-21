package com.sethpthomas91.httpserver;

import java.io.IOException;

public interface ServerSocketWrapperInterface {
    void createServerSocket(int port) throws IOException;
    void listen() throws IOException;
    int getPort();
}
