package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Item;
import application.model.Pedido;
import application.model.Usuario;

/**
 * Persistence class for the Pedido entity
 */


public class PedidoDao {

	/**
	 * Connection variable
	 */
	private Connection c;
	
	
	/**
	 * <p> Constructor </p>
	 * @param None
	 * @return Void
	 * @since 1.0
	 */
	public PedidoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	
	/**
	 * <p> Insert Pedido into DB </p>
	 * @param Pedido p
	 * @return Void
	 * @since 1.0
	 */
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
