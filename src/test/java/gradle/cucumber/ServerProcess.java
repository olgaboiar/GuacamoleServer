package gradle.cucumber;

import java.io.IOException;

public class ServerProcess {
    Process serverProcess;

    public static ServerProcess start() throws IOException{
        return new ServerProcess();
    }

    private ServerProcess() throws IOException {
        serverProcess = new ProcessBuilder()
                .command("java", "-jar", "build/libs/com.olgaboiar.guacomole-1.0-SNAPSHOT.jar", "localhost", "3333")
                .inheritIO()
                .start();
    }
}
