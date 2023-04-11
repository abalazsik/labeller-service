package com.mycompany.labeller.javafx.client.tabs;

import com.mycompany.labeller.javafx.client.LabellerService;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ador
 */
public class LabellerTab extends BaseLabellerTab {

    private LabellerService service;
    
    public LabellerTab() {
        super("Labeller", false);
    }

    @Override
    public void initGui() {
        TextField textField = new TextField();
        Button button = new Button("Search");

        HBox hBox = new HBox(textField, button);

        Pane pane = new Pane(new Label("Results..."));

        VBox vBox = new VBox(hBox, pane);

        BorderPane borderPane = new BorderPane(vBox);
        BorderPane.setMargin(borderPane, new Insets(30));

        this.setContent(borderPane);
    }

}
