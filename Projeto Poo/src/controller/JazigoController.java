package controller;
import java.util.ArrayList;

import controller.exceptions.ExcecaoConversaoToInteger;
import controller.verificacoes.Verificacoes;
import model.entidade.Jazigo;
import model.service.JazigoService;
import view.JazigoView;

public class JazigoController {
	private Jazigo jazigo;

	public boolean ObterDadosView(JazigoView frame, String acao) {
		try {
        	new Verificacoes().ehInteiro(frame.getTxtTamanho().getValue().toString());
        	new Verificacoes().ehInteiro(frame.getTxtQtdSepulturas().getValue().toString());
        	new Verificacoes().ehInteiro(frame.getTxtSepOcupadas().getValue().toString());
        }
        catch(ExcecaoConversaoToInteger e) {
        	System.out.println(e.getMessage());
        	System.exit(0);
        }
        
		jazigo = new Jazigo();
		jazigo.setNomeFamilia(frame.getTxtNomeFamilia().getText());
        jazigo.setLocal(frame.getTxtLocal().getText());
        jazigo.setTamanho(Integer.parseInt(frame.getTxtTamanho().getValue().toString()));
		jazigo.setQtdSepulturas(Integer.parseInt(frame.getTxtQtdSepulturas().getValue().toString()));
		jazigo.setSepOcupadas(Integer.parseInt(frame.getTxtSepOcupadas().getValue().toString()));
		if(acao.equals("ADICIONAR")) return Salva(frame);
		else return Altera(frame);
	}
	public boolean Salva(JazigoView frame) {
		JazigoService service = new JazigoService();
        return service.salvar(jazigo);
    }
	
	public boolean Altera(JazigoView frame) {
        JazigoService service = new JazigoService();
        return service.alterar(jazigo, frame.getTxtAlterar().getSelectedItem().toString());
    }
	
	public boolean Exclui(JazigoView frame) {
		JazigoService service = new JazigoService();
	    return service.excluir(jazigo, frame.getTxtExcluir().getSelectedItem().toString());
	}
	
	public ArrayList<Jazigo> Consulta() {
		JazigoService service = new JazigoService();
	    return service.consultar();
		     	
	}
}