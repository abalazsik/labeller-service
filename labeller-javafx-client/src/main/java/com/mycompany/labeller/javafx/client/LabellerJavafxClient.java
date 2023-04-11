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

    @CommandLine.Option(names = {"--host"}, description = "Server host. default: localhost")
    private String host = "localhost";
    
    @CommandLine.Option(names = {"--port"}, description = "Server port. default: 10002")
    private int port = 10002;

    @CommandLine.Option(names = {"--username", "-u"}, description = "Username to login with. If omitted, the anonymus role will be selected")
    private String username = null;

    @CommandLine.Option(names = {"--password", "-p"}, description = "Password to login with. If omitted, the anonymus role will be selected")
    private String password = null;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new LabellerJavafxClient()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {

        LabellerService.init(host, port);
        
        if (username != null && password != null) {
            LabellerService.INSTANCE.setCredentials(username, password);
        }
        
        Application.launch(LabellerApplication.class, new String[]{});

        return null;
    }
}
