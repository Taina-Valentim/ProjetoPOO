package model.entidade;

import java.time.LocalDate;

public class Obito {
	private String nome;
	private LocalDate dataObito;
	private String cpf;
	private String cidade;
	private String cartorio;
	private int livro;
	private int folha;
	private int termo;
	private String medico;
	private int crm;
	
	
	public Obito(String nome, LocalDate dataObito, String cpf, String cidade, String cartorio, int livro, int folha, int termo, String medico, int crm) {
		this.nome = nome;
		this.dataObito = dataObito;
		this.cpf = cpf;
		this.cidade = cidade;
		this.cartorio = cartorio;
		this.livro = livro;
		this.folha = folha;
		this.termo = termo;
		this.medico = medico;
		this.crm = crm;
	}


	public Obito() { };
	
	
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public LocalDate getDataObito() { return dataObito; }
	public void setDataObito(LocalDate date) { this.dataObito = date; }

	public String getCpf() { return cpf; }
	public void setCpf(String cpf) { this.cpf = cpf; }

	public String getCidade() { return cidade; }
	public void setCidade(String cidade) { this.cidade = cidade; }

	public String getCartorio() { return cartorio; }
	public void setCartorio(String cartorio) { this.cartorio = cartorio; }

	public int getLivro() { return livro; }
	public void setLivro(int livro) { this.livro = livro; }

	public int getFolha() { return folha; }
	public void setFolha(int folha) { this.folha = folha; }

	public int getTermo() { return termo; }
	public void setTermo(int termo) { this.termo = termo; }

	public String getMedico() { return medico; }
	public void setMedico(String medico) { this.medico = medico; }

	public int getCrm() { return crm; }
	public void setCrm(int crm) { this.crm = crm; }
}
