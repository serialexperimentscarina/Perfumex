package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import application.model.Cliente;
import application.model.Lojista;
import application.model.Usuario;

public class UsuarioDao {
	
	private Connection c;
	private static int usuarioCont;
	
	public UsuarioDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void insereCliente(Cliente cli) throws SQLException {
		insereUsuario(cli);
		String sql = "INSERT INTO cliente VALUES (?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, (usuarioCont + 1));
		ps.setString(2, cli.getCpf());
	
		ps.execute();
		ps.close();
		
		usuarioCont++;
	}
	
	public void insereLojista(Lojista l) throws SQLException {
		insereUsuario(l);
		String sql = "INSERT INTO lojista VALUES (?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, (usuarioCont + 1));
		ps.setString(2, l.getCnpj());
	
		ps.execute();
		ps.close();
		
		usuarioCont++;
	}
	
	private void insereUsuario(Usuario u) throws SQLException {
		String sql = "INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, (usuarioCont + 1));
		ps.setString(2, u.getNome());
		ps.setString(3, u.getSobrenome());
		ps.setString(4, u.getEmail());
		ps.setString(5, u.getSenha());
		ps.setString(6, u.getTelefone());
		ps.setString(7, "Ativo");
		ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));
	
		ps.execute();
		ps.close();
	}

}
