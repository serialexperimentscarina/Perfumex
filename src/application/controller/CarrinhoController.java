package application.controller;

import java.io.IOException;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Item;
import application.persistence.CarrinhoDao;
import application.persistence.ItemDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Control class for the Carrinho page
 */

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
	
	/**
	 * Current user's cart
	 */
	private Carrinho carrinhoAtual;
	/**
	 * List of itens in cart
	 */
	private ObservableList<Item> itens = FXCollections.observableArrayList();
	
	/**
	 * <p> Initialize resources </p>
	 * @since 1.0
	 */
	public void initialize() {
        try {
        	inicializarCarrinho();
            popularTabela();
        } catch (Exception e) {
        	Alert alert= new Alert(AlertType.ERROR, "Um problema ocorreu ao tentar finalizar o pedido");
			alert.show();
      
        }
    }
	
	/**
	 * <p> Load user's current Carrinho </p>
	 * @since 1.0
	 */
	private void inicializarCarrinho() throws ClassNotFoundException, SQLException {
		CarrinhoDao cDao = new CarrinhoDao();
		carrinhoAtual = cDao.buscarCarrinhoAtual(SessaoController.usuario);
		
		lblTotalValor.setText("R$ " + carrinhoAtual.getTotal());
	}

	/**
	 * <p> Go to Marketplace listing page </p>
	 * @param event
	 * @since 1.0
	 */
	public void voltar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/ProdutoListagem.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Make an order </p>
	 * @param event
	 * @since 1.0
	 */
	public void finalizar(ActionEvent event) throws IOException {
		if (itens.size() == 0) {
			Alert alert= new Alert(AlertType.ERROR, "Não é possível finalizar um pedido sem itens");
			alert.show();
			return;
		}
		Parent root = FXMLLoader.load(getClass().getResource("/application/Pedido.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Populate TableView </p>
	 * @param None
	 * @since 1.0
	 */
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
