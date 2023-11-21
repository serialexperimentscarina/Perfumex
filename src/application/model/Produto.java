package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Produto {
	
	private int id;
	private String nome;
	private double preco;
	private double percentualDesconto;
	private String descricao;
	private String marca;
	private String fornecedor;
	private int quantidadeAtual;
	private int quantidadeMinima;
	private LocalDate dataCriacao;
	private LocalDate dataUltimaModificacao;
<<<<<<< HEAD
	private Categoria categoria;
	private String Status;

	public Produto(int id, String nome, double preco, double percentualDesconto, String descricao, String marca,
			String fornecedor, int quantidadeAtual, int quantidadeMinima, LocalDate dataCriacao, String status,
			LocalDate dataUltimaModificacao, Categoria categoria,  ArrayList<Produto> produtos) {
=======

	public Produto(int id, String nome, double preco, double percentualDesconto, String descricao, String marca,
			String fornecedor, int quantidadeAtual, int quantidadeMinima, LocalDate dataCriacao,
			LocalDate dataUltimaModificacao) {
>>>>>>> 3df9ffaa50cd54621bb257f0bcc302ee31e0ea02
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
<<<<<<< HEAD
		this.Status=status;
=======
>>>>>>> 3df9ffaa50cd54621bb257f0bcc302ee31e0ea02
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(int quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
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
<<<<<<< HEAD

=======
	
>>>>>>> 3df9ffaa50cd54621bb257f0bcc302ee31e0ea02
	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	
<<<<<<< HEAD
	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}
=======
	public static int geraId() {
		//TBA: Verificar se ID já existe no banco
		return (int) (Math.random() * 10000);
	}
	
>>>>>>> 3df9ffaa50cd54621bb257f0bcc302ee31e0ea02

}
