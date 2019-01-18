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
//        Thread.sleep(500);
//        boolean connected = false;
//        while (! connected) {
//            try {
//                echoClient.connect();
//                connected = true;
//            } catch (Exception e) {
//                System.out.println("no");
//                Thread.sleep(100);
//            }
//
//        }

        echoClient.connect();
    }

    @Then("Server echoes client input")
    public void server_responds_with_the_same_string() throws Throwable {
        byte[] clientInput = "hello\n".getBytes();
        byte[] serverResponse = echoClient.send(clientInput);
        Assert.assertArrayEquals(clientInput, serverResponse);
    }

}
