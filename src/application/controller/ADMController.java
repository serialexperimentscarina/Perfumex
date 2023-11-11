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

public class ADMController {

	 @FXML
	    private Button btnconfig;

	    @FXML
	    private Button btnloja;

	    @FXML
	    private Button btnredimentos;

	    @FXML
	    private Button btnusuarios;

	    @FXML
	    private Label labelstatus;

	    @FXML
	    private Label labelstatus2;

	    @FXML
	    private Pane paneview;
	    
	    @FXML
	    private GridPane pnconfingurações;

	    @FXML
	    private GridPane pnlojas;

	    @FXML
	    private GridPane pnredimentos;

	    @FXML
	    private GridPane pnusuarios;

	public void irADM(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/ADMController"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public  void initialize(URL location,ResourceBundle resources) {

	}
	@FXML
	private void hendleClicks(ActionEvent event) {
		if(event.getSource()==btnusuarios) {
		labelstatus2.setText("/home/usuarios");	
		labelstatus.setText("Usuários");
		paneview.setBackground(new Background(new BackgroundFill(Color.rgb(153,204,255), CornerRadii.EMPTY,Insets.EMPTY)));
		pnusuarios.toFront();
		
		}
		else if(event.getSource()==btnloja) {
			labelstatus2.setText("/home/Loja");	
			labelstatus.setText("Loja");
			paneview.setBackground(new Background(new BackgroundFill(Color.rgb(255,153,153), CornerRadii.EMPTY,Insets.EMPTY)));
            pnlojas.toFront();
			}
			else if(event.getSource()==btnredimentos) {
				labelstatus2.setText("/home/redimentos");	
				labelstatus.setText("Redimentos");
				paneview.setBackground(new Background(new BackgroundFill(Color.rgb(0,255,128), CornerRadii.EMPTY,Insets.EMPTY)));
				pnredimentos.toFront();
				}
				else if(event.getSource()==btnconfig) {
					labelstatus2.setText("/home/Configuração");	
					labelstatus.setText("Configurações");
					paneview.setBackground(new Background(new BackgroundFill(Color.rgb(204,204,255), CornerRadii.EMPTY,Insets.EMPTY)));
					pnconfingurações.toFront();
						
					}
	}

}
