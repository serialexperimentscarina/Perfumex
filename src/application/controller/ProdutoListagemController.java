package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProdutoListagemController {
	
	public void deslogar(ActionEvent event) throws IOException {
		SessaoController sessao = new SessaoController();
		sessao.deslogar();
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/Principal.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void irParaCarrinho(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Carrinho.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
