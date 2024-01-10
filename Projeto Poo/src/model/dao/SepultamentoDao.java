package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entidade.Sepultamento;

public class SepultamentoDao {
	boolean sucesso;
	
	public void salvar(Sepultamento sepultamento, String nomeFalecido) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
    	int idObito = obterIdObito(nomeFalecido);
    	String sql = "INSERT INTO SEPULTAMENTO (DATASEPULTAMENTO, HORARIOENTERRO, FUNERARIA, RESPONSAVEL, LOCAL, IDOBITO) VALUES (?,?,?,?,?,?)";
        
    	try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			stmt.setDate(1, sepultamento.getDataSepultamento());
			stmt.setTime(2, sepultamento.getHorarioEnterro());
			stmt.setString(3, sepultamento.getFuneraria());
			stmt.setString(4, sepultamento.getResponsavel());
			stmt.setString(5, sepultamento.getLocal());
			stmt.setInt(6, idObito);
			
			stmt.execute();
			sucesso = true;
            stmt.close();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Sepultamento sepultamento, String nome, String nomeFalecido) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
    	int idObito = obterIdObito(nomeFalecido);
        String sql = "UPDATE SEPULTAMENTO SET DATASEPULTAMENTO = ?, HORARIOENTERRO = ?, FUNERARIA = ?, RESPONSAVEL = ?, LOCAL = ? WHERE IDOBITO = ?";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			stmt.setDate(1, sepultamento.getDataSepultamento());
			stmt.setTime(2, sepultamento.getHorarioEnterro());
			stmt.setString(3, sepultamento.getFuneraria());
			stmt.setString(4, sepultamento.getResponsavel());
			stmt.setString(5, sepultamento.getLocal());
			stmt.setInt(6, idObito);
			stmt.setString(7, nome);
			
			stmt.execute();
			sucesso = true;
			stmt.close();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(String nome) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
        String sql = "DELETE FROM SEPULTAMENTO WHERE IDOBITO = ?";
        
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
	
	public ArrayList<Sepultamento> consulta() {
    	Conexao conexao = new Conexao();
    	PreparedStatement stmt;
    	ArrayList<Sepultamento> sepultamentos;
    	
		try {
			stmt = conexao.getConn().prepareStatement("SELECT * FROM SEPULTAMENTO");
		
	    	ResultSet rs = stmt.executeQuery();
	    	sepultamentos = new ArrayList<Sepultamento>();
	    	while (rs.next()) {
	    		Sepultamento sepultamento = new Sepultamento();
	    		sepultamento.setId(rs.getInt("IDOBITO"));
	    		sepultamento.setDataSepultamento(rs.getDate("DATASEPULTAMENTO"));
	    		sepultamento.setHorarioEnterro(rs.getTime("HORARIOENTERRO"));
	    		sepultamento.setFuneraria(rs.getString("FUNERARIA"));
	    		sepultamento.setResponsavel(rs.getString("RESPONSAVEL"));
	    		sepultamento.setLocal(rs.getString("LOCAL"));
		    	sepultamentos.add(sepultamento);
	    	}
	    	
	    	rs.close();
	    	stmt.close();
	    	return sepultamentos;
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
    }
	
	public int obterIdObito(String nomeFalecido) {
		Conexao conexao = new Conexao();
		String sql = "SELECT IDOBITO FROM OBITO WHERE NOME = ?";
		int retorno = 0;
		
		try {
	    	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
	    	stmt.setString(1, nomeFalecido);
	    	ResultSet rs = stmt.executeQuery();
	    	if (rs.next()) {
	    		retorno = rs.getInt("IDOBITO");
	    	}
	    	else System.out.println("Nenhum resultado encontrado.");
	        stmt.close();
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public String buscaFalecido(int id) {
		Conexao conexao = new Conexao();
		String sql = "SELECT NOME FROM OBITO WHERE IDOBITO = ?";
		String retorno = "Pessoa não encontrada";
		
		try {
	    	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
	    	stmt.setInt(1, id);
	    	ResultSet rs = stmt.executeQuery();
	    	if (rs.next()) retorno = rs.getString("NOME");
	    	else System.out.println("Nenhum resultado encontrado.");
	        stmt.close();
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }
	public boolean getSucesso() { return sucesso; }
}
