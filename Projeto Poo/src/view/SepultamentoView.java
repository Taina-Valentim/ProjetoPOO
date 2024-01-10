package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import controller.SepultamentoController;
import model.entidade.Sepultamento; 

public class SepultamentoView extends Tela {
	private static final long serialVersionUID = 1L;
	JComboBox<String> txtAlterar = new JComboBox<>();
	JComboBox<String> txtExcluir = new JComboBox<>();
	CardLayout cl = (CardLayout) (Menu.cards.getLayout());
	private JComboBox<String> txtNomeFalecido;
	private JSpinner txtDataSepultamento;
	private JSpinner txtHorarioEnterro;
	private JTextField txtFuneraria;
	private JTextField txtResponsavel;
	private JTextField txtLocal;
	
	
	public SepultamentoView(String acao) {
		super();
		super.setBackground(new Color(135,206,235));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Acao(acao);
	}

	public void Campos() {
		txtNomeFalecido = new JComboBox<String>();
		CriarJComboBoxObito(txtNomeFalecido);
		
        txtDataSepultamento = new JSpinner(new SpinnerDateModel());
		CriarLabel("Data do Sepultamento:");
		CriarSpinnerData(txtDataSepultamento);
		
		txtHorarioEnterro = new JSpinner(new SpinnerDateModel());
		CriarLabel("Horário do Enterro:");
		CriarSpinnerHora(txtHorarioEnterro);
		
		txtFuneraria = new JTextField();
		CriarLabel("Funerária:");
		CriarCaixaDeTexto(txtFuneraria);
		
		txtResponsavel = new JTextField();
		CriarLabel("Responsável:");
		CriarCaixaDeTexto(txtResponsavel);
		
		txtLocal = new JTextField();
		CriarLabel("Local:");
		CriarCaixaDeTexto(txtLocal);
	}
	
	public void Adicionar() {
		Campos();
		BtnAdicionar();
	}
	public void Alterar() {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JLabel nome = new JLabel("Falecido que deseja alterar:");
		SepultamentoController sepultamentoController = new SepultamentoController();
		ArrayList<Sepultamento> sepultamentos = sepultamentoController.Consulta();
		for (Sepultamento sepultamento : sepultamentos) {
			String nomeFalecido = sepultamentoController.ObterNomeFalecido(sepultamento.getId());
		    comboBoxModel.addElement(nomeFalecido);
		}
		txtAlterar.setModel(comboBoxModel);
		txtAlterar.setAlignmentX(0.1f);
		txtAlterar.setPreferredSize(new Dimension(200, 30));
		txtAlterar.setMaximumSize(new Dimension(200, 30));
		this.add(nome);
		this.add(txtAlterar);
		Campos();
		BtnAlterar();
	}
	
	public void Excluir() {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JLabel nome = new JLabel("Nome do Falecido:");
		SepultamentoController sepultamentoController = new SepultamentoController();
		ArrayList<Sepultamento> sepultamentos = sepultamentoController.Consulta();
		for (Sepultamento sepultamento : sepultamentos) {
			String nomeFalecido = sepultamentoController.ObterNomeFalecido(sepultamento.getId());
		    comboBoxModel.addElement(nomeFalecido);
		}
		txtExcluir.setModel(comboBoxModel);
		txtExcluir.setAlignmentX(0.1f);
		txtExcluir.setPreferredSize(new Dimension(300, 30));
		txtExcluir.setMaximumSize(new Dimension(300, 30));
		this.add(nome);
		this.add(txtExcluir);
		
		BtnExcluir();
	}
	
	public void Exibir() {
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 50, 400, 600);
		scrollPane_1.setPreferredSize(new Dimension(900, 600));
		add(scrollPane_1);
		String colunas[] = { "Nome", "Data do Sepultamento", "Horário do Enterro", "Funerária", "Responsável", "Local"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		SepultamentoController sepultamentoController = new SepultamentoController();
		ArrayList<Sepultamento> sepultamentos = sepultamentoController.Consulta();
		JTable table = new JTable(100,sepultamentos.size());
		
		for(Sepultamento sepultamento : sepultamentos) {
			String nomeFalecido = sepultamentoController.ObterNomeFalecido(sepultamento.getId());
			modelo.addRow(new String[]{nomeFalecido, sepultamento.getDataSepultamento().toString(), sepultamento.getHorarioEnterro().toString(), sepultamento.getFuneraria(), sepultamento.getResponsavel(), sepultamento.getLocal() });   
		}
		table.setModel(modelo);
		scrollPane_1.setViewportView(table);
	}
	
	public void BtnAdicionar() {
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SepultamentoController controller = new SepultamentoController();
		        boolean sucesso = controller.ObterDadosView(SepultamentoView.this, "ADICIONAR");
		        if(sucesso) JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
				else JOptionPane.showMessageDialog(null, "Falha ao tentar salvar", "Fracasso!", JOptionPane.ERROR_MESSAGE);
				cl.show(Menu.cards, "INICIO");
			}
		});
		Component espacamento = Box.createVerticalGlue();
        espacamento.setMaximumSize(new Dimension(0, 15));
        this.add(espacamento);
        
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalvar.setBounds(200, 300, 89, 23);
		this.add(btnSalvar);
	}
	
	public void BtnAlterar() {
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = "Tem certeza que deseja alterar " + txtAlterar.getSelectedItem().toString() + "?";
				int option = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação de alteração", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					SepultamentoController controller = new SepultamentoController();
			        boolean sucesso = controller.ObterDadosView(SepultamentoView.this, "ALTERAR");
					if(sucesso) JOptionPane.showMessageDialog(null, "Alterado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					else JOptionPane.showMessageDialog(null, "Falha ao tentar alterar", "Fracasso!", JOptionPane.ERROR_MESSAGE);
					cl.show(Menu.cards, "INICIO");
				}
			}
		});
		Component espacamento = Box.createVerticalGlue();
        espacamento.setMaximumSize(new Dimension(0, 15));
        this.add(espacamento);
        
        btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAlterar.setBounds(147, 300, 89, 23);
		this.add(btnAlterar);
	}

	public void BtnExcluir() {
		JButton btnExcluir = new JButton("Excluir");
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = "Tem certeza que deseja excluir " + txtExcluir.getSelectedItem().toString() + "?";
				int option = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação de exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					SepultamentoController controller = new SepultamentoController();
					boolean sucesso = controller.Exclui(SepultamentoView.this);
					if(sucesso) JOptionPane.showMessageDialog(null, "Excluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					else JOptionPane.showMessageDialog(null, "Falha ao tentar excluir", "Fracasso!", JOptionPane.ERROR_MESSAGE);
					cl.show(Menu.cards, "INICIO");
				} 
			}
		});
		Component espacamento = Box.createVerticalGlue();
        espacamento.setMaximumSize(new Dimension(0, 15));
        this.add(espacamento);
        
        btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnExcluir.setBounds(147, 300, 89, 23);
		this.add(btnExcluir);
	}
	
	public JComboBox<String> getTxtAlterar() { return txtAlterar; }
	public void setTxtAlterar(JComboBox<String> txtAlterar) { this.txtAlterar = txtAlterar; }
	
	public JComboBox<String> getTxtExcluir() { return txtExcluir; }
	public void setTxtExcluir(JComboBox<String> txtExcluir) { this.txtExcluir = txtExcluir; }

	public JSpinner getTxtDataSepultamento() { return txtDataSepultamento; }
	public void setTxtDataSepultamento(JSpinner txtDataSepultamento) { this.txtDataSepultamento = txtDataSepultamento; }

	public JSpinner getTxtHorarioEnterro() { return txtHorarioEnterro; }
	public void setTxtHorarioEnterro(JSpinner txtHorarioEnterro) { this.txtHorarioEnterro = txtHorarioEnterro; }
	
	public JTextField getTxtFuneraria() { return txtFuneraria; }
	public void setTxtFuneraria(JTextField txtFuneraria) { this.txtFuneraria = txtFuneraria; }

	public JTextField getTxtResponsavel() { return txtResponsavel; }
	public void setTxtResponsavel(JTextField txtResponsavel) { this.txtResponsavel = txtResponsavel; }

	public JTextField getTxtLocal() { return txtLocal; }
	public void setTxtLocal(JTextField txtLocal) { this.txtLocal = txtLocal; }

	public JComboBox<String> getTxtNomeFalecido() {
		return txtNomeFalecido;
	}

	public void setTxtNomeFalecido(JComboBox<String> txtNomeFalecido) {
		this.txtNomeFalecido = txtNomeFalecido;
	}

}
