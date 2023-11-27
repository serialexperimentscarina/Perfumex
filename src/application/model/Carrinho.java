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
	 * @since 1.0
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p> quantidadeItens Getter </p>
	 * @return int
	 * @since 1.0
	 */
	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	/**
	 * <p> quantidadeItens Setter </p>
	 * @param quantidadeItens
	 * @since 1.0
	 */
	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	/**
	 * <p> total Getter </p>
	 * @return double
	 * @since 1.0
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * <p> total Setter </p>
	 * @param total
	 * @since 1.0
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * <p> DataCriacao Getter </p>
	 * @return LocalDate
	 * @since 1.0
	 */
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * <p> DataCriacao Setter </p>
	 * @param dataCriacao
	 * @since 1.0
	 */
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * <p> DataUltimaModificacao Getter </p>
	 * @return LocalDate
	 * @since 1.0
	 */
	public LocalDate getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	/**
	 * <p> DataUltimaModificacao Setter </p>
	 * @param dataUltimaModificacao
	 * @since 1.0
	 */
	public void setDataUltimaModificacao(LocalDate dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> produtos Getter </p>
	 * @return ArrayList
	 * @since 1.0
	 */
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * <p> produtos Setter </p>
	 * @param produtos
	 * @since 1.0
	 */
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	/**
	 * <p> Generate new Carrinho ID </p>
	 * @return int
	 * @since 1.0
	 */
	public static int geraId() throws ClassNotFoundException, SQLException {
		CarrinhoDao cDao = new CarrinhoDao();
		return cDao.contarCarrinho() + 1;
	}
	

}
