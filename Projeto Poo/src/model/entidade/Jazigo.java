package model.entidade;

public class Jazigo {
	private String nomeFamilia;
	private String local;
	private Integer tamanho;
	private Integer qtdSepulturas;
	private Integer sepOcupadas;
	
	
	public Jazigo(String nomeFamilia, String local, Integer tamanho, Integer qtdSepulturas, Integer sepOcupadas) {
		this.nomeFamilia = nomeFamilia;
		this.local = local;
		this.tamanho = tamanho;
		this.qtdSepulturas = qtdSepulturas;
		this.sepOcupadas = sepOcupadas;
		
	}
	public Jazigo () { }
	
	
	public String getNomeFamilia() { return nomeFamilia;}
	public void setNomeFamilia(String nomeFamilia) { this.nomeFamilia = nomeFamilia; }
	
	public String getLocal() { return local; }
	public void setLocal(String local) { this.local = local; }
	
	public Integer getTamanho() { return tamanho; }
	public void setTamanho(Integer tamanho) { this.tamanho = tamanho; }
	
	public Integer getQtdSepulturas() { return qtdSepulturas; }
	public void setQtdSepulturas(Integer qtdSepulturas) { this.qtdSepulturas = qtdSepulturas; }
	
	public Integer getSepOcupadas() { return sepOcupadas; }
	public void setSepOcupadas(Integer sepOcupadas) { this.sepOcupadas = sepOcupadas; }
	
}
