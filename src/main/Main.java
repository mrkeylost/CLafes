package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.HomePage;
import view.MainMenu;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MainMenu mainMenu = new MainMenu(primaryStage);
		
		//primaryStage.setScene(new Scene(mainMenu.getBp(), 600, 600));
		
		//primaryStage.setScene(new Scene(new RegisterView(this), 600, 600));
		
		String role = "Customer";
		Integer id = 9;
		
		new HomePage(primaryStage, role, id);
		
		//primaryStage.show();
	}

}
