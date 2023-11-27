package application.model;

import java.time.LocalDate;

/**
 * Model class for the Endereco entity
 */

public class Endereco {
	
	/**
	 * Usuario's Endereco
	 */
	private Usuario usuario;
	/**
	 * Street name
	 */
	private String rua;
	/**
	 * Housing number
	 */
	private int numero;
	/**
	 * CEP
	 */
	private String cep;
	/**
	 * (Optional) Complement
	 */
	private String complemento;
	/**
	 * State
	 */
	private String estado;
	/**
	 * City name
	 */
	private String cidade;
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
	 * @param Usuario usuario, String rua, int numero, String cep, String complemento, String estado,
			String cidade, LocalDate dataCriacao, LocalDate dataUltimaModificacao
	 * @return Void
	 * @since 1.0
	 */
	public Endereco(Usuario usuario, String rua, int numero, String cep, String complemento, String estado,
			String cidade, LocalDate dataCriacao, LocalDate dataUltimaModificacao) {
		super();
		this.usuario = usuario;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.estado = estado;
		this.cidade = cidade;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> Usuario Getter </p>
	 * @param None
	 * @return Usuario
	 * @since 1.0
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * <p> usuario Setter </p>
	 * @param Usuario usuario
	 * @return Void
	 * @since 1.0
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * <p> rua Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getRua() {
		return rua;
	}

	/**
	 * <p> rua Setter </p>
	 * @param String rua
	 * @return Void
	 * @since 1.0
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}

	/**
	 * <p> numero Getter </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * <p> numero Setter </p>
	 * @param int numero
	 * @return Void
	 * @since 1.0
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * <p> cep Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * <p> cep Setter </p>
	 * @param String cep
	 * @return Void
	 * @since 1.0
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * <p> complemento Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * <p> cep Setter </p>
	 * @param String cep
	 * @return Void
	 * @since 1.0
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * <p> estado Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * <p> estado Setter </p>
	 * @param String estado
	 * @return Void
	 * @since 1.0
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * <p> cidade Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getCidade() {
		return cidade;
	}
	/**
	 * <p> cidade Setter </p>
	 * @param String cidade
	 * @return Void
	 * @since 1.0
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
