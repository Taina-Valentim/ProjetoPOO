package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entidade.Jazigo;

public class JazigoDao {
	boolean sucesso;
	
	public void salvar(Jazigo jazigo) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
        String sql = "INSERT INTO JAZIGO (NOMEFAMILIA, LOCAL, TAMANHO, QTDSEPULTURAS, SEPOCUPADAS) VALUES (?,?,?,?,?)";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
        	stmt.setString(1, jazigo.getNomeFamilia());
			stmt.setString(2, jazigo.getLocal());
			stmt.setInt(3, jazigo.getTamanho());
			stmt.setInt(4, jazigo.getQtdSepulturas());
			stmt.setInt(5, jazigo.getSepOcupadas());
			
			stmt.execute();
			sucesso = true;
            stmt.close();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Jazigo jazigo, String nome) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
        String sql = "UPDATE JAZIGO SET NOMEFAMILIA = ?, LOCAL = ?, TAMANHO = ?, QTDSEPULTURAS = ?, SEPOCUPADAS = ? WHERE NOMEFAMILIA = ?";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
        	stmt.setString(1, jazigo.getNomeFamilia());
			stmt.setString(2, jazigo.getLocal());
			stmt.setInt(3, jazigo.getTamanho());
			stmt.setInt(4, jazigo.getQtdSepulturas());
			stmt.setInt(5, jazigo.getSepOcupadas());
			stmt.setString(6, nome);
			
			stmt.execute();
			sucesso = true;
			stmt.close();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Jazigo jazigo, String nome) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
        String sql = "DELETE FROM JAZIGO WHERE NOMEFAMILIA = ?";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			stmt.setString(1, nome);
			
			stmt.execute();
			sucesso = true;
			stmt.close();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Jazigo> consultar() {
    	Conexao conexao = new Conexao();
    	PreparedStatement stmt;
    	ArrayList<Jazigo> jazigos;
    	
		try {
			stmt = conexao.getConn().prepareStatement("SELECT * FROM JAZIGO");
		
	    	ResultSet rs = stmt.executeQuery();
	    	jazigos = new ArrayList<Jazigo>();
	    	while (rs.next()) {
		    	Jazigo jazigo = new Jazigo();
		    	jazigo.setNomeFamilia(rs.getString("NOMEFAMILIA"));
		    	jazigo.setLocal(rs.getString("LOCAL"));
		    	jazigo.setTamanho(Integer.parseInt(rs.getString("TAMANHO")));
		    	jazigo.setQtdSepulturas(Integer.parseInt(rs.getString("QTDSEPULTURAS")));
		    	jazigo.setSepOcupadas(Integer.parseInt(rs.getString("SEPOCUPADAS")));
		    	jazigos.add(jazigo);
	    	}
	    	
	    	rs.close();
	    	stmt.close();
	    	return jazigos;
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }
	public boolean getSucesso() { return sucesso; }
}
