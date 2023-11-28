package application.controller;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import application.model.Produto;
import application.persistence.ProdutoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Control class for the Seller Dashboard page
 */


public class DashboardController implements Initializable{
 
	@FXML
    private Button Home_btn;
    @FXML
    private Label ProdutosInativos_home;
    @FXML
    private AnchorPane TelaUpdate;
    @FXML
    private Label Total_ativos;
    @FXML
    private Label Total_produtos;
    @FXML
    private TableColumn<Produto, Float> Valor_col_table_update;
    @FXML
    private Button btnUpdute_update;
    @FXML
    private Button btn_addProd;
    @FXML
    private Button btn_clearProd;
    @FXML
    private Button btn_clear_update;
    @FXML
    private Button btn_deleteProd;
    @FXML
    private Button btn_updateProd;
    @FXML
    private TextField buscar_produto;
    @FXML
    private DatePicker datepiker;
    @FXML
    private TableColumn<Produto, String> decricao_col_table_update;
    @FXML
    private ComboBox<Float> desconto;
    @FXML
    private Label descricaoUpdate;
    @FXML
    private TextField descricao_produto;
    @FXML
    private Button deslogar;
    @FXML
    private Label forcedorUpdate;
    @FXML
    private TextField fornecedor_produto;
    @FXML
    private BarChart<?, ?> home_chat;
    @FXML
    private TableColumn<Produto, LocalDate> idProduto_col_DataCriacao;
    @FXML
    private TableColumn<Produto, LocalDate> idProduto_col_DataMOD;
    @FXML
    private TableColumn<Produto, Float> idProduto_col_Desconto;
    @FXML
    private TableColumn<Produto, String> idProduto_col_Descricao;
    @FXML
    private TableColumn<Produto, String> idProduto_col_Fornecedor;
    @FXML
    private TableColumn<Produto, Integer> idProduto_col_ID;
    @FXML
    private TableColumn<Produto, String> idProduto_col_Marca;
    @FXML
    private TableColumn<Produto, Float> idProduto_col_Preco;
    @FXML
    private TableColumn<Produto, Integer> idProduto_col_QTDAtual;
    @FXML
    private TableColumn<Produto, Integer> idProduto_col_SQTDMinima;
    @FXML
    private Label marcaUpddate;
    @FXML
    private TextField marca_produto;
    @FXML
    private Label nome_ProdutoUpdate;
    @FXML
    private TableColumn<Produto, String> nome_col_table_update;
    @FXML
    private TextField nome_produto;
    @FXML
    private TableColumn<Produto, String> nomeprod_col_prod;
    @FXML
    private TableColumn<Produto, Integer> produtoId_col_table_update;
    @FXML
    private Button produtosBTN;
    @FXML
    private Spinner<Integer> spinerqtdMinima;
    @FXML
    private Spinner<Integer> spinerqtdmaxima;
    @FXML
    private TableView<Produto> tViewProduto;
    @FXML
    private AnchorPane telaHome;
    @FXML
    private AnchorPane telacadProduto;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField valor_produto;
    
    private ProdutoDao produtoDao;
    
    @FXML
    private TableColumn<Produto, String> marca_col_table_update ;
    @FXML
    private TableColumn<Produto, String>  fornecedor_col_table_update;
    
    @FXML
    private TextField ID_update;
    @FXML
    private TextField nome_update;
    @FXML
    private TextField desc_update;
    @FXML
    private TextField valor_update;
    @FXML
    private ComboBox<Double> desconto_update;
    @FXML
    private TextField quant_update;
    
    private FilteredList<Produto> filteredLista;
    @FXML
    private TableView<Produto> tableviewProdutos;
    @FXML
    private Label maxProduto;
    @FXML
    private Label minProduto;
    @FXML
    private Label avgProduto;
	@FXML
	private Label countVendas;
	@FXML
	private Label naoVendidos;
	
	/**
	 * Current user's products
	 */
	private ObservableList<Produto> lista = FXCollections.observableArrayList();
	   
	/**
	 * <p> Populate Desconto boxes </p>
	 * @since 1.0
	 */
    public void desconto() {
        desconto.getItems().clear();
        desconto.getItems().add((float) 0);
        desconto.getItems().add((float) 0.25);
        desconto.getItems().add((float) 0.5);
        desconto.getItems().add((float) 0.75);
        desconto.getSelectionModel().select(0); 
        
        desconto_update.getItems().clear();
        desconto_update.getItems().add((Double) 0.0);
        desconto_update.getItems().add((Double) 0.25);
        desconto_update.getItems().add((Double) 0.5);
        desconto_update.getItems().add((Double) 0.75);
        desconto_update.getSelectionModel().select(0); 
    }

	/**
	 * <p> Change screens </p>
	 * @param event
	 * @since 1.0
	 */
	   public void telamuda(ActionEvent event) {
		   if(event.getSource()== Home_btn) {
			  telaHome.setVisible(true);
			  telacadProduto.setVisible(false);
			  TelaUpdate.setVisible(false);  
			  
			  Home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #692db3, #93bbf7);");
	            produtosBTN.setStyle("-fx-background-color:transparent");
	            updateBtn.setStyle("-fx-background-color:transparent");

			  
		   }else if(event.getSource()== produtosBTN) {
			   telacadProduto.setVisible(true);
			   telaHome.setVisible(false);
				  TelaUpdate.setVisible(false); 
				  
				  produtosBTN.setStyle("-fx-background-color:linear-gradient(to bottom right, #692db3, #93bbf7);");
		            Home_btn.setStyle("-fx-background-color:transparent");
		            updateBtn.setStyle("-fx-background-color:transparent");
				  
		   }else if(event.getSource()== updateBtn) {
			   TelaUpdate.setVisible(true);
			   telacadProduto.setVisible(false);
			   telaHome.setVisible(false);
			   
			   updateBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #692db3, #93bbf7);");
	            Home_btn.setStyle("-fx-background-color:transparent");
	            produtosBTN.setStyle("-fx-background-color:transparent");
		   }
	   }
	    

	/**
	 * <p> Log out user </p>
	 * @since 1.0
	 */
	   public void deslogar() {
	    	Alert alert= new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmação de mensagem");
	    	alert.setTitle(null);
	    	alert.setContentText("Você têm certeza que deseja sair? ");
	    	Optional <ButtonType> option= alert.showAndWait();
	    	try {
	    	if(option.get().equals(ButtonType.OK)) {
	    		deslogar.getScene().getWindow().hide();
				Parent root=FXMLLoader.load(getClass().getResource("/application/Principal.fxml"));
				Scene scene =new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				} 
	    	}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	    }
	   

		/**
		 * <p> Update Produto </p>
		 * @param event
		 * @since 1.0
		 */
	   public void handleUpdateProduct(ActionEvent event) {
	        updateBtn.fire();
	    }
	   
	   
	  
	   //--------------------------------------------------------------------------------------------------------------------------
	/**
	 * <p> Initialize resources </p>
	 * @since 1.0
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		desconto();
        SpinnerValueFactory<Integer> valueFactoryMinima = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        SpinnerValueFactory<Integer> valueFactoryMaxima = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);

        spinerqtdMinima.setValueFactory(valueFactoryMinima);
        spinerqtdmaxima.setValueFactory(valueFactoryMaxima);
        
        
        try {
            produtoDao = new ProdutoDao();
            lista = FXCollections.observableArrayList();
            filteredLista = new FilteredList<>(lista, p -> true);
            popularTabela();
    		gerarEstatisticas();
    		gerarEstatisticasValor();
        } catch (Exception e) {
            e.printStackTrace();
        }

       //--------------------------------------Pegando pela label-----------------------------------------------------------------
        
        
        buscar_produto.textProperty().addListener((observable, oldValue, newValue) -> {
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
    }
	
	/**
	 * <p> Generate statistics </p>
	 * @param None
	 * @return Void
	 * @since 1.0
	 */
	private void gerarEstatisticas() throws ClassNotFoundException, SQLException {
		countVendas.setText(String.valueOf(produtoDao.quantVendidosPorLojista(SessaoController.usuario)));
		naoVendidos.setText(String.valueOf(produtoDao.quantNaoVendidosPorLojista(SessaoController.usuario)));
		
		ResultSet estatisticas = produtoDao.estatisticasProduto(SessaoController.usuario);
		
		if (estatisticas.next()) {
			Total_produtos.setText(String.valueOf(estatisticas.getInt("total")));
			Total_ativos.setText(String.valueOf(estatisticas.getInt("ativos")));
			ProdutosInativos_home.setText(String.valueOf(estatisticas.getInt("inativos")));
		}
	}
		
	/**
	 * <p> Generate statistics related to price </p>
	 * @since 1.0
	 */
	private void gerarEstatisticasValor() throws ClassNotFoundException, SQLException {
		ResultSet estatisticas = produtoDao.estatisticasValorProduto(SessaoController.usuario);
		
		if (estatisticas.next()) {
			maxProduto.setText("R$" + String.valueOf(estatisticas.getInt("maximo")));
			minProduto.setText("R$" + String.valueOf(estatisticas.getInt("minimo")));
			avgProduto.setText("R$" + String.valueOf(estatisticas.getInt("media")));
		}
	
		
	}

	//---------------------------------ADD PRODUTO----------------------------------------------------------------------------------------
	
	/**
	 * <p> Add new Produto </p>
	 * @param event
	 * @since 1.0
	 */
	public void handleAddProduto(ActionEvent event) {
        try {
        	if (camposValidos()) {
	        Produto produto = new Produto(Produto.geraId(), nome_produto.getText(), Double.parseDouble(valor_produto.getText()), 
	        		desconto.getValue(), descricao_produto.getText(), marca_produto.getText(), fornecedor_produto.getText(),
	        		spinerqtdmaxima.getValue(), spinerqtdMinima.getValue(), LocalDate.now(), LocalDate.now());

	            produtoDao.inserirProduto(produto);
	            exibirAlerta("Produto adicionado com sucesso!", Alert.AlertType.INFORMATION);
		        lista.add(produto);
	            limparCampos();
        	}
	   } catch (Exception e) {
	            e.printStackTrace();
	            exibirAlerta("Erro ao adicionar produto. Verifique os dados e tente novamente.", Alert.AlertType.ERROR);
	    } 
	}

	/**
	 * <p> Validate fields </p>
	 * @return boolean
	 * @since 1.0
	 */
    private boolean camposValidos() {
        if (nome_produto.getText().isEmpty() || valor_produto.getText().isEmpty() || spinerqtdMinima.getValue() == null || 
        		spinerqtdMinima.getValue() == null || valor_produto.getText().isEmpty()) {
        	exibirAlerta("Um ou mais campos obrigatórios estão vazios.", Alert.AlertType.ERROR);
        	return false;
        }
        if(nome_produto.getText().length() > 50) {
			Alert alert = new Alert(AlertType.ERROR, "Nome ultrapassa 50 caracteres");
			alert.show();
			return false;
        }

        if(descricao_produto.getText().length() > 255) {
			Alert alert = new Alert(AlertType.ERROR, "Descrição ultrapassa 255 caracteres");
			alert.show();
			return false;
        }
        
        if(marca_produto.getText().length() > 50) {
			Alert alert = new Alert(AlertType.ERROR, "Marca ultrapassa 50 caracteres");
			alert.show();
			return false;
        }
        
        if(fornecedor_produto.getText().length() > 50) {
			Alert alert = new Alert(AlertType.ERROR, "Fornecedor ultrapassa 50 caracteres");
			alert.show();
			return false;
        }

        
        if(Double.parseDouble(valor_produto.getText()) <= 0) {
			Alert alert = new Alert(AlertType.ERROR, "Valor deve ser maior que zero");
			alert.show();
			return false;
        }
        
        if(spinerqtdMinima.getValue() <= 0) {
			Alert alert = new Alert(AlertType.ERROR, "Quantidade mínima não pode ser negativa");
			alert.show();
			return false;
        }
        
        if(spinerqtdmaxima.getValue() <= 0) {
			Alert alert = new Alert(AlertType.ERROR, "Quantidade atual não pode ser negativa");
			alert.show();
			return false;
        }
        
        return true;
    }

	/**
	 * <p> Clean fields </p>
	 * @since 1.0
	 */
    public void limparCampos() {
        nome_produto.clear();
        valor_produto.clear();
        descricao_produto.clear();
        marca_produto.clear();
        fornecedor_produto.clear();
    }

	/**
	 * <p> Display alerts </p>
	 * @param mensagem the message to be displayed, tipo AlertType for message
	 * @since 1.0
	 */
    private void exibirAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    //-----------------Consultando as coisas na tabela-----------------------------------------------------------------------------------------------------------
	/**
	 * <p> Populate TableView </p>
	 * @since 1.0
	 */
    private void popularTabela() throws SQLException {
    	ResultSet rs = produtoDao.buscarProdutosLojista();
    	
    	// Popula lista com resultados do SQL
    	while(rs.next()){
    		Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getFloat("preco"), rs.getFloat("percentual_desconto"),
    				rs.getString("descricao"), rs.getString("marca"), rs.getString("fornecedor"), rs.getInt("quantidade_atual"), 
    				rs.getInt("quantidade_minima"), rs.getDate("data_criacao").toLocalDate(), rs.getDate("data_ultima_modificacao").toLocalDate());
    		lista.add(produto);
    	}  

    	// Ligar as colunas com atributos dos objetos
    	idProduto_col_ID.setCellValueFactory( new PropertyValueFactory<Produto, Integer>("id"));
    	nomeprod_col_prod.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome"));
    	idProduto_col_Preco.setCellValueFactory( new PropertyValueFactory<Produto, Float>("preco"));
    	idProduto_col_Desconto.setCellValueFactory( new PropertyValueFactory<Produto, Float>("percentualDesconto"));
    	idProduto_col_Descricao.setCellValueFactory( new PropertyValueFactory<Produto, String>("descricao"));
    	idProduto_col_Marca.setCellValueFactory( new PropertyValueFactory<Produto, String>("marca"));
    	idProduto_col_Fornecedor.setCellValueFactory( new PropertyValueFactory<Produto, String>("fornecedor"));
    	idProduto_col_QTDAtual.setCellValueFactory( new PropertyValueFactory<Produto, Integer>("quantidadeAtual"));
    	idProduto_col_SQTDMinima.setCellValueFactory( new PropertyValueFactory<Produto, Integer>("quantidadeMinima"));
    	idProduto_col_DataCriacao.setCellValueFactory( new PropertyValueFactory<Produto, LocalDate>("dataCriacao"));
    	idProduto_col_DataMOD.setCellValueFactory( new PropertyValueFactory<Produto, LocalDate>("dataUltimaModificacao"));
    	tViewProduto.setItems(lista);
    	
    	produtoId_col_table_update.setCellValueFactory( new PropertyValueFactory<Produto, Integer>("id"));
    	nome_col_table_update.setCellValueFactory( new PropertyValueFactory<Produto, String>("nome"));
    	Valor_col_table_update.setCellValueFactory( new PropertyValueFactory<Produto, Float>("preco"));
    	decricao_col_table_update.setCellValueFactory( new PropertyValueFactory<Produto, String>("descricao"));
    	tableviewProdutos.setItems(lista);
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    
    //---------DELETE PRODUTO---------------------------------------------------------------------------------------   
		/**
		 * <p> Delete Produto </p>
		 * @param event
		 * @since 1.0
		 */   
	    @FXML
	   public void handleDeleteProduto(ActionEvent event) {
	       // Get the selected products
	       ObservableList<Produto> selectedProdutos = tViewProduto.getSelectionModel().getSelectedItems();

	       if (!selectedProdutos.isEmpty()) {
	           Alert alert = new Alert(AlertType.CONFIRMATION);
	           alert.setTitle("Confirmação de Exclusão");
	           alert.setHeaderText(null);
	           alert.setContentText("Você tem certeza que deseja excluir os produtos selecionados?");

	           Optional<ButtonType> result = alert.showAndWait();

	           if (result.isPresent() && result.get() == ButtonType.OK) {
	               try {
	                   // Delete the selected products from the database
	                   for (Produto selectedProduto : selectedProdutos) {
	                	   if (produtoDao.podeSerDeletado(selectedProduto.getId())) {
		                       produtoDao.deletarProduto(selectedProduto.getId());
	                	   } else {
	                		   exibirAlerta("Produto já possui vendas e não pode ser excluido.", Alert.AlertType.ERROR);
	                	   }
	                   }

	                   // Remove the selected products from the TableView
	                   lista.removeAll(selectedProdutos);

	                   // Clear the selection
	                   tViewProduto.getSelectionModel().clearSelection();

	                   // Display success message
	                   exibirAlerta("Produtos excluídos com sucesso!", Alert.AlertType.INFORMATION);
	               } catch (SQLException e) {
	                   e.printStackTrace();
	                   exibirAlerta("Erro ao excluir produtos. Verifique e tente novamente.", Alert.AlertType.ERROR);
	               }
	           }
	       } else {
	           exibirAlerta("Selecione pelo menos um produto para excluir.", Alert.AlertType.WARNING);
	       }
	   }
	   //--------------------------------------------------------------------------------------------------------------------------
   //---------------------------------UPDATE------------------------------------------------------------------------------------------ 
		/**
		 * <p> Populate fields on update </p>
		 * @param event
		 * @since 1.0
		 */   
	    public void populateFields(ActionEvent event) {
        try {
            int productId = Integer.parseInt(ID_update.getText());
            Produto produto = produtoDao.buscarProdutoPorId(productId);

            if (produto != null) {
            	nome_update.setText(produto.getNome());
            	desc_update.setText(produto.getDescricao());
            	valor_update.setText(String.valueOf(produto.getPreco()));
            	desconto_update.getSelectionModel().select(produto.getPercentualDesconto());
            	quant_update.setText(String.valueOf(produto.getQuantidadeAtual()));
            } else {
                exibirAlerta("Produto não encontrado.", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException | SQLException e) {
            exibirAlerta("Erro ao buscar produto. Verifique o ID e tente novamente.", Alert.AlertType.ERROR);
        }
    }

	/**
	 * <p> Update Produto </p>
	 * @param event
	 * @since 1.0
	 */   
    @FXML
    public void updateProduct(ActionEvent event) {
        try {
        	if (!validarUpdate()) {
        		
        	}
        	
            int productId = Integer.parseInt(ID_update.getText());
            Produto produto = produtoDao.buscarProdutoPorId(productId);

            if (produto != null) {
                produto.setNome(nome_update.getText());
                produto.setDescricao(desc_update.getText());
                produto.setPreco(Double.parseDouble(valor_update.getText()));
                produto.setPercentualDesconto(desconto_update.getValue());
                produto.setQuantidadeAtual(Integer.parseInt(quant_update.getText()));
                produtoDao.atualizarProduto(produto);

                lista.clear();
                popularTabela();

                exibirAlerta("Produto atualizado com sucesso!", Alert.AlertType.INFORMATION);
            } else {
                exibirAlerta("Produto não encontrado.", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException | SQLException e) {
            exibirAlerta("Erro ao atualizar produto. Verifique os dados e tente novamente.", Alert.AlertType.ERROR);
        }
    }
    
	/**
	 * <p> Validate update </p>
	 * @return boolean
	 * @since 1.0
	 */   
    private boolean validarUpdate() {
		if (nome_update.getText().isEmpty() || valor_update.getText().isEmpty() || quant_update.getText().isEmpty()) {
			exibirAlerta("Um ou mais campos obrigatórios estão vazios", Alert.AlertType.ERROR);
		}
		
		if(nome_update.getText().length() > 50) {
			Alert alert = new Alert(AlertType.ERROR, "Nome ultrapassa 50 caracteres");
			alert.show();
			return false;
        }

        if(desc_update.getText().length() > 255) {
			Alert alert = new Alert(AlertType.ERROR, "Descrição ultrapassa 255 caracteres");
			alert.show();
			return false;
        }
        
        if(Double.parseDouble(valor_update.getText()) <= 0) {
			Alert alert = new Alert(AlertType.ERROR, "Valor deve ser maior que zero");
			alert.show();
			return false;
        }
		
        if(Integer.parseInt(quant_update.getText()) <= 0) {
			Alert alert = new Alert(AlertType.ERROR, "Quantidade atual não pode ser negativa");
			alert.show();
			return false;
        }
		
        
		return true;
	}

	/**
	 * <p> Clean update fields </p>
	 * @param event
	 * @since 1.0
	 */   
	public void limparCampos(ActionEvent event) {
        // Limpa os campos
        nome_ProdutoUpdate.setText("");
        marcaUpddate.setText("");
        forcedorUpdate.setText("");
        descricaoUpdate.setText("");
        valor_update.setText("");
        ID_update.setText("");
    }

    


}
    
    

        
        
	


