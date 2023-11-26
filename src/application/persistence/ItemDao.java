package application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.model.Carrinho;
import application.model.Cliente;
import application.model.Item;
import application.model.Produto;
import application.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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