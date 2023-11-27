package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Cliente;
import application.model.Produto;
import application.model.Usuario;

/**
 * Persistence class for the Carrinho entity
 */

public class CarrinhoDao {
	
	/**
	 * Connection variable
	 */
	private Connection c;
	
	/**
	 * <p> Constructor </p>
	 * @since 1.0
	 */
	public CarrinhoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	/**
	 * <p> Insert Carrinho into DB </p>
	 * @param car, u
	 * @since 1.0
	 */
	public void criarNovoCarrinho(Carrinho car, Usuario u) throws SQLException {
		String sql = "INSERT INTO carrinho VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, car.getId());
		ps.setInt(2, car.getQuantidadeItens());
		ps.setDouble(3, car.getTotal());
		ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setInt(6, u.getId());
	
		ps.execute();
		ps.close();
	}

	
	/**
	 * <p> Count Carrinhos in DB </p>
	 * @return int
	 * @since 1.0
	 */
	public int contarCarrinho() throws SQLException{
		String sql = "SELECT COUNT(id) AS contagem FROM carrinho";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getInt("contagem");
		}
		return 0;
	}
	
	
	/**
	 * <p> Search for a user's current cart </p>
	 * @param usuario
	 * @return Carrinho
	 * @since 1.0
	 */
	public Carrinho buscarCarrinhoAtual(Usuario usuario) throws SQLException {
		String sql = "SELECT c.*\r\n"
				+ "FROM carrinho c\r\n"
				+ "LEFT OUTER JOIN pedido p ON c.id = p.carrinhoid\r\n"
				+ "WHERE c.clienteusuarioid = ? AND p.carrinhoid IS NULL";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, usuario.getId());
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Carrinho car = new Carrinho(rs.getInt("id"), rs.getInt("quantidade_itens"), rs.getDouble("total"),
					rs.getDate("data_criacao").toLocalDate(), rs.getDate("data_ultima_modificacao").toLocalDate());
			return car;
		}
		return null;
	}

	
	/**
	 * <p> Update Carrinho </p>
	 * @param carrinhoAtual
	 * @since 1.0
	 */
	public void atualizarCarrinho(Carrinho carrinhoAtual) throws SQLException {
		String sql = "SELECT COUNT(produtoid) AS contagem, SUM(subtotal) AS total FROM\r\n"
				+ "item WHERE carrinhoid = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, carrinhoAtual.getId());
		ResultSet rs = ps.executeQuery();

		int quant = 0;
		double total = 0;
		
		if (rs.next()) {
			quant = rs.getInt("contagem");
			total = rs.getDouble("total");
		}
		
		String sql2 = "UPDATE carrinho SET quantidade_itens = ?, total = ? WHERE id = ?";
		PreparedStatement ps2 = c.prepareStatement(sql2);
		
		ps2.setInt(1, quant);
		ps2.setDouble(2, total);
		ps2.setInt(3, carrinhoAtual.getId());
		ps2.executeQuery();
	}
	
	
	/**
	 * <p> Search for the senders of the current card </p>
	 * @param car
	 * @return String
	 * @since 1.0
	 */
	public String buscarRemetentes(Carrinho car) throws SQLException {
		String sql = "SELECT DISTINCT(u.nome + ' ' + u.sobrenome) AS remetente\r\n"
				+ "FROM carrinho c, item i, produto p, lojista l, usuario u\r\n"
				+ "WHERE i.carrinhoid = c.id AND i.produtoid = p.id AND p.lojistaid = l.usuarioid AND u.id = l.usuarioid \r\n"
				+ "AND c.id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, car.getId());
		ResultSet rs = ps.executeQuery();

		String remetente = "";
		
		while (rs.next()) {
			remetente += rs.getString("remetente") + " ,";
		}
		
		return remetente.substring(0, remetente.length() - 1);
	}


}
