package com.olgaboiar.guacomole;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient implements Closeable{
    String host;
    int port;
    Socket socket;
    int connectionAttempts;
    byte[] readBuffer;
    int MAX_CONNECTION_ATTEMPTS = 20;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.connectionAttempts = 0;
    }

    public void connect() throws InterruptedException {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            if (this.connectionAttempts < MAX_CONNECTION_ATTEMPTS) {
                Thread.sleep(500);
                this.connectionAttempts ++;
                connect();
            }
        }
    }

    public void send(byte[] data) throws IOException {
        socket.getOutputStream().write(data);
        readBuffer = new byte[data.length];
    }

    public byte[] receive() throws IOException {
        socket.getInputStream().read(readBuffer);
        return readBuffer;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
