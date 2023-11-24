package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Cliente;

public class CarrinhoDao {
	
	private Connection c;
	
	public CarrinhoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void criarNovoCarrinho(Carrinho car, Cliente cli) throws SQLException {
		String sql = "INSERT INTO carrinho VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, car.getId());
		ps.setInt(2, car.getQuantidadeItens());
		ps.setDouble(3, car.getTotal());
		ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setInt(6, cli.getId());
	
		ps.execute();
		ps.close();
	}

	public int contarCarrinho() throws SQLException{
		String sql = "SELECT COUNT(id) AS contagem FROM carrinho";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getInt("contagem");
		}
		return 0;
	
	}

}
