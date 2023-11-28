package application.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Carrinho;
import application.model.Item;
import application.model.Produto;
import application.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Persistence class for the Item entity
 */


public class ItemDao {
	
	/**
	 * Connection variable
	 */
	private Connection c;
	
	
	/**
	 * <p> Constructor </p>
	 * @throws SQLException, ClassNotFoundException
	 * @since 1.0
	 */
	public ItemDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	
	/**
	 * <p> Insert Item into DB </p>
	 * @param car, i, u, quant
	 * @throws SQLException
	 * @since 1.0
	 */
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
	
	
	/**
	 * <p> List items on current user's cart </p>
	 * @param carrinhoAtual
	 * @return ObservableList
	 * @throws SQLException
	 * @since 1.0
	 */
	public ObservableList<Item> listarItems(Carrinho carrinhoAtual) throws SQLException {
		String sql = "SELECT p.nome, i.quantidade_itens, i.subtotal\r\n"
				+ "FROM produto p, item i, carrinho c\r\n"
				+ "WHERE p.id = i.produtoid AND i.carrinhoid = c.id\r\n"
				+ "AND c.id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, carrinhoAtual.getId());
		
		ResultSet rs = ps.executeQuery();

		ObservableList<Item> itens = FXCollections.observableArrayList();
		while (rs.next()) {
			Item i = new Item(new Produto(rs.getString("nome")), rs.getInt("quantidade_itens"), rs.getDouble("subtotal"), null, null);
			itens.add(i);
		}
		
		return itens;
	}

}
