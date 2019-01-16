package gradle.cucumber;

import java.io.IOException;

public class ServerProcess {
    Process serverProcess;

    public static ServerProcess start(String build) throws IOException{
        return new ServerProcess(build);
    }

    private ServerProcess(String build) throws IOException {
        serverProcess = new ProcessBuilder()
                .command("java", "-jar", build, "localhost", "3333")
                .inheritIO()
                .start();
    }
}
