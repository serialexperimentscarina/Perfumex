package application.controller;

import java.sql.SQLException;

import application.model.Usuario;
import application.persistence.UsuarioDao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SessaoController {
	
	public static Usuario usuario;
	public static String tipo;
	
	public void logar(Usuario u) throws SQLException, ClassNotFoundException {
		usuario = u;
		
		UsuarioDao uDao = new UsuarioDao();
		tipo = uDao.buscarTipoUsuario(u);
	}
	
	public void deslogar() {
		usuario = null;
		tipo = "";
	}
	

}
