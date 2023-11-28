package application.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Control class for the Lojista registration page
 */


public class LojistaController {
	@FXML
	private TextField tFieldNome;
	@FXML
	private TextField tFieldSobrenome;
	@FXML
	private TextField tFieldCNPJ;
	@FXML
	private TextField tFieldTelefone;
	@FXML
	private TextField tFieldCEP;
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
	
	@FXML
	private Button btnCadastrarLoja;
	
	/**
	 * <p> Initialize resources </p>
	 * @since 1.0
	 */
	public void initialize() {
		cBoxEstado.getItems().clear();
		cBoxEstado.getItems().addAll("AC", "AL", "AP", "AP", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
				"RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF");
		cBoxEstado.getSelectionModel().select("SP");
	}
	/**
	 * <p> Go back to Register main page </p>
	 * @param event
	 * @since 1.0
	 */
	public void irParaCadastro(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/PrincipalCadastro.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Register Lojista and Endereco </p>
	 * @param event
	 * @since 1.0
	 */
	public void cadastrarLojista(ActionEvent event) {		
		try {
			if (!validarCampos()) {
				return;
			}
			Lojista lojista = new Lojista(Usuario.geraId(), tFieldNome.getText(), tFieldSobrenome.getText(), tFieldEmail.getText(),
					tFieldSenha.getText(), tFieldTelefone.getText(), "Ativo", LocalDate.now(), LocalDate.now(), tFieldCNPJ.getText());
			Endereco endereco = new Endereco(lojista, tFieldRua.getText(), Integer.parseInt(tFieldNum.getText()), tFieldCEP.getText(),
				tFieldComplemento.getText(), cBoxEstado.getValue(), tFieldCidade.getText(), LocalDate.now(), LocalDate.now());
			
			UsuarioDao uDao = new UsuarioDao();
			uDao.insereLojista(lojista);
			
			EnderecoDao eDao = new EnderecoDao();
			eDao.insererEndereco(endereco);
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Lojista Cadastrado");
			alert.show();
			
			limparCampos();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * <p> Clean all fields </p>
	 * @since 1.0
	 */
	public void limparCampos() {
		tFieldNome.setText("");
		tFieldSobrenome.setText("");
		tFieldEmail.setText("");
		tFieldSenha.setText("");
		tFieldTelefone.setText("");
		tFieldCNPJ.setText("");
		tFieldRua.setText("");
		tFieldNum.setText("");
		tFieldCEP.setText("");
		tFieldComplemento.setText("");
		tFieldCidade.setText("");
		cBoxEstado.getSelectionModel().select("SP");
	}
	
	/**
	 * <p> Validate all fields </p>
	 * @return boolean
	 * @since 1.0
	 */
	private boolean validarCampos() throws ClassNotFoundException, SQLException {
		if(tFieldNome.getText().length() == 0 || tFieldSobrenome.getText().length() == 0 || tFieldEmail.getText().length() == 0
				|| tFieldSenha.getText().length() == 0 || tFieldTelefone.getText().length() == 0 || tFieldRua.getText().length() == 0
				|| tFieldNum.getText().length() == 0 || tFieldCEP.getText().length() == 0 || tFieldCidade.getText().length() == 0 ||
						tFieldCNPJ.getText().length() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "Um ou mais campos vazios");
			alert.show();
			return false;
		}
		
		if(tFieldNome.getText().length() > 50) {
			Alert alert = new Alert(AlertType.ERROR, "Nome ultrapassa 50 caracteres");
			alert.show();
			return false;
		}
		if(tFieldSobrenome.getText().length() > 50) {
			Alert alert = new Alert(AlertType.ERROR, "Sobrenome ultrapassa 50 caracteres");
			alert.show();
			return false;
		}
		
		UsuarioDao uDao = new UsuarioDao();
		if(!uDao.checarDispEmail(tFieldEmail.getText())) {
			Alert alert = new Alert(AlertType.ERROR, "Email já cadastrado");
			alert.show();
			return false;
		}
		
		if(tFieldEmail.getText().length() > 100) {
			Alert alert = new Alert(AlertType.ERROR, "Email ultrapassa 100 caracteres");
			alert.show();
			return false;
		}
		
		if(tFieldSenha.getText().length() > 50 || tFieldSenha.getText().length() < 5) {
			Alert alert = new Alert(AlertType.ERROR, "Senha deve ter entre 5 e 50 caracteres");
			alert.show();
			return false;
		}
		
		if(tFieldTelefone.getText().length() < 8) {
			Alert alert = new Alert(AlertType.ERROR, "Telefone deve ter ao menos 8 caracteres");
			alert.show();
			return false;
		}
		
		if (Pattern.matches("[a-zA-Z]+", tFieldTelefone.getText())) {
			Alert alert = new Alert(AlertType.ERROR, "Telefone deve conter apenas caracteres numéricos");
			alert.show();
			return false;
		}
		
		if(tFieldRua.getText().length() > 100) {
			Alert alert = new Alert(AlertType.ERROR, "Rua ultrapassa 100 caracteres");
			alert.show();
			return false;
		}
		
		if(Integer.parseInt(tFieldNum.getText()) <= 0) {
			Alert alert = new Alert(AlertType.ERROR, "Número não pode ser 0 ou negativo");
			alert.show();
			return false;
		}
		
		if (Pattern.matches("[a-zA-Z]+", tFieldNum.getText())) {
			Alert alert = new Alert(AlertType.ERROR, "Número deve conter apenas caracteres numéricos");
			alert.show();
			return false;
		}
		
		if(tFieldCEP.getText().length() != 8) {
			Alert alert = new Alert(AlertType.ERROR, "CEP deve conter 8 caracteres");
			alert.show();
			return false;
		}
		
		if (Pattern.matches("[a-zA-Z]+", tFieldCEP.getText())) {
			Alert alert = new Alert(AlertType.ERROR, "CEP deve conter apenas caracteres numéricos");
			alert.show();
			return false;
		}
		
		if(tFieldCidade.getText().length() > 50) {
			Alert alert = new Alert(AlertType.ERROR, "Cidade ultrapassa 50 caracteres");
			alert.show();
			return false;
		}
		
		if (Pattern.matches("[a-zA-Z]+", tFieldCNPJ.getText())) {
			Alert alert = new Alert(AlertType.ERROR, "CNPJ deve conter apenas caracteres numéricos");
			alert.show();
			return false;
		}
		
		if(tFieldCNPJ.getText().length() != 14) {
			Alert alert = new Alert(AlertType.ERROR, "CNPJ deve conter 14 caracteres");
			alert.show();
			return false;
		}
		
		return true;
	}
}


