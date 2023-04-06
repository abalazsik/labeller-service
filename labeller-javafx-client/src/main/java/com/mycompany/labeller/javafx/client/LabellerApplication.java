package com.mycompany.labeller.javafx.client;

import com.mycompany.labeller.javafx.client.tabs.LabellerTab;
import com.mycompany.labeller.javafx.client.tabs.LabelsTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author ador
 */
public class LabellerApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Labeller JavaFX Application");

        TabPane tabPane = new TabPane(
                new LabellerTab(),
                new LabelsTab()
        );

        Scene scene = new Scene(tabPane, 640, 480);

        stage.setScene(scene);

        stage.show();
    }

}
