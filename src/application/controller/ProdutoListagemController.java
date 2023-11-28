package application.controller;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableCell;

/**
 * Control class for the marketplace product's listing page
 */


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
	
	/**
	 * Current user's cart
	 */
	private Carrinho carrinhoAtual;	
	/**
	 * List of products
	 */
	private ObservableList<Produto> lista = FXCollections.observableArrayList();
	/**
	 * Filtered list of products
	 */
    private FilteredList<Produto> filteredLista;

	/**
	 * <p> Load in essential resources </p>
	 * @param location, resources
	 * @since 1.0
	 */
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
	
	/**
	 * <p> Trigger logout </p>
	 * @param event
	 * @since 1.0
	 */
	public void deslogar(ActionEvent event) throws IOException {
		SessaoController sessao = new SessaoController();
		sessao.deslogar();
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/Principal.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * <p> Go to Carrinho page </p>
	 * @param event
	 * @since 1.0
	 */
	public void irParaCarrinho(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Carrinho.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setUserData(carrinhoAtual);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * <p> Populate TableView </p>
	 * @since 1.0
	 */
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
	
	/**
	 * <p> Load in current user's Carrinho </p>
	 * @since 1.0
	 */
	private void inicializarCarrinho() throws ClassNotFoundException, SQLException {
		CarrinhoDao cDao = new CarrinhoDao();
		carrinhoAtual = cDao.buscarCarrinhoAtual(SessaoController.usuario);
		
	}
	
	/**
	 * <p> Include Button in TableView </p>
	 * @since 1.0
	 */
	private void addToCartButton() {
        Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>> cellFactory = new Callback<TableColumn<Produto, Void>, TableCell<Produto, Void>>() {
            @Override
            public TableCell<Produto, Void> call(final TableColumn<Produto, Void> param) {
                final TableCell<Produto, Void> cell = new TableCell<Produto, Void>() {

                    private Button btn = new Button("Adicionar ao carrinho");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Produto produto = getTableView().getItems().get(getIndex());
                            
                            int quant = 0;
                            TextInputDialog td = new TextInputDialog("Digite a quantidade");
                            td.showAndWait(); 
                            try {
                            	quant = Integer.parseInt(td.getEditor().getText());
                            } catch (Exception e) {
                            	Alert alert= new Alert(AlertType.ERROR, "Por favor, digite um número");
                    			alert.show();
                    			return;
                            }
                            
                            if (produto.getQuantidadeAtual() - quant < produto.getQuantidadeMinima()) {
                            	Alert alert= new Alert(AlertType.ERROR, "Quantidade indisponível");
                    			alert.show();
                    			return;
                            }
                            
                            Item item = new Item(produto, quant, ((produto.getPreco() - (produto.getPreco() * produto.getPercentualDesconto())) * quant), LocalDate.now(), LocalDate.now());
                            try {
								ItemDao iDao = new ItemDao();
								iDao.inserirItem(carrinhoAtual, item, SessaoController.usuario, quant);
								
								CarrinhoDao cDao = new CarrinhoDao();
								cDao.atualizarCarrinho(carrinhoAtual);
								
								ProdutoDao pDao = new ProdutoDao();
								pDao.atualizarEstoque(produto, quant);
								
								//byanca mudou aqui para confirmation antes estava assim 
								// Alert alert= new Alert(AlertType.ERROR, " algo deu errado ao tentar adicionar o item no carrinho");
								carrinhoAtual = cDao.buscarCarrinhoAtual(SessaoController.usuario);
							} catch (Exception e) {
								Alert alert= new Alert(AlertType.CONFIRMATION, " item adicionado no carrinho");
                    			alert.show();
                    			return;
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
