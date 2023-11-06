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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LojistaController {
	@FXML
	private TextField tFieldnomelojista;
	@FXML
	private TextField tFieldsobrenomelojista;
	@FXML
	private TextField tFieldCNPJ;
	@FXML
	private TextField tFieldTlefone;
	@FXML
	private TextField tFieldCEPLojista;
	@FXML
	private TextField tFieldEndLojista;
	@FXML
	private ComboBox<String> cBoxEstado;
	@FXML
	private TextField txtCidadeLojista;
	@FXML
	private TextField tFieldBairroLojista;
	@FXML
	private TextField tFieldEmail;
	@FXML
	private TextField tFieldSenha;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnVoltar;
	
	@FXML
	private Button btnCadastrarLoja;
	
	public void initialize() {
		cBoxEstado.getItems().clear();
		cBoxEstado.getItems().addAll("AC", "AL", "AP", "AP", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
				"RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF");
		cBoxEstado.getSelectionModel().select("SP");
	}


	public void irParaLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/PrincipalCadastro.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}


