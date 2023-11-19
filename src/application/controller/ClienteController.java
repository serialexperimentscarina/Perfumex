package application.controller;

import java.io.IOException;
import java.time.LocalDate;

import application.model.Cliente;
import application.model.Endereco;
import application.model.Lojista;
import application.model.Usuario;
import application.persistence.EnderecoDao;
import application.persistence.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ClienteController {

	@FXML
	private TextField tFieldNome;
	@FXML
	private TextField tFieldSobrenome;
	@FXML
	private TextField tFieldCpf;
	@FXML
	private TextField tFieldTelefone;
	@FXML
	private TextField tFieldCep;
	@FXML
	private TextField tFieldRua;
	@FXML
	private TextField tFieldNum;
	@FXML
	private ComboBox<String> cBoxEstado;
	@FXML
	private TextField tFieldCidade;
	@FXML
	private TextField tFieldComplemento;
	@FXML
	private TextField tFieldEmail;
	@FXML
	private TextField tFieldSenha;
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


	public void irParaLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/PrincipalCadastro.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void cadastrarCliente(ActionEvent event) {
		System.out.println("teste");
		//TBA: Validações, verificar campos vazios, ou campos que violam tamanho máximo em banco
		Cliente cliente = new Cliente(Usuario.geraId(), tFieldNome.getText(), tFieldSobrenome.getText(), tFieldEmail.getText(),
				tFieldSenha.getText(), tFieldTelefone.getText(), "Ativo", LocalDate.now(), LocalDate.now(), tFieldCpf.getText());
		Endereco endereco = new Endereco(cliente, tFieldRua.getText(), Integer.parseInt(tFieldNum.getText()), tFieldCep.getText(),
			tFieldComplemento.getText(), cBoxEstado.getValue(), tFieldCidade.getText(), LocalDate.now(), LocalDate.now());
		
		try {
			UsuarioDao uDao = new UsuarioDao();
			uDao.insereCliente(cliente);
			
			EnderecoDao eDao = new EnderecoDao();
			eDao.insererEndereco(endereco);
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Cliente Cadastrado");
			alert.show();
			
			limparCampos();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public void limparCampos() {
		tFieldNome.setText("");
		tFieldSobrenome.setText("");
		tFieldEmail.setText("");
		tFieldSenha.setText("");
		tFieldTelefone.setText("");
		tFieldCpf.setText("");
		tFieldRua.setText("");
		tFieldNum.setText("");
		tFieldCep.setText("");
		tFieldComplemento.setText("");
		tFieldCidade.setText("");
		cBoxEstado.getSelectionModel().select("SP");
	}
}
