package application.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.controller.SessaoController;
import application.model.Produto;
import application.model.Usuario;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Persistence class for the Produto entity
 */

public class ProdutoDao {

	/**
	 * Connection variable
	 */
	private Connection c;
	
	/**
	 * <p> Constructor </p>
	 * @since 1.0
	 */
	public ProdutoDao() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	/**
	 * <p> Insert product into DB </p>
	 * @param p
	 * @since 1.0
	 */
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
	
	/**
	 * <p> Search avaliable products </p>
	 * @return ResultSet
	 * @since 1.0
	 */
	public ResultSet buscarProdutosCliente() throws SQLException {
		String sql = "SELECT * FROM produto WHERE quantidade_atual > quantidade_minima";		
		PreparedStatement ps = c.prepareStatement(sql);		
		
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	/**
	 * <p> Search current Lojista's products </p>
	 * @return ResultSet
	 * @since 1.0
	 */
	public ResultSet buscarProdutosLojista() throws SQLException {
		String sql = "SELECT * FROM produto WHERE lojistaid = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, SessaoController.usuario.getId());
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	/**
	 * <p> Count the number of Produtos in DB </p>
	 * @return int
	 * @since 1.0
	 */
	public int contarProduto() throws SQLException{
		String sql = "SELECT COUNT(id) AS contagem FROM produto";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getInt("contagem");
		}
		return 0;
	
	}

	/**
	 * <p> Delete an specific product by ID </p>
	 * @param id
	 * @since 1.0
	 */
	public void deletarProduto(int id) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto deletado com sucesso!");
            } else {
                System.out.println("Nenhum produto foi deletado. Verifique o ID e tente novamente.");
            }
        }
	}
	
	/**
	 * <p> Search a Produto by it's ID </p>
	 * @param id
	 * @return Produto
	 * @since 1.0
	 */
	public Produto buscarProdutoPorId(int id) throws SQLException {
		
		 String sql = "SELECT * FROM produto WHERE id = ?";
		    try (PreparedStatement ps = c.prepareStatement(sql)) {
		        ps.setInt(1, id);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            return new Produto(
		                    rs.getInt("id"),
		                    rs.getString("nome"),
		                    rs.getFloat("preco"),
		                    rs.getFloat("percentual_desconto"),
		                    rs.getString("descricao"),
		                    rs.getString("marca"),
		                    rs.getString("fornecedor"),
		                    rs.getInt("quantidade_atual"),
		                    rs.getInt("quantidade_minima"),
		                    rs.getDate("data_criacao").toLocalDate(),
		                    rs.getDate("data_ultima_modificacao").toLocalDate()
		            );
		        }
		    }
		    return null; // Return null if the product is not found
		}

	/**
	 * <p> Update a Produto's attributes </p>
	 * @param produto
	 * @since 1.0
	 */
	public void atualizarProduto(Produto produto) throws SQLException {
	    String sql = "UPDATE produto SET nome = ?, preco = ?, percentual_desconto = ?, descricao = ?, marca = ?, fornecedor = ?, quantidade_atual = ?, quantidade_minima = ?, data_ultima_modificacao = ? WHERE id = ?";
	    try (PreparedStatement ps = c.prepareStatement(sql)) {
	        ps.setString(1, produto.getNome());
	        ps.setDouble(2, produto.getPreco());
	        ps.setDouble(3, produto.getPercentualDesconto());
	        ps.setString(4, produto.getDescricao());
	        ps.setString(5, produto.getMarca());
	        ps.setString(6, produto.getFornecedor());
	        ps.setInt(7, produto.getQuantidadeAtual());
	        ps.setInt(8, produto.getQuantidadeMinima());
	        ps.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));
	        ps.setInt(10, produto.getId());

	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Produto atualizado com sucesso!");
	        } else {
	            System.out.println("Nenhum produto teve as informações atualizadas. Verifique o ID e tente novamente.");
	        }
	    }
	}
	
	/**
	 * <p> Generate statistics of a Lojista's Produtos </p>
	 * @param u
	 * @return ResultSet
	 * @since 1.0
	 */
	public ResultSet estatisticasProduto(Usuario u) throws SQLException {
		String sql = "SELECT COUNT(id) AS total, \r\n"
				+ "SUM(CASE WHEN quantidade_atual > quantidade_minima THEN 1 ELSE 0 END) AS ativos, \r\n"
				+ "SUM(CASE WHEN quantidade_atual <= quantidade_minima THEN 1 ELSE 0 END) AS inativos\r\n"
				+ "FROM produto\r\n"
				+ "WHERE lojistaid = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		return rs;
 	}

	/**
	 * <p> Generate statistics of a Lojista's Produto's values </p>
	 * @param u
	 * @return ResultSet
	 * @since 1.0
	 */
	public ResultSet estatisticasValorProduto(Usuario u) throws SQLException {
		String sql = "SELECT MAX(preco) AS maximo, MIN(preco) AS minimo, AVG(preco) AS media\r\n"
				+ "FROM produto\r\n"
				+ "WHERE lojistaid = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}

	/**
	 * <p> Count quantities of sold Produtos </p>
	 * @param u
	 * @return int
	 * @since 1.0
	 */
	public int quantVendidosPorLojista(Usuario u) throws SQLException {
		String sql = "SELECT SUM(quantidade_itens) AS vendidos\r\n"
				+ "FROM item i, produto p, lojista l\r\n"
				+ "WHERE i.produtoid = p.id AND l.usuarioid = p.lojistaid AND l.usuarioid = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getInt("vendidos");
		}
		
		return 0;
	}
	
	/**
	 * <p> Count quantities of Produtos not sold </p>
	 * @param u
	 * @return int
	 * @since 1.0
	 */
	public int quantNaoVendidosPorLojista(Usuario u) throws SQLException {
		String sql = "SELECT COUNT(p.id) AS sem_vendas\r\n"
				+ "FROM produto p LEFT OUTER JOIN item i ON i.produtoid = p.id \r\n"
				+ "WHERE p.lojistaid = ? AND i.produtoid IS NULL";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, u.getId());
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			return rs.getInt("sem_vendas");
		}
		
		return 0;
	}

	
	/**
	 * <p> Update Produtos quantity </p>
	 * @param p, quant
	 * @since 1.0
	 */
	public void atualizarEstoque(Produto p, int quant) throws SQLException {
		String sql = "UPDATE produto SET quantidade_atual ? WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, quant);
		ps.setInt(2, p.getId());
		ResultSet rs = ps.executeQuery();
	}

}
		
	
	
	
	
	
	



