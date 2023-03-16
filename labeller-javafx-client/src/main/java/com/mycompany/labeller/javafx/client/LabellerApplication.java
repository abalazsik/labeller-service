package com.mycompany.labeller.javafx.client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ador
 */
public class LabellerApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Labeller JavaFX Application");

        Group root = new Group();
        Scene scene = new Scene(root, 640, 480);
        
        stage.setScene(scene);
        
        stage.show();
    }

}
