package com.olgaboiar.guacomole;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient implements Closeable{
    String host;
    int port;
    Socket socket;
    int connectionAttempts;
    int maxConnectionAttempts;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.maxConnectionAttempts = 20;
        this.connectionAttempts = 0;
    }

    public void connect() throws IOException, InterruptedException {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            if (this.connectionAttempts < this.maxConnectionAttempts) {
                Thread.sleep(500);
                this.connectionAttempts ++;
                connect();
            }
        }
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
