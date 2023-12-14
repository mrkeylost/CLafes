package view;

import java.util.Optional;

import controller.PcController;
import javafx.scene.control.TextInputDialog;

public class UpdatePCForm extends TextInputDialog{
	PcController pcController = new PcController();
	public UpdatePCForm(String id) {
		setTitle("Update PC");
		setHeaderText("Enter new Pc Status");
		setContentText("PC Status: ");
		
		Optional<String> result = showAndWait();
		
		result.ifPresent(newStatus -> {
			pcController.updatePC(newStatus, id);
		});
	}

}
