package model.service;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import controller.verificacoes.Criptografia;
import model.dao.UsuarioDao;
import model.entidade.Usuario;

public class UsuarioService {
    private UsuarioDao dao;

    public UsuarioService() {
        this.dao = new UsuarioDao();
    }
                                                                                           
    public boolean salvar(Usuario calculo) throws NoSuchAlgorithmException {
    	calculo.setSenha(Criptografia.criarHash(calculo.getSenha()));
        dao.salvar(calculo);
        if(dao.getSucesso()) {
        	System.out.println("Salvo com sucesso!");
        	return true;
        }
        return false;
    }
    
    public boolean alterar(Usuario calculo, String nome) throws NoSuchAlgorithmException {
    	calculo.setSenha(Criptografia.criarHash(calculo.getSenha()));
        dao.alterar(calculo, nome);
        if(dao.getSucesso()) {
        	System.out.println("Alterado com sucesso!");
        	return true;
        }
        return false;
    }
    
    public boolean excluir(String nome) {
        dao.excluir(nome);
        if(dao.getSucesso()) {
        	System.out.println("Excluído com sucesso!");
        	return true;
        }
        return false;
    }
    
    public ArrayList<Usuario> consultar() {
		UsuarioDao usuarioDao = new UsuarioDao();
        return usuarioDao.consultar();     	
	}
}
