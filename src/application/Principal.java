package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Perfumex
 * @author Byanca Matos
 * @author Carina Gonçalves Barroso
 */

public class Principal extends Application {
	
	/**
	 * <p> Start method </p>
	 * @param Stage primaryStage
	 * @return Void
	 * @since 1.0
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(this.getClass().getResource("Principal.FXML"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Perfumex");
		Image icon = new Image(getClass().getResource("/assets/Icon.png").toExternalForm());
		primaryStage.getIcons().add(icon);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * <p> Main method </p>
	 * @param String[] args
	 * @return Void
	 * @since 1.0
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
