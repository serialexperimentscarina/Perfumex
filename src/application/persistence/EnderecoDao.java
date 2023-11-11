package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.model.Endereco;
import application.model.Usuario;

public class EnderecoDao {
	
	private Connection c;
	
	public EnderecoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void insererEndereco(Endereco e) throws SQLException {
		String sql = "INSERT INTO endereco VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, e.getUsuario().getId());
		ps.setString(2, e.getRua());
		ps.setInt(3, e.getNumero());
		ps.setString(4, e.getCep());
		ps.setString(5, e.getComplemento());
		ps.setString(6, e.getEstado());
		ps.setString(7, e.getCidade());
		ps.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));
	
		ps.execute();
		ps.close();
	}

}