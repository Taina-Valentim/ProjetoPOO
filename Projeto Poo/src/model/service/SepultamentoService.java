package model.service;
import java.util.ArrayList;

import model.dao.SepultamentoDao;
import model.entidade.Sepultamento;

public class SepultamentoService {
    private SepultamentoDao dao;

    public SepultamentoService() {
        this.dao = new SepultamentoDao();
    }
                                                                                           
    public boolean salvar(Sepultamento calculo, String nomeFalecido) {
        dao.salvar(calculo, nomeFalecido);
        if(dao.getSucesso()) {
        	System.out.println("Salvo com sucesso!");
        	return true;
        }
        return false;
    }
    
    public boolean alterar(Sepultamento calculo, String nome, String nomeFalecido) {
    	dao.alterar(calculo, nome, nomeFalecido);
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
    
    public ArrayList<Sepultamento> consultar() {
		SepultamentoDao sepultamentoDao = new SepultamentoDao();
        return sepultamentoDao.consulta();     	
	}
    
    public String buscarFalecido(int id) {
    	SepultamentoDao sepultamentoDao = new SepultamentoDao();
    	return sepultamentoDao.buscaFalecido(id);
    }
}
