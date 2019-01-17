package com.olgaboiar.guacomole;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient implements Closeable{
    String host;
    int port;
    Socket socket;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException, InterruptedException {
        socket = new Socket();
        Thread.sleep(500);
//        synchronized (socket) {
//            socket.wait();
//        }
        socket.connect(new InetSocketAddress(host, port));
    }

    public byte[] send(byte[] data) throws IOException {
        socket.getOutputStream().write(data);
        byte[] readBuffer = new byte[data.length];
        socket.getInputStream().read(readBuffer);
        return readBuffer;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
