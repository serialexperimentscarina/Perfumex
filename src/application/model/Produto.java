package application.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.persistence.ProdutoDao;
import application.persistence.UsuarioDao;

/**
 * Model class for the Produto entity
 */

public class Produto {
	
	/**
	 * Product's id
	 */
	private int id;
	/**
	 * Product's name
	 */
	private String nome;
	/**
	 * Product's price
	 */
	private double preco;
	/**
	 * Discount to be applied
	 */
	private double percentualDesconto;
	/**
	 * Product's description
	 */
	private String descricao;
	/**
	 * Product's brand
	 */
	private String marca;
	/**
	 * Product's supplier
	 */
	private String fornecedor;
	/**
	 * Current quantity
	 */
	private int quantidadeAtual;
	/**
	 * Minimum quantity
	 */
	private int quantidadeMinima;
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
	 * @param String nome
	 * @return Void
	 * @since 1.0
	 */
	public Produto(String nome) {
		super();
		this.nome = nome;
	}

	/**
	 * <p> Constructor </p>
	 * @param int id, String nome, double preco, double percentualDesconto, String descricao, String marca,
			String fornecedor, int quantidadeAtual, int quantidadeMinima, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao
	 * @return Void
	 * @since 1.0
	 */
	public Produto(int id, String nome, double preco, double percentualDesconto, String descricao, String marca,
			String fornecedor, int quantidadeAtual, int quantidadeMinima, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.percentualDesconto = percentualDesconto;
		this.descricao = descricao;
		this.marca = marca;
		this.fornecedor = fornecedor;
		this.quantidadeAtual = quantidadeAtual;
		this.quantidadeMinima = quantidadeMinima;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> ID Getter </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public int getId() {
		return id;
	}

	/**
	 * <p> ID Setter </p>
	 * @param int id
	 * @return Void
	 * @since 1.0
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p> Nome Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * <p> nome Setter </p>
	 * @param String nome
	 * @return Void
	 * @since 1.0
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * <p> Preco Getter </p>
	 * @param None
	 * @return double
	 * @since 1.0
	 */
	public double getPreco() {
		return preco;
	}
	
	/**
	 * <p> preco Setter </p>
	 * @param double preco
	 * @return Void
	 * @since 1.0
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * <p> percentualDesconto Getter </p>
	 * @param None
	 * @return double
	 * @since 1.0
	 */
	public double getPercentualDesconto() {
		return percentualDesconto;
	}

	/**
	 * <p> percentualDesconto Setter </p>
	 * @param double percentualDesconto
	 * @return Void
	 * @since 1.0
	 */
	public void setPercentualDesconto(double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	/**
	 * <p> descricao Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * <p> descricao Setter </p>
	 * @param double percentualDesconto
	 * @return Void
	 * @since 1.0
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * <p> marca Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * <p> marca Setter </p>
	 * @param String marca
	 * @return Void
	 * @since 1.0
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * <p> quantidadeAtual Getter </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

	/**
	 * <p> quantidadeAtual Setter </p>
	 * @param int quantidadeAtual
	 * @return Void
	 * @since 1.0
	 */
	public void setQuantidadeAtual(int quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	/**
	 * <p> quantidadeMinima Getter </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}

	/**
	 * <p> quantidadeMinima Setter </p>
	 * @param int quantidadeMinima
	 * @return Void
	 * @since 1.0
	 */
	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
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
	 * <p> fornecedor Getter </p>
	 * @param String
	 * @return int
	 * @since 1.0
	 */
	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	/**
	 * <p> Generate new Produto ID </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public static int geraId() throws ClassNotFoundException, SQLException {
		ProdutoDao pDao = new ProdutoDao();
		return pDao.contarProduto() + 1;
	}

}
