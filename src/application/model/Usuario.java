package application.model;

import java.sql.SQLException;
import java.time.LocalDate;

import application.persistence.UsuarioDao;

/**
 * Model class for the Usuario entity
 */

public class Usuario {
	
	/**
	 * User's ID
	 */
	private int id;
	/**
	 * User's name
	 */
	private String nome;
	/**
	 * User's last name
	 */
	private String sobrenome;
	/**
	 * User's email (unique)
	 */
	private String email;
	/**
	 * User's password
	 */
	private String senha;
	/**
	 * User's phone number
	 */
	private String telefone;
	/**
	 * User's current status
	 */
	private String status;
	/**
	 * Creation date
	 */
	private LocalDate dataCriacao;
	/**
	 * Update date
	 */
	private LocalDate dataUltimaModificacao;
	
	
	/**
	 * <p> Constructor </p>
	 * @param int id, String nome, String sobrenome, String email, String senha, String telefone, String status,
			LocalDate dataCriacao, LocalDate dataUltimaModificacao
	 * @return Void
	 * @since 1.0
	 */
	public Usuario(int id, String nome, String sobrenome, String email, String senha, String telefone, String status,
			LocalDate dataCriacao, LocalDate dataUltimaModificacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.dataUltimaModificacao = dataUltimaModificacao;
	}

	/**
	 * <p> ID Getter </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public int getId() {
		return id;
	}


	/**
	 * <p> ID Setter </p>
	 * @param int id
	 * @return Void
	 * @since 1.0
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <p> Nome Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * <p> Nome Setter </p>
	 * @param String nome
	 * @return Void
	 * @since 1.0
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * <p> Sobrenome Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getSobrenome() {
		return sobrenome;
	}

	/**
	 * <p> Sobrenome Setter </p>
	 * @param String sobrenome
	 * @return Void
	 * @since 1.0
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	/**
	 * <p> Email Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <p> Email Setter </p>
	 * @param int id
	 * @return Void
	 * @since 1.0
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p> Senha Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * <p> Senha Setter </p>
	 * @param String senha
	 * @return Void
	 * @since 1.0
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * <p> Telefone Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * <p> Telefone Setter </p>
	 * @param String telefone
	 * @return Void
	 * @since 1.0
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * <p> Status Getter </p>
	 * @param None
	 * @return String
	 * @since 1.0
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * <p> Status Setter </p>
	 * @param String status
	 * @return Void
	 * @since 1.0
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * <p> DataCriacao Getter </p>
	 * @param None
	 * @return LocalDate
	 * @since 1.0
	 */
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * <p> DataCriacao Setter </p>
	 * @param LocalDate dataCriacao
	 * @return Void
	 * @since 1.0
	 */
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * <p> DataUltimaModificacao Getter </p>
	 * @param None
	 * @return LocalDate
	 * @since 1.0
	 */
	public LocalDate getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}

	/**
	 * <p> DataUltimaModificacao Setter </p>
	 * @param LocalDate dataUltimaModificacao
	 * @return Void
	 * @since 1.0
	 */
	public void setDataUltimaModificacao(LocalDate dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}
	
	/**
	 * <p> Generate new Usuario ID </p>
	 * @param None
	 * @return int
	 * @since 1.0
	 */
	public static int geraId() throws ClassNotFoundException, SQLException {
		UsuarioDao uDao = new UsuarioDao();
		return uDao.contarUsuario() + 1;
	}
	
	
}
