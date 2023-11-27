package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import application.model.Cliente;
import application.model.Lojista;
import application.model.Usuario;

/**
 * Persistence class for the Usuario entity and it's subclasses
 */

public class UsuarioDao {
	
	/**
	 * Connection variable
	 */
	private Connection c;
	
	/**
	 * <p> Constructor </p>
	 * @since 1.0
	 */
	public UsuarioDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	/**
	 * <p> Insert Clientes into DB </p>
	 * @param cli
	 * @since 1.0
	 */
	public void insereCliente(Cliente cli) throws SQLException {
		insereUsuario(cli);
		String sql = "INSERT INTO cliente VALUES (?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cli.getId());
		ps.setString(2, cli.getCpf());
	
		ps.execute();
		ps.close();
	}
	
	/**
	 * <p> Insert Lojistas into DB </p>
	 * @param l
	 * @since 1.0
	 */
	public void insereLojista(Lojista l) throws SQLException {
		insereUsuario(l);
		String sql = "INSERT INTO lojista VALUES (?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, l.getId());
		ps.setString(2, l.getCnpj());
	
		ps.execute();
		ps.close();
	}
	
	/**
	 * <p> Search User by login info </p>
	 * @param email, senha
	 * @return Usuario
	 * @since 1.0
	 */
	public Usuario buscaUsuarioLogin(String email, String senha) throws SQLException{
		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, senha);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Usuario u = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"), email, senha, 
					rs.getString("telefone"), rs.getString("status"), rs.getDate("data_criacao").toLocalDate(), 
					rs.getDate("data_ultima_modificacao").toLocalDate());
			return u;
		}
		return null;
	
	}
	
	/**
	 * <p> Search the type of an user </p>
	 * @param u
	 * @return String
	 * @since 1.0
	 */
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
	
	/**
	 * <p> Return a Cliente's CPF </p>
	 * @param u
	 * @return String
	 * @since 1.0
	 */
	public String buscarCPF(Usuario u) throws SQLException{
		String sql = "SELECT cpf FROM cliente WHERE usuarioid = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getString("cpf");
		}
		return "";
	}
	
	/**
	 * <p> Check if email is avaliable </p>
	 * @param email
	 * @return boolean
	 * @since 1.0
	 */
	public boolean checarDispEmail(String email) throws SQLException{
		String sql = "SELECT id FROM usuario WHERE email = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return false;
		}
		return true;
	}
	
	/**
	 * <p> Insert new Usuario into DB </p>
	 * @param u
	 * @since 1.0
	 */
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

	/**
	 * <p> Count how many users are in the DB </p>
	 * @return int
	 * @since 1.0
	 */
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
