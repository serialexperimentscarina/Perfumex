package application.persistence;
<<<<<<< HEAD
=======

>>>>>>> 3df9ffaa50cd54621bb257f0bcc302ee31e0ea02
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.controller.SessaoController;
import application.model.Produto;

public class ProdutoDao {
<<<<<<< HEAD
    private Connection c;

    public ProdutoDao() throws ClassNotFoundException, SQLException {
        GenericDao gDao = new GenericDao();
        c = gDao.getConnection();
    }

    public void inserirProduto(Produto p) throws SQLException {
    	String sql = "INSERT INTO produto (id, nome, preco, percentual_desconto, descricao, marca, fornecedor, quantidade_atual, quantidade_minima, data_criacao, data_ultima_modificacao, lojistaid) "
    	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement ps = c.prepareStatement(sql)) {
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

            // Check if SessaoController.usuario is not null before accessing its methods
            if (SessaoController.usuario != null) {
                ps.setInt(12, SessaoController.usuario.getId());
            } else {
                // Handle the case where SessaoController.usuario is null
                // You might want to log a message or take appropriate action
            }

            System.out.println("Valores a serem inseridos:");
            // Print other values as before

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
=======
	
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

>>>>>>> 3df9ffaa50cd54621bb257f0bcc302ee31e0ea02
}
