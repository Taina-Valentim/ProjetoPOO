package model.service;
import java.util.ArrayList;

import model.dao.JazigoDao;
import model.entidade.Jazigo;

public class JazigoService {
    private JazigoDao dao;

    public JazigoService() {
        this.dao = new JazigoDao();
    }
                                                                                           
    public boolean salvar(Jazigo calculo) {
        dao.salvar(calculo);
        if(dao.getSucesso()) {
        	System.out.println("Salvo com sucesso!");
        	return true;
        }
        return false;
    }
    
    public boolean alterar(Jazigo calculo, String nome) {
        dao.alterar(calculo, nome);
        if(dao.getSucesso()) {
        	System.out.println("Alterado com sucesso!");
        	return true;
        }
        return false;
    }
    
    public boolean excluir(Jazigo calculo, String nome) {
        dao.excluir(calculo, nome);
        if(dao.getSucesso()) {
        	System.out.println("Excluído com sucesso!");
        	return true;
        }
        return false;
    }
    
    public ArrayList<Jazigo> consultar() {
    	JazigoDao jazigoDao = new JazigoDao();
        return jazigoDao.consultar();
    }
}