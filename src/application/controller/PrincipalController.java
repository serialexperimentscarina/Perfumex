package application.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import application.model.Endereco;
import application.model.Lojista;
import application.model.Usuario;
import application.persistence.EnderecoDao;
import application.persistence.UsuarioDao;
import javafx.event.ActionEvent;

public class PrincipalController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TextField tFieldEmail;
	@FXML
	private TextField tFieldSenha;
	
	@FXML
	public void irParaLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/application/Principal.FXML"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void irParaCadastro(ActionEvent event) throws IOException {
		System.out.println("TESTE");
		Parent root = FXMLLoader.load(getClass().getResource("/application/PrincipalCadastro.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void irParaCliente(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Cliente.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void logar(ActionEvent event) {
		System.out.println("TESTE");
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
				SessaoController c = new SessaoController(u);

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
	
   
	@FXML
	public void irParaCadastroLojista(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Lojista.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void irParaLoja(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Loja.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void irParaProduto(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Produto.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void irADM(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/DashbordADM.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void irparaDashboardLojista(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/DashboardLojista.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void irParaProdutoListagem(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/ProdutoListagem.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

