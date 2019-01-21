package com.olgaboiar.guacomole;

import gradle.cucumber.ServerProcess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerRunnerTest {
    ServerProcess serverProcess;
    Socket socket;

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
    void testServerIsReachable () throws Exception {
        InetAddress server = InetAddress.getByName("localhost");
        boolean reachable = server.isReachable(3333);
        assertTrue(reachable);

    }

}