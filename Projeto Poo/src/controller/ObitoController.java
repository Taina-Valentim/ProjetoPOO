package controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import controller.exceptions.ExcecaoConversaoToInteger;
import controller.exceptions.ExcecaoDataHora;
import controller.verificacoes.Verificacoes;
import model.entidade.Obito;
import model.service.ObitoService;
import view.ObitoView;

public class ObitoController {
	private Obito obito;

	public boolean ObterDadosView(ObitoView frame, String acao) {
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(frame.getTxtDataObito().getValue().toString(), formatacao);
		try {
			new Verificacoes().ehDataValida(date);
		}
		catch(ExcecaoDataHora e) {
        	System.out.println(e.getMessage());
        	System.exit(0);
        }
		try {
			new Verificacoes().ehInteiro(frame.getTxtLivro().getText());
			new Verificacoes().ehInteiro(frame.getTxtFolha().getText());
			new Verificacoes().ehInteiro(frame.getTxtTermo().getText());
			new Verificacoes().ehInteiro(frame.getTxtCrm().getText());
		}
		catch(ExcecaoConversaoToInteger e) {
        	System.out.println(e.getMessage());
        	System.exit(0);
        }
		
		obito = new Obito();
		obito.setNome(frame.getTxtNome().getText());
		obito.setDataObito(date);
		obito.setCpf(frame.getTxtCpf().getText());
		obito.setCidade(frame.getTxtCidade().getText());
		obito.setCartorio(frame.getTxtCartorio().getText());
		obito.setLivro(Integer.parseInt(frame.getTxtLivro().getText()));
		obito.setFolha(Integer.parseInt(frame.getTxtFolha().getText()));
		obito.setTermo(Integer.parseInt(frame.getTxtTermo().getText()));
		obito.setMedico(frame.getTxtMedico().getText());
		obito.setCrm(Integer.parseInt(frame.getTxtCrm().getText()));
		String nomeFamilia = frame.getTxtNomeFamiliaJazigo().getSelectedItem().toString();
		if(acao.equals("ADICIONAR")) return Salva(frame, nomeFamilia);
		else return Altera(frame, nomeFamilia);
    }
	
	public boolean Salva(ObitoView frame, String nomeFamilia) {
		ObitoService service = new ObitoService();
        return service.salvar(obito, nomeFamilia);
    }
	
	public boolean Altera(ObitoView frame, String nomeFamilia) {
		ObitoService service = new ObitoService();
        return service.alterar(obito, frame.getTxtAlterar().getSelectedItem().toString(), nomeFamilia);
    }
	
	public boolean Exclui(ObitoView frame) {
		ObitoService service = new ObitoService();
	    return service.excluir(frame.getTxtExcluir().getSelectedItem().toString());
	}
	
	public ArrayList<Obito> Consulta() {
		ObitoService service = new ObitoService();
	    return service.exibir();
	}
	
	public String ObterNomeFamiliaJazigo(String nome) {
		ObitoService service = new ObitoService();
		return service.buscarFamilia(nome);
	}
}