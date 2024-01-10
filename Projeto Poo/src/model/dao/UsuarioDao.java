package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entidade.Usuario;

public class UsuarioDao {
	boolean sucesso;
	
	public void salvar(Usuario usuario) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
        String sql = "INSERT INTO USUARIO (NOME, SENHA, NIVEL) VALUES (?,?,?)";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
        	stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getNivel());
			
			stmt.execute();
			sucesso = true;
            stmt.close();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public void alterar(Usuario usuario, String nome) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
        String sql = "UPDATE USUARIO SET NOME = ?, SENHA = ?, NIVEL = ? WHERE NOME = ?";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
        	stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getNivel());
			
			stmt.setString(4, nome);
			
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
        String sql = "DELETE FROM USUARIO WHERE NOME = ?";
        
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
	
	public ArrayList<Usuario> consultar() {
    	Conexao conexao = new Conexao();
    	PreparedStatement stmt;
    	ArrayList<Usuario> usuarios;
    	
		try {
			stmt = conexao.getConn().prepareStatement("SELECT * FROM USUARIO");
		
	    	ResultSet rs = stmt.executeQuery();
	    	usuarios = new ArrayList<Usuario>();
	    	while (rs.next()) {
		    	Usuario usuario = new Usuario();
		    	usuario.setNome(rs.getString("NOME"));
		    	usuario.setSenha(rs.getString("SENHA"));
		    	usuario.setNivel(rs.getString("NIVEL"));
		    	usuarios.add(usuario);
	    	}
	    	
	    	rs.close();
	    	stmt.close();
	    	return usuarios;
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
    }
	
	public void logar(String login, String senha) {    	
    	Conexao conexao = new Conexao();
    	sucesso = false;
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE NOME = ? AND SENHA = ?";
        
        try {
        	PreparedStatement stmt = conexao.getConn().prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 1) sucesso = true;
			stmt.close();
		} 
        catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setSucesso(boolean sucesso) { this.sucesso = sucesso; }
	public boolean getSucesso() { return sucesso; }
}
