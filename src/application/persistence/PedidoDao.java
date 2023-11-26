package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Item;
import application.model.Pedido;
import application.model.Usuario;

public class PedidoDao {

	private Connection c;
	
	public PedidoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void inserirPedido(Pedido p) throws SQLException {
		String sql = "INSERT INTO pedido VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getCarrinho().getId());
		ps.setString(2, p.getTipoPagto());
		ps.setString(3, p.getStatus());
		ps.setDate(4, java.sql.Date.valueOf(p.getDataCriacao()));
		ps.setDate(5, java.sql.Date.valueOf(p.getDataUltimaModificacao()));
	
		ps.execute();
		ps.close();
	}
	
	
	
}
