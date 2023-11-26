package application.controller;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.model.Carrinho;
import application.model.Item;
import application.model.Produto;
import application.persistence.CarrinhoDao;
import application.persistence.ItemDao;
import application.persistence.ProdutoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextInputControl;

public class ProdutoListagemController implements Initializable{
	
    @FXML
    private javafx.scene.control.TextField tFieldPesquisar= new javafx.scene.control.TextField();
	@FXML
	private TableView<Produto> tViewProduto;
	@FXML
	private TableColumn<Produto, String> tColumnNome;
	@FXML
	private TableColumn<Produto, Double> tColumPreco;
	@FXML
	private TableColumn<Produto, Double> tColumnDesconto;
	@FXML
	private TableColumn<Produto, String> tColumnDesc;
	@FXML
	private TableColumn<Produto, String> tColumnMarca;
	@FXML
	private TableColumn<Produto, String> tColumnForn;
	@FXML
	private TableColumn<Produto, Void> tColumnActions;
	
	private Carrinho carrinhoAtual;	
    
	private ObservableList<Produto> lista = FXCollections.observableArrayList();
    private FilteredList<Produto> filteredLista;

	public void initialize(URL location, ResourceBundle resources) {
        try {
        	inicializarCarrinho();
            popularTabela();
            addToCartButton();
            
            filteredLista = new FilteredList<>(lista, p -> true);
            tFieldPesquisar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredLista.setPredicate(produto -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    return String.valueOf(produto.getId()).contains(lowerCaseFilter)
                            || produto.getNome().toLowerCase().contains(lowerCaseFilter)
                            || produto.getDescricao().toLowerCase().contains(lowerCaseFilter)
                            || produto.getMarca().toLowerCase().contains(lowerCaseFilter)
                            || produto.getFornecedor().toLowerCase().contains(lowerCaseFilter);
                          
                });
            });
            
            tViewProduto.setItems(filteredLista);
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
		stage.setUserData(carrinhoAtual);
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
	
	private void inicializarCarrinho() throws ClassNotFoundException, SQLException {
		CarrinhoDao cDao = new CarrinhoDao();
		carrinhoAtual = cDao.buscarCarrinhoAtual(SessaoController.usuario);
		
	}
	
	private void addToCartButton() {
        Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>> cellFactory = new Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>>() {
            @Override
            public TableCell<Produto, Void> call(final TableColumn<Produto, Void> param) {
                final TableCell<Produto, Void> cell = new TableCell<Produto, Void>() {

                    private Button btn = new Button("Adicionar ao carrinho");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Produto produto = getTableView().getItems().get(getIndex());
                            
                            int quant;
                            TextInputDialog td = new TextInputDialog("Digite a quantidade");
                            td.showAndWait(); 
                            quant = Integer.parseInt(td.getEditor().getText()); 
                            
                            Item item = new Item(produto, quant, ((produto.getPreco() - (produto.getPreco() * produto.getPercentualDesconto())) * quant), LocalDate.now(), LocalDate.now());
                            try {
								ItemDao iDao = new ItemDao();
								iDao.inserirItem(carrinhoAtual, item, SessaoController.usuario, quant);
								
								CarrinhoDao cDao = new CarrinhoDao();
								cDao.atualizarCarrinho(carrinhoAtual);
								
								ProdutoDao pDao = new ProdutoDao();
								pDao.atualizarEstoque(produto, quant);
								
								carrinhoAtual = cDao.buscarCarrinhoAtual(SessaoController.usuario);
							} catch (Exception e) {
								e.printStackTrace();
							}
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        tColumnActions.setCellFactory(cellFactory);


    }


}
