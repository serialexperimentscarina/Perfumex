package application.controller;

import java.sql.SQLException;

import application.model.Usuario;
import application.persistence.UsuarioDao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Control class for keeping the user session
 */


public class SessaoController {
	
	/**
	 * Current user
	 */
	public static Usuario usuario;
	/**
	 * Type of current user
	 */
	public static String tipo;
	
	/**
	 * <p> Log in user </p>
	 * @param Usuario u
	 * @return Void
	 * @since 1.0
	 */
	public void logar(Usuario u) throws SQLException, ClassNotFoundException {
		usuario = u;
		
		UsuarioDao uDao = new UsuarioDao();
		tipo = uDao.buscarTipoUsuario(u);
	}
	
	/**
	 * <p> Log out user </p>
	 * @param None
	 * @return Void
	 * @since 1.0
	 */
	public void deslogar() {
		usuario = null;
		tipo = "";
	}
	

}
