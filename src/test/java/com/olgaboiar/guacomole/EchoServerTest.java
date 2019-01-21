package com.olgaboiar.guacomole;

import gradle.cucumber.ServerProcess;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EchoServerTest {
    EchoClient testClient;
    ServerProcess serverProcess;

    @BeforeEach
    public void setUp () throws Exception {
        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
        testClient = new EchoClient("localhost", 3333);
    }

    @AfterEach
    public void tearDown() throws Exception {
        serverProcess.stop();
    }

    @Test
    void testServerReturnsHelloWhenClientSentHello () throws Exception {
        testClient.connect();
        byte[] clientInput = "hello\n".getBytes();
        testClient.send(clientInput);
        byte[] serverResponse = testClient.receive();
        Assert.assertArrayEquals(clientInput, serverResponse);
        testClient.close();
    }

    @Test
    void testServerReturnsByeWhenClientSentBye () throws Exception {
        testClient.connect();
        byte[] clientInput = "Bye\n".getBytes();
        testClient.send(clientInput);
        byte[] serverResponse = testClient.receive();
        Assert.assertArrayEquals(clientInput, serverResponse);
        testClient.close();
    }

}
