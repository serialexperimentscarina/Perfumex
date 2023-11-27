package application.model;

import java.time.LocalDate;

/**
 * Model class for the Item entity
 */

public class Item {
	
	/**
	 * Produto this item refers to
	 */
	private Produto produto;
	/**
	 * Quantity of item copies
	 */
	private int quantidadeItens;
	/**
	 * Subtotal of itens
	 */
	private double subtotal;
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
	 * @param produto, quantidadeItens, subtotal, dataCriacao,
		 dataUltimaModificacao
	 * @since 1.0
	 */
	public Item(Produto produto, int quantidadeItens, double subtotal, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao) {
		super();
		this.produto = produto;
		this.quantidadeItens = quantidadeItens;
		this.subtotal = subtotal;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> Produto Getter </p>
	 * @return Produto
	 * @since 1.0
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * <p> Produto Setter </p>
	 * @param produto
	 * @since 1.0
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
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
	 * <p> subtotal Getter </p>
	 * @return double
	 * @since 1.0
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * <p> subtotal Setter </p>
	 * @param subtotal
	 * @since 1.0
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
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
	
}
