package application.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.persistence.CarrinhoDao;
import application.persistence.UsuarioDao;

/**
 * Model class for the Carrinho entity
 */


public class Carrinho {
	
	/**
	 * Carrinho's id
	 */
	private int id;
	/**
	 * Quantity of itens in cart
	 */
	private int quantidadeItens;
	/**
	 * Sum of subtotal of itens
	 */
	private double total;
	/**
	 * List of products in cart
	 */
	private ArrayList<Produto> produtos;
	/**
	 * Creation date
	 */
	private LocalDate dataCriacao;
	/**
	 * Update date
	 */
	private LocalDate dataUltimaModificacao;
	
	/**
	 * <p> Constructor </p>
	 * @param  id, quantidadeItens, total, dataCriacao, dataUltimaModificacao
	 * @return void
	 * @since 1.0
	 */
	public Carrinho(int id, int quantidadeItens, double total, LocalDate dataCriacao, LocalDate dataUltimaModificacao) {
		super();
		this.id = id;
		this.quantidadeItens = quantidadeItens;
		this.total = total;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> ID Getter </p>
	 * @return int
	 * @since 1.0
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * <p> id Setter </p>
	 * @param id
	 * @return void
	 * @since 1.0
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p> quantidadeItens Getter </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	/**
	 * <p> quantidadeItens Setter </p>
	 * @param int quantidadeItens
	 * @return Void
	 * @since 1.0
	 */
	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	/**
	 * <p> total Getter </p>
	 * @param None
	 * @return double
	 * @since 1.0
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * <p> total Setter </p>
	 * @param double total
	 * @return Void
	 * @since 1.0
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * <p> DataCriacao Getter </p>
	 * @param None
	 * @return LocalDate
	 * @since 1.0
	 */
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * <p> DataCriacao Setter </p>
	 * @param LocalDate dataCriacao
	 * @return Void
	 * @since 1.0
	 */
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * <p> DataUltimaModificacao Getter </p>
	 * @param None
	 * @return LocalDate
	 * @since 1.0
	 */
	public LocalDate getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	/**
	 * <p> DataUltimaModificacao Setter </p>
	 * @param LocalDate dataUltimaModificacao
	 * @return Void
	 * @since 1.0
	 */
	public void setDataUltimaModificacao(LocalDate dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> produtos Getter </p>
	 * @param None
	 * @return ArrayList<Produto>
	 * @since 1.0
	 */
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * <p> produtos Setter </p>
	 * @param ArrayList<Produto> produtos
	 * @return Void
	 * @since 1.0
	 */
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	/**
	 * <p> Generate new Carrinho ID </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public static int geraId() throws ClassNotFoundException, SQLException {
		CarrinhoDao cDao = new CarrinhoDao();
		return cDao.contarCarrinho() + 1;
	}
	

}
