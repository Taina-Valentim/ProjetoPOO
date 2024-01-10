package model.entidade;

public class Usuario {
	private String nome;
	private String senha;
	private String nivel;
	
	public Usuario(String nome, String senha, String nivel) {
		this.nome = nome;
		this.senha = senha;
		this.nivel = nivel;
	}


	public Usuario() { }


	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }

	public String getNivel() { return nivel; }
	public void setNivel(String nivel) { this.nivel = nivel; }
	
}
