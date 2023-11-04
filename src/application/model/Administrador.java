package application.model;

import java.time.LocalDate;

public class Administrador extends Usuario{
	
	private String papel;

	public Administrador(int id, String nome, String sobrenome, String email, String senha, String telefone,
			String status, LocalDate dataCriacao, LocalDate dataUltimaModificacao, String papel) {
		super(id, nome, sobrenome, email, senha, telefone, status, dataCriacao, dataUltimaModificacao);
		this.papel = papel;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

}
