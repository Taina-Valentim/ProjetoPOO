package model.service;

import java.security.NoSuchAlgorithmException;

import controller.verificacoes.Criptografia;
import model.dao.UsuarioDao;

public class LoginService {
	private UsuarioDao dao;
	
	public LoginService() {
        this.dao = new UsuarioDao();
    }
	public boolean logar(String login, String senha) throws NoSuchAlgorithmException {
		String senhaCriptografada = Criptografia.criarHash(senha);
		dao.logar(login, senhaCriptografada);
		if(dao.getSucesso()) {
        	System.out.println("Logado com sucesso!");
        	return true;
        }
        return false;
	}
}
