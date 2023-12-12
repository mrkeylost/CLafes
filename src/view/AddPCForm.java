package view;

import java.util.Optional;

import controller.PcController;
import javafx.scene.control.TextInputDialog;

public class AddPCForm extends TextInputDialog{
	PcController pcController = new PcController();
	public AddPCForm() {
		
		setTitle("Add PC");
		setHeaderText("Enter new PC Id");
		setContentText("PC Id: ");
		
		Optional<String> result = showAndWait();
		
		result.ifPresent(newPCId -> {
			pcController.addNewPc(newPCId);
		});
	}

}
