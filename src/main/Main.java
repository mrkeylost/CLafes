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
		
		primaryStage.setScene(new Scene(mainMenu.getBp(), 600, 600));
		
		primaryStage.show();
	}

}
