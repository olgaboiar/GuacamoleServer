package com.olgaboiar.guacomole;

import java.io.IOException;

public class ServerRunner {
    public static void main(String[] args) throws IOException {
        EchoServer server;
        server = new EchoServer();
        server.start("localhost", 3333);
    }
}
