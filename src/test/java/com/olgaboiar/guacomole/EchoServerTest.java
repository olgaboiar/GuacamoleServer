package com.olgaboiar.guacomole;


import cucumber.api.java.an.E;
import gradle.cucumber.ServerProcess;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EchoServerTest {
    EchoClient test_client;
    ServerProcess serverProcess;
    Socket socket;

    @BeforeEach
    public void setup () throws Exception {
        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
        test_client = new EchoClient("localhost", 3333);
    }

    @Test
    void testServerAcceptsClientConnection () throws Exception {
        socket = new Socket();
        Thread.sleep(500);
        socket.connect(new InetSocketAddress("localhost", 3333));
        boolean connected = socket.isConnected();
        Assert.assertTrue(connected);
        socket.close();
    }

    @Test
    void testServerReturnsHelloWhenClientSentHello () throws Exception {
        Thread.sleep(500);
        test_client.connect();
        byte[] client_input = "hello\n".getBytes();
        byte[] server_response = test_client.send(client_input);
        Assert.assertArrayEquals(client_input, server_response);
        test_client.close();
    }

}