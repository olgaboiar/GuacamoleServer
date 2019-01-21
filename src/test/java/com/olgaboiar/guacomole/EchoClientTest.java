package com.olgaboiar.guacomole;

import gradle.cucumber.ServerProcess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EchoClientTest {
    ServerProcess serverProcess;
    EchoClient testClient;

    @BeforeEach
    public void setUp () throws Exception {
        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
    }

    @AfterEach
    public void tearDown() {
        serverProcess.stop();
    }

    @Test
    void testClientConnectsToServer () throws Exception {
        testClient = new EchoClient("localhost", 3333);
        testClient.connect();
        boolean connected = testClient.isConnected();
        assertTrue(connected);
        testClient.close();
    }

}