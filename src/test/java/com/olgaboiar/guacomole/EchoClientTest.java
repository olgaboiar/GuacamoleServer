package com.olgaboiar.guacomole;

import gradle.cucumber.ServerProcess;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class EchoClientTest {
    ServerProcess serverProcess;
    Socket socket;

    @BeforeEach
    public void preTest () throws Exception {
        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
    }

    @AfterEach
    public void postTest() throws Exception {
        serverProcess.stop();
    }

    @Test
    void testClientConnectsToServer () throws Exception {
        socket = new Socket();
        Thread.sleep(1000);
        socket.connect(new InetSocketAddress("localhost", 3333));
        boolean connected = socket.isConnected();
        assertTrue(connected);
        socket.close();
    }

}