package application.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.controller.SessaoController;
import application.model.Produto;
import javafx.scene.control.cell.PropertyValueFactory;

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
	
	public ResultSet buscarProdutosCliente() throws SQLException {
		String sql = "SELECT * FROM produto WHERE quantidade_atual >= quantidade_minima";		
		PreparedStatement ps = c.prepareStatement(sql);		
		
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet buscarProdutosLojista() throws SQLException {
		String sql = "SELECT * FROM produto WHERE lojistaid = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, SessaoController.usuario.getId());
		
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}

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
	public void atualizarValorProduto(int idProduto, double novoValor) throws SQLException {
        String sql = "UPDATE produto SET preco = ? WHERE id = ?";

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDouble(1, novoValor);
            ps.setInt(2, idProduto);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Valor do produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum produto teve o valor atualizado. Verifique o ID e tente novamente.");
            }
        }
    }
	
	

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



}
		
	
	
	
	
	
	



