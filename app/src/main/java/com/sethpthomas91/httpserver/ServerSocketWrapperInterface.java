package com.sethpthomas91.httpserver;

import java.io.IOException;

public interface ServerSocketWrapperInterface {
    public void createServerSocket(int port) throws IOException;
    public void listen() throws IOException;
}
