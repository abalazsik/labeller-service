package com.mycompany.labeller.javafx.client;

import java.util.concurrent.Callable;
import javafx.application.Application;
import picocli.CommandLine;

/**
 *
 * @author ador
 */
@CommandLine.Command
public class LabellerJavafxClient implements Callable {

    @CommandLine.Option(names = {"--url"}, description = "URL of the server")
    private String serverUrl = "http://localhost:8080";

    @CommandLine.Option(names = {"--username", "-u"}, description = "Username to login with. If omitted, the anonymus role will be selected")
    private String username;

    @CommandLine.Option(names = {"--password", "-p"}, description = "Password to login with. If omitted, the anonymus role will be selected")
    private String password;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new LabellerJavafxClient()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {

        Application.launch(LabellerApplication.class, new String[]{});

        return null;
    }
}
