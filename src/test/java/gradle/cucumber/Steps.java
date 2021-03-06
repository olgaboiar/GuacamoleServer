package gradle.cucumber;

import com.olgaboiar.guacomole.EchoClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class Steps {
    ServerProcess serverProcess;
    private EchoClient echoClient;

    @Given("Server process is started")
    public void the_server_is_started() throws Throwable {
        String build = "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
    }

    @When("Client connects to server")
    public void client_connects_to_server() throws Throwable {
        echoClient = new EchoClient("localhost", 3333);
        echoClient.connect();
    }

    @Then("Server echoes client input")
    public void server_responds_with_the_same_string() throws Throwable {
        byte[] clientInput = "hello\n".getBytes();
        echoClient.send(clientInput);
        byte[] serverResponse = echoClient.receive();
        Assert.assertArrayEquals(clientInput, serverResponse);
    }

}
