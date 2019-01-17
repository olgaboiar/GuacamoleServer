package com.olgaboiar.guacomole;

import gradle.cucumber.ServerProcess;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class EchoClientTest {
//    EchoClient test_client;
    ServerProcess serverProcess;
    Socket socket;

    @BeforeEach
    public void setup () throws Exception {
        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
//        test_client = new EchoClient("localhost", 8080);
    }

    @Test
    void testClientConnectsToServer () throws Exception {
//        setup();
//        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
//        serverProcess = ServerProcess.start(build);
        socket = new Socket();
        Thread.sleep(1000);
        socket.connect(new InetSocketAddress("localhost", 3333));
        boolean connected = socket.isConnected();
        assertTrue(connected);
        socket.close();
    }

}