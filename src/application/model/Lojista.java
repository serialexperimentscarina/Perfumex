package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lojista extends Usuario{
	
	private String cnpj;
	private ArrayList<Produto> produtos;

	public Lojista(int id, String nome, String sobrenome, String email, String senha, String telefone, String status,
			LocalDate dataCriacao, LocalDate dataUltimaModificacao, String cnpj) {
		super(id, nome, sobrenome, email, senha, telefone, status, dataCriacao, dataUltimaModificacao);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

}
