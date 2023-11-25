package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Cliente;
import application.model.Item;
import application.model.Produto;
import application.model.Usuario;

public class ItemDao {
	
	private Connection c;
	
	public ItemDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	
	public void inserirItem(Carrinho car, Item i, Usuario u, int quant) throws SQLException {
		String sql = "INSERT INTO item VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, car.getId());
		ps.setInt(2, i.getProduto().getId());
		ps.setInt(3, quant);
		ps.setDouble(4, i.getSubtotal());
		ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
	
		ps.execute();
		ps.close();
	}

}
