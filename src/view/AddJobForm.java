package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Dialog;

import java.util.Optional;

import controller.JobController;
import javafx.scene.control.ButtonType;


public class AddJobForm extends Dialog<String[]> {

    private TextField userIdField;
    private TextField pcIdField;
    
    JobController jobController = new JobController();
    
    public AddJobForm() {
    	
        setTitle("Add Job Form");

        Label userIdLabel = new Label("User ID:");
        Label pcIdLabel = new Label("PC ID:");
        userIdField = new TextField();
        pcIdField = new TextField();

        GridPane grid = new GridPane();
        grid.add(userIdLabel, 1, 1);
        grid.add(userIdField, 2, 1);
        grid.add(pcIdLabel, 1, 2);
        grid.add(pcIdField, 2, 2);

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
            	return new String[]{userIdField.getText(), pcIdField.getText()};
            }
            return null;
        });
        
        Optional<String[]> result = showAndWait();

        result.ifPresent(data -> {
            String userId = data[0];
            String pcId = data[1];
            
            jobController.addStaffJob(userId, pcId);
        });
    }
}
