package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Control class for the card after an order is finalized
 */


public class PedidoFinalizadoController {
	
	/**
	 * <p> Go back to Marketplace Listing </p>
	 * @param event
	 * @throws IOException
	 * @since 1.0
	 */
	public void voltar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/ProdutoListagem.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
