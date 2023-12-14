package view;

import java.util.Optional;

import controller.UserController;
import javafx.scene.control.TextInputDialog;

public class ChangeRoleForm extends TextInputDialog{
	
	UserController staff = new UserController();
	
	public ChangeRoleForm(String userId) {
		setTitle("Change Role");
		setHeaderText("Enter new role:");
		setContentText("Role:");
		
		Optional<String> result = showAndWait();
		result.ifPresent(newRole -> {
			staff.ChangeRoleUser(userId, newRole);
			
			
		});
	}

}
