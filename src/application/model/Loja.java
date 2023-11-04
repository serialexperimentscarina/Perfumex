package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Loja {
	
	private int id;
	private String nome;
	private String ramo;
	private String emailComercial;
	private String telefoneComercial;
	private String descricao;
	private String status;
	private LocalDate dataCriacao;
	private LocalDate dataUltimaModificacao;
	private ArrayList<Produto> produtos;
	
	public Loja(int id, String nome, String ramo, String emailComercial, String telefoneComercial, String descricao,
			String status, LocalDate dataCriacao, LocalDate dataUltimaModificacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.ramo = ramo;
		this.emailComercial = emailComercial;
		this.telefoneComercial = telefoneComercial;
		this.descricao = descricao;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getEmailComercial() {
		return emailComercial;
	}

	public void setEmailComercial(String emailComercial) {
		this.emailComercial = emailComercial;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	public void setDataUltimaModificacao(LocalDate dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
