package gradle.cucumber;

import com.olgaboiar.guacomole.EchoClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class Steps {
    ServerProcess serverProcess;
    private EchoClient echo_client;

    @Given("Server process is started")
    public void the_server_is_started() throws Throwable {
        serverProcess = ServerProcess.start();
    }

    @When("Client connects to server")
    public void client_connects_to_server() throws Throwable {
        echo_client = new EchoClient("localhost", 3333);
        echo_client.connect();
    }

    @Then("Server echoes client input")
    public void server_responds_with_the_same_string() throws Throwable {
        byte[] client_input = "hello\n".getBytes();
        byte[] server_response = echo_client.send(client_input);
        if (client_input == server_response) {
        }
        Assert.assertArrayEquals(client_input, server_response);
    }

}
