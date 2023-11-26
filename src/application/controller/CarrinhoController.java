package application.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Item;
import application.model.Produto;
import application.persistence.CarrinhoDao;
import application.persistence.ItemDao;
import application.persistence.ProdutoDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CarrinhoController {
	
	@FXML
	private TableView<Item> tViewCarrinho;
	@FXML
	private TableColumn<Item, String> tColumnProd;
	@FXML
	private TableColumn<Item, Integer> tColumnQuant;
	@FXML
	private TableColumn<Item, Double> tColumnSubtotal;
	@FXML
	private Label lblTotalValor;
	
	private Carrinho carrinhoAtual;
	private ObservableList<Item> itens = FXCollections.observableArrayList();
	
	public void initialize() {
        try {
        	inicializarCarrinho();
            popularTabela();
        } catch (Exception e) {
            e.printStackTrace();
      
        }
    }
	
	private void inicializarCarrinho() throws ClassNotFoundException, SQLException {
		CarrinhoDao cDao = new CarrinhoDao();
		carrinhoAtual = cDao.buscarCarrinhoAtual(SessaoController.usuario);
		
		lblTotalValor.setText("R$ " + carrinhoAtual.getTotal());
	}

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
	
	private void popularTabela() throws SQLException, ClassNotFoundException {
		ItemDao iDao = new ItemDao();
		itens = iDao.listarItems(carrinhoAtual);
		
		tColumnProd.setCellValueFactory(item -> 
        new SimpleStringProperty(item.getValue().getProduto().getNome()));
		tColumnQuant.setCellValueFactory( new PropertyValueFactory<Item, Integer>("quantidadeItens"));
		tColumnSubtotal.setCellValueFactory( new PropertyValueFactory<Item, Double>("subtotal"));
		tViewCarrinho.setItems(itens);
	}

}
