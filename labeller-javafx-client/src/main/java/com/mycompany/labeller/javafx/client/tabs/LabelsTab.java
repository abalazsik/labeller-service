package com.mycompany.labeller.javafx.client.tabs;

import com.mycompany.labeller.javafx.client.LabellerService;
import com.mycompany.labeller.javafx.client.model.JFXLabel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ador
 */
public class LabelsTab extends BaseLabellerTab {

    private LabellerService service;
    private ObservableList<JFXLabel> data = FXCollections.observableArrayList();
    private static final int LIMIT = 20;

    private TableView<JFXLabel> table;
    private Button moreButton;

    public LabelsTab() {
        super("Labels", false);
    }

    @Override
    public void initGui() {
        TableColumn idColumn = new TableColumn("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<JFXLabel, Long>("id"));
        TableColumn nameColumn = new TableColumn("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<JFXLabel, String>("name"));
        TableColumn descriptionColumn = new TableColumn("description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<JFXLabel, String>("description"));
        TableColumn techicalColumn = new TableColumn("technical");
        techicalColumn.setCellValueFactory(new PropertyValueFactory<JFXLabel, Boolean>("technical"));

        table = new TableView<JFXLabel>();
        
        table.getColumns().addAll(
                idColumn, nameColumn, descriptionColumn, techicalColumn
        );

        table.setEditable(false);

        /*
        table.setItems(FXCollections.observableArrayList(
        ));
        */

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(table);
        
        moreButton = new Button("More");
        
        moreButton.setOnAction(e -> {
            fetchMore();
        });
        
        borderPane.setBottom(moreButton);
        BorderPane.setMargin(borderPane, new Insets(30));

        this.setContent(borderPane);
    }
    
    private void fetchMore() {
    
    }
}
