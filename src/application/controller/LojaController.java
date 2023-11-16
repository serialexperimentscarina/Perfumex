package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LojaController {

	@FXML
	private TextField tFieldNomeLoja;
	@FXML
	private TextField tFieldRamo;
	@FXML
	private TextField tFieldTelefoneLoja;
	@FXML
	private DatePicker dDataCriacao;
	@FXML
	private TextField tFieldCep;
	@FXML
	private TextField tFieldEndereco;
	@FXML
	private ComboBox<String> cBoxEstado;
	@FXML
	private TextField tFieldCidadeLoja ;
	@FXML
	private TextField tFieldEmail;
	@FXML
	private TextArea ttextoDescricao;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnVoltar;
	
	public void initialize() {
		cBoxEstado.getItems().clear();
		cBoxEstado.getItems().addAll("AC", "AL", "AP", "AP", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
				"RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF");
		cBoxEstado.getSelectionModel().select("SP");
	}


	public void irParaCadastroLojista(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Lojista.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void irParaProduto(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Produto.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}


