package application.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Item;
import application.model.Produto;
import application.persistence.ProdutoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CarrinhoController {
	
	private TableView<Item> tViewCarrinho;
	
	public void voltar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/ProdutoListagem.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void finalizar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Pedido.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
