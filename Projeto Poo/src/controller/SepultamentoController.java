package controller;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import controller.exceptions.ExcecaoDataHora;
import controller.verificacoes.Verificacoes;
import model.entidade.Sepultamento;
import model.service.SepultamentoService;
import view.SepultamentoView;

public class SepultamentoController {
	private Sepultamento sepultamento;

	public boolean ObterDadosView(SepultamentoView frame, String acao) {
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		LocalTime time = LocalTime.parse(frame.getTxtHorarioEnterro().getValue().toString(), formatoHora);
		
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(frame.getTxtDataSepultamento().getValue().toString(), formatoData);
		try {
			new Verificacoes().ehDataValida(date);
			new Verificacoes().ehHoraValida(time);
		}
		catch(ExcecaoDataHora e) {
        	System.out.println(e.getMessage());
        	System.exit(0);
        }

		
		sepultamento = new Sepultamento();
		sepultamento.setDataSepultamento(Date.valueOf(date));
		sepultamento.setHorarioEnterro(Time.valueOf(time));
		sepultamento.setFuneraria(frame.getTxtFuneraria().getText());
		sepultamento.setResponsavel(frame.getTxtResponsavel().getText());
		sepultamento.setLocal(frame.getTxtLocal().getText());
		String nomeFalecido = frame.getTxtNomeFalecido().getSelectedItem().toString();
		if(acao.equals("ADICIONAR")) return Salva(frame, nomeFalecido);
		else return Altera(frame, nomeFalecido);
		
    }
	
	public boolean Salva(SepultamentoView frame, String nomeFalecido) {
		SepultamentoService service = new SepultamentoService();
        return service.salvar(sepultamento, nomeFalecido);
    }
	
	public boolean Altera(SepultamentoView frame, String nomeFalecido) {
		SepultamentoService service = new SepultamentoService();
        return service.alterar(sepultamento, frame.getTxtAlterar().getSelectedItem().toString(), nomeFalecido);
    }
	
	public boolean Exclui(SepultamentoView frame) {
		SepultamentoService service = new SepultamentoService();
	    return service.excluir(frame.getTxtExcluir().getSelectedItem().toString());
	}
	
	public ArrayList<Sepultamento> Consulta() {
		SepultamentoService service = new SepultamentoService();
	    return service.consultar();
	}
	
	public String ObterNomeFalecido(int id) {
		SepultamentoService service = new SepultamentoService();
		return service.buscarFalecido(id);
	}
}