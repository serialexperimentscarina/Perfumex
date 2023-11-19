package application.controller;

import java.io.IOException;
import java.time.LocalDate;

import application.model.Endereco;
import application.model.Lojista;
import application.model.Produto;
import application.model.Usuario;
import application.persistence.EnderecoDao;
import application.persistence.ProdutoDao;
import application.persistence.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ProdutoController {
	
	@FXML
	private TextField tFieldNome;
	@FXML
	private TextField tFieldForn;
	@FXML
	private TextField tFieldMarca;
	@FXML
	private TextField tFieldQAtual;
	@FXML
	private TextField tFieldQMin;
	@FXML
	private TextArea tAreaDesc;
	@FXML
	private TextField tFieldValor;
	@FXML
	private TextField tFieldDesco;
	
	public void cadastrarProduto(ActionEvent event) {
		//TBA: Validações, verificar campos vazios, ou campos que violam tamanho máximo em banco
		Produto produto = new Produto(Produto.geraId(), tFieldNome.getText(), Float.parseFloat(tFieldValor.getText()), Float.parseFloat(tFieldDesco.getText()),
				tAreaDesc.getText(), tFieldMarca.getText(), tFieldForn.getText(), Integer.parseInt(tFieldQAtual.getText()), Integer.parseInt(tFieldQMin.getText()), 
				LocalDate.now(), LocalDate.now());
		
		try {
			ProdutoDao pDao = new ProdutoDao();
			pDao.inserirProduto(produto);
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Produto Cadastrado");
			alert.show();
			
			limpar(event);;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void voltar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/DashboardLojista.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void limpar(ActionEvent event) {
		tFieldNome.setText("");
		tFieldForn.setText("");
		tFieldMarca.setText("");
		tFieldQAtual.setText("");
		tFieldQMin.setText("");
		tAreaDesc.setText("");
		tFieldValor.setText("");
		tFieldDesco.setText("");
	}
	
}
