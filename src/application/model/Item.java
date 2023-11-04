package application.model;

import java.time.LocalDate;

public class Item {
	
	private Produto produto;
	private int quantidadeItens;
	private double subtotal;
	private LocalDate dataCriacao;
	private LocalDate dataUltimaModificacao;
	
	public Item(Produto produto, int quantidadeItens, double subtotal, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao) {
		super();
		this.produto = produto;
		this.quantidadeItens = quantidadeItens;
		this.subtotal = subtotal;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
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
