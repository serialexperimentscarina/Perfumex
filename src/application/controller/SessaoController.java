package application.controller;

import java.sql.SQLException;

import application.model.Usuario;
import application.persistence.UsuarioDao;

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
	 * @param u the user to be logged on
	 * @since 1.0
	 */
	public void logar(Usuario u) throws SQLException, ClassNotFoundException {
		usuario = u;
		
		UsuarioDao uDao = new UsuarioDao();
		tipo = uDao.buscarTipoUsuario(u);
	}
	
	/**
	 * <p> Log out user </p>
	 * @since 1.0
	 */
	public void deslogar() {
		usuario = null;
		tipo = "";
	}
	

}
