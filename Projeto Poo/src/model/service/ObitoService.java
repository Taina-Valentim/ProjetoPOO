package model.service;
import java.util.ArrayList;

import model.dao.ObitoDao;
import model.entidade.Obito;

public class ObitoService {
    private ObitoDao dao;

    public ObitoService() {
        this.dao = new ObitoDao();
    }
                                                                                           
    public boolean salvar(Obito calculo, String nomeFamilia) {
        dao.salvar(calculo, nomeFamilia);
        if(dao.getSucesso()) {
        	System.out.println("Salvo com sucesso!");
        	return true;
        }
        return false;
    }
    
    public boolean alterar(Obito calculo, String nome, String nomeFamilia) {
        dao.alterar(calculo, nome, nomeFamilia);
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
    
    public ArrayList<Obito> exibir() {
		ObitoDao obitoDao = new ObitoDao();
        return obitoDao.consultar();     	
	}
    
    public String buscarFamilia(String nome) {
    	ObitoDao obitoDao = new ObitoDao();
    	return obitoDao.buscaFamilia(nome);
    }
}
