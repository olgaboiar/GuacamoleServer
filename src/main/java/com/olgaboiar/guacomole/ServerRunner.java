package com.olgaboiar.guacomole;

import java.io.IOException;

public class ServerRunner {
    public static void main(String[] args) throws IOException {
        EchoServer server;
        server = new EchoServer();
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        server.start(host, port);
//        synchronized (server) {
//            server.notify();
//        }
    }
}
