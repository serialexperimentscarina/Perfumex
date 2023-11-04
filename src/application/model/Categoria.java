package application.model;

import java.time.LocalDate;

public class Categoria {
	
	private int id;
	private String nome;
	private String descricao;
	private String publicoAlvo;
	private LocalDate dataCriacao;
	private LocalDate dataUltimaModificacao;
	
	public Categoria(int id, String nome, String descricao, String publicoAlvo, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.publicoAlvo = publicoAlvo;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
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

}
