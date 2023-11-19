package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DashLojistaController {
	@FXML
    private Button btnestoque;

    @FXML
    private Button btnprodutos;

    @FXML
    private Button btnredimentos;

    @FXML
    private Label labelstatus;

    @FXML
    private Label labelstatus1;

    @FXML
    private Label labelstatus11;

    @FXML
    private Label labelstatus111;

    @FXML
    private Label labelstatus2;

    @FXML
    private Pane paneview;

    @FXML
    private GridPane pnredimentos;

    @FXML
    private GridPane pnusuarios;
    
    @FXML
    private Button btnDeslogar;

	public void irParaCadastroLojista(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Lojista.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void irParaProduto(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Produto.FXML"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void hendleClicks(ActionEvent event) {
		if(event.getSource()==btnestoque) {
		labelstatus2.setText("/home/Estoque");	
		labelstatus.setText("Lojista");
		paneview.setBackground(new Background(new BackgroundFill(Color.rgb(153,204,255), CornerRadii.EMPTY,Insets.EMPTY)));
		pnusuarios.toFront();
		
		}
			else if(event.getSource()==btnredimentos) {
				labelstatus2.setText("/home/redimentos");	
				labelstatus.setText("Redimentos");
				paneview.setBackground(new Background(new BackgroundFill(Color.rgb(0,255,128), CornerRadii.EMPTY,Insets.EMPTY)));
				pnredimentos.toFront();
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

}

