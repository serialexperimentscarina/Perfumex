package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import application.model.Cliente;
import application.model.Lojista;
import application.model.Usuario;

public class UsuarioDao {
	
	private Connection c;
	
	public UsuarioDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void insereCliente(Cliente cli) throws SQLException {
		insereUsuario(cli);
		String sql = "INSERT INTO cliente VALUES (?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cli.getId());
		ps.setString(2, cli.getCpf());
	
		ps.execute();
		ps.close();
	}
	
	public void insereLojista(Lojista l) throws SQLException {
		insereUsuario(l);
		String sql = "INSERT INTO lojista VALUES (?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, l.getId());
		ps.setString(2, l.getCnpj());
	
		ps.execute();
		ps.close();
	}
	
	public Usuario buscaUsuarioLogin(String email, String senha) throws SQLException{
		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, senha);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Usuario u = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"), email, senha, rs.getString("telefone"), rs.getString("status"), rs.getDate("data_criacao").toLocalDate(), rs.getDate("data_ultima_modificacao").toLocalDate());
			return u;
		}
		return null;
	
	}
	
	public String buscarTipoUsuario(Usuario u) throws SQLException{
		String sql = "SELECT * FROM cliente WHERE usuarioid = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return "cliente";
		} else {
			return "lojista";
		}
	
	}
	
	private void insereUsuario(Usuario u) throws SQLException {
		String sql = "INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getId());
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

	public int contarUsuario() throws SQLException{
		String sql = "SELECT COUNT(id) AS contagem FROM usuario";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getInt("contagem");
		}
		return 0;
	
	}
}
