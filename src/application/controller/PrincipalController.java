package application.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

import application.model.Usuario;
import application.persistence.UsuarioDao;
import javafx.event.ActionEvent;

/**
 * Control class for the initial page
 */


public class PrincipalController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField tFieldEmail;
	@FXML
	private PasswordField tFieldSenha;
	
	/**
	 * <p> Go to Login page </p>
	 * @param event
	 * @throws IOException
	 * @since 1.0
	 */
	@FXML
	public void irParaLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/Principal.FXML"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Go to Register page </p>
	 * @param event
	 * @throws IOException
	 * @since 1.0
	 */
	@FXML
	public void irParaCadastro(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/PrincipalCadastro.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Go to Register Cliente page </p>
	 * @param event
	 * @throws IOException
	 * @since 1.0
	 */
	@FXML
	public void irParaCliente(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Cliente.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Login user </p>
	 * @param event
	 * @since 1.0
	 */
	@FXML
	public void logar(ActionEvent event) {
		String email = tFieldEmail.getText();
		String senha = tFieldSenha.getText();
		
		try {
			UsuarioDao uDao = new UsuarioDao();
			Usuario u = uDao.buscaUsuarioLogin(email, senha);
			if (u == null) {
				Alert alert = new Alert(AlertType.ERROR, "Email ou senha incorreto(a)");
				alert.show();
			} else
			{
				SessaoController c = new SessaoController();
				c.logar(u);

				if (SessaoController.tipo == "cliente") {
					irParaProdutoListagem(event);
				} else {
					irparaDashboardLojista(event);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * <p> Go to Register Lojista page </p>
	 * @param event
	 * @throws IOException
	 * @since 1.0
	 */
	@FXML
	public void irParaCadastroLojista(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Lojista.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Go to Dashboard after Lojista login </p>
	 * @param event
	 * @throws IOException
	 * @since 1.0
	 */
	public void irparaDashboardLojista(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/dashboard.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Go to Marketplace listing after Cliente login </p>
	 * @param event
	 * @throws IOException
	 * @since 1.0
	 */
	public void irParaProdutoListagem(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/ProdutoListagem.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
