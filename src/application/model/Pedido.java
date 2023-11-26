package application.model;

import java.time.LocalDate;

public class Pedido {
	
	private Carrinho carrinho;
	private String tipoPagto;
	private String status;
	private LocalDate dataCriacao;
	private LocalDate dataUltimaModificacao;
	
	public Pedido(Carrinho carrinho, String tipoPagto, String status, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao) {
		super();
		this.carrinho = carrinho;
		this.tipoPagto = tipoPagto;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public String getTipoPagto() {
		return tipoPagto;
	}

	public void setTipoPagto(String tipoPagto) {
		this.tipoPagto = tipoPagto;
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

}
