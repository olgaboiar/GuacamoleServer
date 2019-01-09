package test.java;
import static org.junit.jupiter.api.Assertions.assertEquals;

import main.TcpClient;
import org.junit.jupiter.api.Test;

public class GuacamoleEchoServerAcceptanceTest {
//    @BeforeEach
//    start server
//    @AfterEach
//    stop server
    @Test
    void whenClientSendsString_ServerRespondsIdenticalString() throws Exception {
        try (var client = TcpClient.forLocalServer()) {
            client.connect();
            var clientInput = "hello";
            var serverResponse = client.echo(clientInput);

            assertEquals(clientInput, serverResponse);
        }
    }
}
