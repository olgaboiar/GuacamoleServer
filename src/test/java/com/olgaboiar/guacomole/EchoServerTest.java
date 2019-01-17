package com.olgaboiar.guacomole;

import gradle.cucumber.ServerProcess;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EchoServerTest {
    EchoClient testClient;
    ServerProcess serverProcess;
    Socket socket;

    @BeforeEach
    public void setup () throws Exception {
        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
        testClient = new EchoClient("localhost", 3333);
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
        testClient.connect();
        byte[] clientInput = "hello\n".getBytes();
        byte[] serverResponse = testClient.send(clientInput);
        Assert.assertArrayEquals(clientInput, serverResponse);
        testClient.close();
    }

    @Test
    void testServerReturnsByeWhenClientSentBye () throws Exception {
        Thread.sleep(500);
        testClient.connect();
        byte[] clientInput = "Bye\n".getBytes();
        byte[] serverResponse = testClient.send(clientInput);
        Assert.assertArrayEquals(clientInput, serverResponse);
        testClient.close();
    }

}
