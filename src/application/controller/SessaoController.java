package application.controller;

import java.sql.SQLException;

import application.model.Usuario;
import application.persistence.UsuarioDao;

public class SessaoController {
	
	public static Usuario usuario;
	public static String tipo;
	
	public SessaoController(Usuario u) throws ClassNotFoundException, SQLException {
		usuario = u;
		
		UsuarioDao uDao = new UsuarioDao();
		tipo = uDao.buscarTipoUsuario(u);
	}

}
