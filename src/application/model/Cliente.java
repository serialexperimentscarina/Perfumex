package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model class for the Cliente entity
 */

public class Cliente extends Usuario{

	/**
	 * Cliente's cpf
	 */
	private String cpf;

	/**
	 * <p> Constructor </p>
	 * @param int id, String nome, String sobrenome, String email, String senha, String telefone, String status,
			LocalDate dataCriacao, LocalDate dataUltimaModificacao, String cpf
	 * @return Void
	 * @since 1.0
	 */
	public Cliente(int id, String nome, String sobrenome, String email, String senha, String telefone, String status,
			LocalDate dataCriacao, LocalDate dataUltimaModificacao, String cpf) {
		super(id, nome, sobrenome, email, senha, telefone, status, dataCriacao, dataUltimaModificacao);
		this.cpf = cpf;
	}

	/**
	 * <p> cpf Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * <p> cpf Setter </p>
	 * @param String cpf
	 * @return Void
	 * @since 1.0
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
