package application.model;

import java.time.LocalDate;

public class Carrinho {
	
	private int id;
	private int quantidadeItens;
	private double total;
	private LocalDate dataCriacao;
	private LocalDate dataUltimaModificacao;
	
	public Carrinho(int id, int quantidadeItens, double total, LocalDate dataCriacao, LocalDate dataUltimaModificacao) {
		super();
		this.id = id;
		this.quantidadeItens = quantidadeItens;
		this.total = total;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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