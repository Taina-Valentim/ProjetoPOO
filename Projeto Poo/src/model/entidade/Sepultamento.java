package model.entidade;

import java.sql.Date;
import java.sql.Time;

public class Sepultamento {
	private int id;
	private Date dataSepultamento;
	private Time horarioEnterro;
	private String funeraria;
	private String responsavel;
	private String local;
	
	
	public Sepultamento(int id, Date dataSepultamento, Time horarioEnterro, String funeraria, String responsavel, String local) {
		this.id = id;
		this.dataSepultamento = dataSepultamento;
		this.horarioEnterro = horarioEnterro;
		this.funeraria = funeraria;
		this.responsavel = responsavel;
		this.local = local;
	}
	
	public Sepultamento() { }

	public Date getDataSepultamento() { return dataSepultamento; }
	public void setDataSepultamento(Date dataSepultamento) { this.dataSepultamento = dataSepultamento; }
	
	public Time getHorarioEnterro() { return horarioEnterro; }
	public void setHorarioEnterro(Time horarioEnterro) { this.horarioEnterro = horarioEnterro; }

	public String getFuneraria() { return funeraria; }
	public void setFuneraria(String funeraria) { this.funeraria = funeraria; }

	public String getResponsavel() { return responsavel; }
	public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

	public String getLocal() { return local; }
	public void setLocal(String local) { this.local = local; }

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
}
