package application.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	    private TableColumn<?, ?> Valor_col_table_update;

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
	    private ComboBox<String> categoria;

	    @FXML
	    private ComboBox<String> comboStatus;

	    @FXML
	    private DatePicker data_modificacao;

	    @FXML
	    private DatePicker datepiker;

	    @FXML
	    private TableColumn<?, ?> decricao_col_table_update;

	    @FXML
	    private ComboBox<Integer> desconto;

	    @FXML
	    private Label descricaoUpdate;

	    @FXML
	    private TextField descricao_produto;

	    @FXML
	    private Button deslogar;

	    @FXML
	    private Label forcedorUpdate;

	    @FXML
	    private TableColumn<?, ?> fornecedor_col_table_update;

	    @FXML
	    private TextField fornecedor_produto;

	    @FXML
	    private BarChart<?, ?> home_chat;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_DataCriacao;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_DataMOD;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_Desconto;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_Descricao;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_Fornecedor;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_ID;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_Marca;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_Preco;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_QTDAtual;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_SQTDMinima;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_Status;

	    @FXML
	    private TableColumn<?, ?> idProduto_col_categoria;

	    @FXML
	    private TextField id_produto;

	    @FXML
	    private Label marcaUpddate;

	    @FXML
	    private TableColumn<?, ?> marca_col_table_update;

	    @FXML
	    private TextField marca_produto;

	    @FXML
	    private Label nome_ProdutoUpdate;

	    @FXML
	    private TableColumn<?, ?> nome_col_table_update;

	    @FXML
	    private TextField nome_produto;

	    @FXML
	    private TableColumn<?, ?> nomeprod_col_prod;

	    @FXML
	    private TableColumn<?, ?> produtoId_col_table_update;

	    @FXML
	    private Button produtosBTN;

	    @FXML
	    private Spinner<Integer> spinerqtdMinima;

	    @FXML
	    private Spinner<Integer> spinerqtdmaxima;

	    @FXML
	    private TableView<?> tablevie;

	    @FXML
	    private TableView<?> tableviewProdutos;

	    @FXML
	    private AnchorPane telaHome;

	    @FXML
	    private AnchorPane telacadProduto;

	    @FXML
	    private Button updateBtn;

	    @FXML
	    private TextField valor_produto;

    
    private ProdutoDao produtoDao;
	    
	   
    public void desconto() {
        desconto.getItems().clear();
        desconto.getItems().addAll(0, 5, 10, 15, 20, 25, 30, 35, 50);
        desconto.getSelectionModel().select(0); 
    }
	    public void status() {
	    	comboStatus.getItems().clear();
	    	comboStatus.getItems().addAll("Inativo","Ativo");
	    	comboStatus.getSelectionModel().select("Ativo");
		}
	    
	    public void categoria() {
	    	categoria.getItems().clear();
	    	categoria.getItems().addAll("-","Masculino","Feminino","KIDS");
	    	categoria.getSelectionModel().select("-");
		}
	    
	   public void telamuda(ActionEvent event) {
		   System.out.println("TESTE");
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
	      
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		desconto();
        status();
        categoria();
        SpinnerValueFactory<Integer> valueFactoryMinima = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        SpinnerValueFactory<Integer> valueFactoryMaxima = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);

        spinerqtdMinima.setValueFactory(valueFactoryMinima);
        spinerqtdmaxima.setValueFactory(valueFactoryMaxima);
        
        
        try {
            produtoDao = new ProdutoDao();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
      
        }
    }
	
	
	public void handleAddProduto(ActionEvent event) {
	    if (camposValidos()) {
	        Produto produto = new Produto(0, null, 0, 0, null, null, null, 0, 0, null, null, null, null, null);
	        produto.setId(Integer.parseInt(id_produto.getText()));
	        produto.setNome(nome_produto.getText());
	        produto.setPreco(Double.parseDouble(valor_produto.getText()));
	        produto.setPercentualDesconto(desconto.getValue()); 
	        produto.setDescricao(descricao_produto.getText());
	        produto.setMarca(marca_produto.getText());
	        produto.setFornecedor(fornecedor_produto.getText());
	        produto.setQuantidadeAtual(spinerqtdmaxima.getValue()); 
	        produto.setQuantidadeMinima(spinerqtdMinima.getValue()); 
	        produto.setDataCriacao(LocalDate.now());
	        produto.setDataUltimaModificacao(LocalDate.now());
	        produto.setStatus(comboStatus.getValue()); 
	        try {
	            // Assuming you have a ProdutoDAO class with a method inserirProduto(Produto produto)
	            produtoDao.inserirProduto(produto);
	            exibirAlerta("Produto adicionado com sucesso!", Alert.AlertType.INFORMATION);
	            limparCampos();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            exibirAlerta("Erro ao adicionar produto. Verifique os dados e tente novamente.", Alert.AlertType.ERROR);
	        }
	    } else {
	        exibirAlerta("Preencha todos os campos corretamente.", Alert.AlertType.WARNING);
	    }
	}

    private boolean camposValidos() {
        return !id_produto.getText().isEmpty() && !nome_produto.getText().isEmpty() && !valor_produto.getText().isEmpty();
    }

    public void limparCampos() {
        id_produto.clear();
        nome_produto.clear();
        valor_produto.clear();
        descricao_produto.clear();
        marca_produto.clear();
        fornecedor_produto.clear();
    }

    private void exibirAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
        
        
	


