package application.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import application.model.Carrinho;
import application.model.Cliente;
import application.model.Endereco;
import application.model.Item;
import application.model.Pedido;
import application.persistence.CarrinhoDao;
import application.persistence.EnderecoDao;
import application.persistence.ItemDao;
import application.persistence.PedidoDao;
import application.persistence.UsuarioDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PedidoController {
	
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
	@FXML
	private Label lblDestin;
	@FXML
	private Label lblTelefone;
	@FXML
	private Label lblCPF;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblLogradouro;
	@FXML
	private Label lblNum;
	@FXML
	private Label lblComplemento;
	@FXML
	private Label lblEstado;
	@FXML
	private ComboBox<String> cBoxParcelas;
	 @FXML
	 ToggleGroup pagamento;
	
	private Carrinho carrinhoAtual;
	private ObservableList<Item> itens = FXCollections.observableArrayList();
	
	public void initialize() {
        try {
        	popularCBox();
        	inicializarCarrinho();
            popularTabela();
            popularCampos();
        } catch (Exception e) {
            e.printStackTrace();
      
        }
    }
	
	
	private void popularCBox() {
		cBoxParcelas.getItems().clear();
		cBoxParcelas.getItems().addAll("1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x", "11x", "12x");
		cBoxParcelas.getSelectionModel().select("1x");
		
	}


	private void popularCampos() throws ClassNotFoundException, SQLException {
		EnderecoDao eDao = new EnderecoDao();
		Endereco end = eDao.buscarEndereco(SessaoController.usuario);
		
		UsuarioDao uDao = new UsuarioDao();
		String cpf = uDao.buscarCPF(SessaoController.usuario);
		
		lblDestin.setText(SessaoController.usuario.getNome() + " " + SessaoController.usuario.getSobrenome());
		lblTelefone.setText(SessaoController.usuario.getTelefone());
		lblCPF.setText(cpf);
		lblEmail.setText(SessaoController.usuario.getEmail());
		lblLogradouro.setText(end.getRua());
		lblNum.setText(String.valueOf(end.getNumero()));
		lblComplemento.setText(end.getComplemento());
		lblEstado.setText(end.getEstado());
	}


	private void inicializarCarrinho() throws ClassNotFoundException, SQLException {
		CarrinhoDao cDao = new CarrinhoDao();
		carrinhoAtual = cDao.buscarCarrinhoAtual(SessaoController.usuario);
		
		lblTotalValor.setText("R$ " + carrinhoAtual.getTotal());
	}


	private void popularTabela() throws ClassNotFoundException, SQLException {
		ItemDao iDao = new ItemDao();
		itens = iDao.listarItems(carrinhoAtual);
		
		tColumnProd.setCellValueFactory(item -> 
        new SimpleStringProperty(item.getValue().getProduto().getNome()));
		tColumnQuant.setCellValueFactory( new PropertyValueFactory<Item, Integer>("quantidadeItens"));
		tColumnSubtotal.setCellValueFactory( new PropertyValueFactory<Item, Double>("subtotal"));
		tViewCarrinho.setItems(itens);
		
	}


	public void voltar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Carrinho.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void finalizar(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
		RadioButton selectedRadioButton = (RadioButton) pagamento.getSelectedToggle();
		String pagto = selectedRadioButton.getText();
		
		Pedido ped = new Pedido(carrinhoAtual, pagto, "Em preparo", LocalDate.now(), LocalDate.now());
		PedidoDao pDao = new PedidoDao();
		pDao.inserirPedido(ped);
		
		Carrinho car = new Carrinho(Carrinho.geraId(), 0, 0, LocalDate.now(), LocalDate.now());
		CarrinhoDao cDao = new CarrinhoDao();
		cDao.criarNovoCarrinho(car, SessaoController.usuario);
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/PedidoFinalizado.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
