package view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.JazigoController;
import controller.ObitoController;
import model.entidade.Jazigo;
import model.entidade.Obito;

class Tela extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public void Acao(String acao) {
		if(acao.equals("ADICIONAR")) {
			Adicionar();
		}
		if(acao.equals("ALTERAR")) {
			Alterar();
		}
		if(acao.equals("EXCLUIR")) {
			Excluir();
		}
		if(acao.equals("EXIBIR")) {
			Exibir();
		}
	}
	
	public void Adicionar() { }
	public void Alterar() { }
	public void Excluir() { }
	public void Exibir() { }
	
	public void CriarLabel(String campo) {
		Component espacamento = Box.createVerticalGlue();
        espacamento.setMaximumSize(new Dimension(0, 15));
        this.add(espacamento);
        
        JLabel label = new JLabel(campo);
        label.setAlignmentX(0.1f);
        label.setPreferredSize(new Dimension(200, 30));
        label.setMaximumSize(new Dimension(200, 30));
        this.add(label);
	}
	
	public void CriarCaixaDeTexto(JTextField cxTexto) {
		cxTexto.setAlignmentX(0.1f);
		cxTexto.setPreferredSize(new Dimension(200, 30));
        cxTexto.setMaximumSize(new Dimension(200, 30));
        this.add(cxTexto);
	}
	
	public void CriarCampoSenha(JPasswordField senha) {
		senha.setAlignmentX(0.1f);
		senha.setPreferredSize(new Dimension(200, 30));
		senha.setMaximumSize(new Dimension(200, 30));
		this.add(senha);
	}
	
	public void CriarSpinner(JSpinner spinner) {
		spinner.setAlignmentX(0.1f);
		spinner.setPreferredSize(new Dimension(200, 30));
        spinner.setMaximumSize(new Dimension(40, 30));
        this.add(spinner);
	}
	
	public void CriarSpinnerData(JSpinner spinnerData) {
		spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
		spinnerData.setAlignmentX(0.1f);
		spinnerData.setPreferredSize(new Dimension(200, 30));
		spinnerData.setMaximumSize(new Dimension(200, 30));
		this.add(spinnerData);
	}
	
	public void CriarSpinnerHora(JSpinner spinnerHora) {
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(editor);
        spinnerHora.setAlignmentX(0.1f);
        spinnerHora.setPreferredSize(new Dimension(200, 30));
        spinnerHora.setMaximumSize(new Dimension(200, 30));
		this.add(spinnerHora);
	}
	
	public void CriarJComboBox(JComboBox<String> jComboBox) {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("ADMINISTRADOR");
		comboBoxModel.addElement("OPERADOR");
		jComboBox.setModel(comboBoxModel);
		jComboBox.setAlignmentX(0.1f);
		jComboBox.setPreferredSize(new Dimension(200, 30));
		jComboBox.setMaximumSize(new Dimension(200, 30));
		this.add(jComboBox);
	}
	
	public void CriarJComboBoxJazigo(JComboBox<String> jComboBox) {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JLabel nome = new JLabel("Nome da Família do Jazigo:");
		JazigoController jazigoController = new JazigoController();
		ArrayList<Jazigo> jazigos = jazigoController.Consulta();
		for (Jazigo jazigo : jazigos) {
		    comboBoxModel.addElement(jazigo.getNomeFamilia());
		}
		jComboBox.setModel(comboBoxModel);
		jComboBox.setAlignmentX(0.1f);
		jComboBox.setPreferredSize(new Dimension(200, 30));
		jComboBox.setMaximumSize(new Dimension(200, 30));
		this.add(nome);
		this.add(jComboBox);
	}
	
	public void CriarJComboBoxObito(JComboBox<String> jComboBox) {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JLabel nome = new JLabel("Nome Novo do Falecido:");
		ObitoController obitoController = new ObitoController();
		ArrayList<Obito> obitos = obitoController.Consulta();
		for (Obito obito : obitos) {
		    comboBoxModel.addElement(obito.getNome());
		}
		jComboBox.setModel(comboBoxModel);
		jComboBox.setAlignmentX(0.1f);
		jComboBox.setPreferredSize(new Dimension(200, 30));
		jComboBox.setMaximumSize(new Dimension(200, 30));
		this.add(nome);
		this.add(jComboBox);
	}
	
	public void CriarFormatoCPF(JFormattedTextField campoCPF) {
		campoCPF.setAlignmentX(0.1f);
		campoCPF.setPreferredSize(new Dimension(200, 30));
		campoCPF.setMaximumSize(new Dimension(200, 30));
		this.add(campoCPF);
	}
	
	protected static MaskFormatter FormatoCPF() {
        MaskFormatter formato = null;
        try {
        	formato = new MaskFormatter("###.###.###-##");
        	formato.setPlaceholderCharacter('_');
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return formato;
    }
}



