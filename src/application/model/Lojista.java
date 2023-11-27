package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model class for the Lojista entity
 */

public class Lojista extends Usuario{
	
	/**
	 * Lojista's CNPJ
	 */
	private String cnpj;
	/**
	 * Lojista's Produtos
	 */
	private ArrayList<Produto> produtos;

	/**
	 * <p> Constructor </p>
	 * @param int id, String nome, String sobrenome, String email, String senha, String telefone, String status,
			LocalDate dataCriacao, LocalDate dataUltimaModificacao, String cnpj
	 * @return Void
	 * @since 1.0
	 */
	public Lojista(int id, String nome, String sobrenome, String email, String senha, String telefone, String status,
			LocalDate dataCriacao, LocalDate dataUltimaModificacao, String cnpj) {
		super(id, nome, sobrenome, email, senha, telefone, status, dataCriacao, dataUltimaModificacao);
		this.cnpj = cnpj;
	}

	/**
	 * <p> cnpj Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * <p> cnpj Setter </p>
	 * @param String cnpj
	 * @return Void
	 * @since 1.0
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

}
