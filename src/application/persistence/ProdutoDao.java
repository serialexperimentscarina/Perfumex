package application.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.controller.SessaoController;
import application.model.Produto;

public class ProdutoDao {

private Connection c;
	
	public ProdutoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void inserirProduto(Produto p) throws SQLException {
		String sql = "INSERT INTO produto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ps.setString(2, p.getNome());
		ps.setDouble(3, p.getPreco());
		ps.setDouble(4, p.getPercentualDesconto());
		ps.setString(5, p.getDescricao());
		ps.setString(6, p.getMarca());
		ps.setString(7, p.getFornecedor());
		ps.setInt(8, p.getQuantidadeAtual());
		ps.setInt(9, p.getQuantidadeMinima());
		ps.setDate(10, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setDate(11, java.sql.Date.valueOf(java.time.LocalDate.now()));
		ps.setInt(12, SessaoController.usuario.getId());
	
		ps.execute();
		ps.close();
	}
	
	public ResultSet buscarProdutosLojista() throws SQLException {
		String sql = "SELECT * FROM produto WHERE lojistaid = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, SessaoController.usuario.getId());
		
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}

}
