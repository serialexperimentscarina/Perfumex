package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("Principal.FXML"));
		Scene scene = new Scene(anchorPane);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Perfumex");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
