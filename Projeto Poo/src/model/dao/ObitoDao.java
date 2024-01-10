package model.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entidade.Obito;

public class ObitoDao {
	boolean sucesso;
	
	public void salvar(Obito obito, String nomeFamilia) {    	
		Conexao conexao = new Conexao();
		sucesso = false;
		int idJazigo = obterIdJazigo(nomeFamilia);
		String sql = "INSERT INTO OBITO (NOME, DATAOBITO, CPF, CIDADE, CARTORIO, LIVRO, FOLHA, TERMO, MEDICO, CRM, IDJAZIGO) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
	    	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
	    	stmt.setString(1, obito.getNome());
			stmt.setDate(2, Date.valueOf(obito.getDataObito()));
			stmt.setString(3, obito.getCpf());
			stmt.setString(4, obito.getCidade());
			stmt.setString(5, obito.getCartorio());
			stmt.setInt(6, obito.getLivro());
			stmt.setInt(7, obito.getFolha());
			stmt.setInt(8, obito.getTermo());
			stmt.setString(9, obito.getMedico());
			stmt.setInt(10, obito.getCrm());
			stmt.setInt(11, idJazigo);
			
			stmt.execute();
			sucesso = true;
	        stmt.close();
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Obito obito, String nome, String nomeFamilia) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
    	int idJazigo = obterIdJazigo(nomeFamilia);
        String sql = "UPDATE OBITO SET NOME = ?, DATAOBITO = ?, CPF = ?, CIDADE = ?, CARTORIO = ?, LIVRO = ?, FOLHA = ?, TERMO = ?, MEDICO = ?, CRM = ?, IDJAZIGO = ? WHERE NOME = ?";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
        	stmt.setString(1, obito.getNome());
			stmt.setDate(2, Date.valueOf(obito.getDataObito()));
			stmt.setString(3, obito.getCpf());
			stmt.setString(4, obito.getCidade());
			stmt.setString(5, obito.getCartorio());
			stmt.setInt(6, obito.getLivro());
			stmt.setInt(7, obito.getFolha());
			stmt.setInt(8, obito.getTermo());
			stmt.setString(9, obito.getMedico());
			stmt.setInt(10, obito.getCrm());
			stmt.setInt(11, idJazigo);
			stmt.setString(12, nome);
			
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
        String sql = "DELETE FROM OBITO WHERE NOME = ?";
        
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
	
	public ArrayList<Obito> consultar() {
    	Conexao conexao = new Conexao();
    	PreparedStatement stmt;
    	ArrayList<Obito> obitos;
    	
		try {
			stmt = conexao.getConn().prepareStatement("SELECT * FROM OBITO");
		
	    	ResultSet rs = stmt.executeQuery();
	    	obitos = new ArrayList<Obito>();
	    	while (rs.next()) {
	    		Obito obito = new Obito();
		    	obito.setNome(rs.getString("NOME"));
		    	Date sqlDate = rs.getDate("DATAOBITO");
		    	LocalDate localDate = sqlDate.toLocalDate();
		    	obito.setDataObito(localDate);
		    	obito.setCpf(rs.getString("CPF"));
		    	obito.setCidade(rs.getString("CIDADE"));
		    	obito.setCartorio(rs.getString("CARTORIO"));
		    	obito.setLivro(Integer.parseInt(rs.getString("LIVRO")));
		    	obito.setFolha(Integer.parseInt(rs.getString("FOLHA")));
		    	obito.setTermo(Integer.parseInt(rs.getString("TERMO")));
		    	obito.setMedico(rs.getString("MEDICO"));
		    	obito.setCrm(Integer.parseInt(rs.getString("CRM")));
		    	obitos.add(obito);
	    	}
	    	
	    	rs.close();
	    	stmt.close();
	    	return obitos;
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
    }
	
	public int obterIdJazigo(String nomeFamilia) {
		Conexao conexao = new Conexao();
		String sql = "SELECT IDJAZIGO FROM JAZIGO WHERE NOMEFAMILIA = ?";
		int retorno = 0;
		
		try {
	    	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
	    	stmt.setString(1, nomeFamilia);
	    	ResultSet rs = stmt.executeQuery();
	    	if (rs.next()) {
	    		retorno = rs.getInt("IDJAZIGO");
	    	}
	    	else System.out.println("Nenhum resultado encontrado.");
	        stmt.close();
		} 
	    catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public String buscaFamilia(String nome) {
		Conexao conexao = new Conexao();
		String buscaIdJazigo = "SELECT IDJAZIGO FROM OBITO WHERE NOME = ?";
		String sql = "SELECT NOMEFAMILIA FROM JAZIGO WHERE IDJAZIGO = ?";
		String retorno = "Família não encontrada";
		
		try {
	    	PreparedStatement stmt = conexao.getConn().prepareStatement(buscaIdJazigo);
	    	stmt.setString(1, nome);
	    	ResultSet rs = stmt.executeQuery();
	    	if (rs.next()) {
	    		int id = rs.getInt("IDJAZIGO");
	    		stmt = conexao.getConn().prepareStatement(sql);
		    	stmt.setInt(1, id);
		    	rs = stmt.executeQuery();
		    	if (rs.next()) retorno = rs.getString("NOMEFAMILIA");
		    	else System.out.println("Nenhum resultado encontrado.");
	    	}
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
