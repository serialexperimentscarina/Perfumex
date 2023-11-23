package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.model.Produto;
import application.persistence.ProdutoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProdutoListagemController {
	
	@FXML
	TableView<Produto> tViewProduto;
	@FXML
	TableColumn<Produto, String> tColumnNome;
	@FXML
	TableColumn<Produto, Double> tColumPreco;
	@FXML
	TableColumn<Produto, Double> tColumnDesconto;
	@FXML
	TableColumn<Produto, String> tColumnDesc;
	@FXML
	TableColumn<Produto, String> tColumnMarca;
	@FXML
	TableColumn<Produto, String> tColumnForn;
    
	private ObservableList<Produto> lista = FXCollections.observableArrayList();
	
	public void initialize() {
        try {
            popularTabela();
        } catch (Exception e) {
            e.printStackTrace();
      
        }
    }
	
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

	private void popularTabela() throws SQLException, ClassNotFoundException {
		ProdutoDao produtoDao = new ProdutoDao();
		ResultSet rs = produtoDao.buscarProdutosCliente();
		
		// Popula lista com resultados do SQL
		while(rs.next()){
			Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getFloat("preco"), rs.getFloat("percentual_desconto"),
					rs.getString("descricao"), rs.getString("marca"), rs.getString("fornecedor"), rs.getInt("quantidade_atual"), 
					rs.getInt("quantidade_minima"), rs.getDate("data_criacao").toLocalDate(), rs.getDate("data_ultima_modificacao").toLocalDate());
			lista.add(produto);
		}  
	
		// Ligar as colunas com atributos dos objetos
		tColumnNome.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome"));
		tColumPreco.setCellValueFactory( new PropertyValueFactory<Produto, Double>("preco"));
		tColumnDesconto.setCellValueFactory( new PropertyValueFactory<Produto, Double>("percentualDesconto"));
		tColumnDesc.setCellValueFactory( new PropertyValueFactory<Produto, String>("descricao"));
		tColumnMarca.setCellValueFactory( new PropertyValueFactory<Produto, String>("marca"));
		tColumnForn.setCellValueFactory( new PropertyValueFactory<Produto, String>("fornecedor"));
		tViewProduto.setItems(lista);
	}
	    

}
