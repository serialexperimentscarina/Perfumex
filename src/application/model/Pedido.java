package application.model;

import java.time.LocalDate;

/**
 * Model class for the Pedido entity
 */

public class Pedido {
	
	/**
	 * Carrinho this Pedido refers to
	 */
	private Carrinho carrinho;
	/**
	 * Type of payment
	 */
	private String tipoPagto;
	/**
	 * Pedido's status
	 */
	private String status;
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
	 * @param Carrinho carrinho, String tipoPagto, String status, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao
	 * @return Void
	 * @since 1.0
	 */
	public Pedido(Carrinho carrinho, String tipoPagto, String status, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao) {
		super();
		this.carrinho = carrinho;
		this.tipoPagto = tipoPagto;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> Carrinho Getter </p>
	 * @param None
	 * @return Carrinho
	 * @since 1.0
	 */
	public Carrinho getCarrinho() {
		return carrinho;
	}

	/**
	 * <p> Carrinho Setter </p>
	 * @param Carrinho carrinho
	 * @return Void
	 * @since 1.0
	 */
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	/**
	 * <p> tipoPagto Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getTipoPagto() {
		return tipoPagto;
	}

	/**
	 * <p> tipoPagto Setter </p>
	 * @param String tipoPagto
	 * @return Void
	 * @since 1.0
	 */
	public void setTipoPagto(String tipoPagto) {
		this.tipoPagto = tipoPagto;
	}

	/**
	 * <p> status Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * <p> status Setter </p>
	 * @param String status
	 * @return Void
	 * @since 1.0
	 */
	public void setStatus(String status) {
		this.status = status;
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
	

}
