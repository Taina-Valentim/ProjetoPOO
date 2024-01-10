package view;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

import controller.JazigoController;
import model.entidade.Jazigo;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent; 

public class JazigoView extends Tela{
	private static final long serialVersionUID = 1L;
	JComboBox<String> txtAlterar = new JComboBox<>();
	JComboBox<String> txtExcluir = new JComboBox<>();
	CardLayout cl = (CardLayout) (Menu.cards.getLayout());
	private JTextField txtNomeFamilia;
	private JTextField txtLocal;
	private JSpinner txtTamanho;
	private JSpinner txtQtdSepulturas;
	private JSpinner txtSepOcupadas;
	
	public JazigoView(String acao) {
		super();
		super.setBackground(new Color(135,206,235));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Acao(acao);
	}
	
	public void Campos() {
		txtNomeFamilia = new JTextField();
		CriarLabel("Nome da Família");
		CriarCaixaDeTexto(txtNomeFamilia);
		
		txtLocal = new JTextField();
		CriarLabel("Local");
		CriarCaixaDeTexto(txtLocal);
		
		txtTamanho = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		CriarLabel("Tamanho");
		CriarSpinner(txtTamanho);
		
		txtQtdSepulturas = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		CriarLabel("Quantidade de Sepulturas");
		CriarSpinner(txtQtdSepulturas);
		
		txtSepOcupadas = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		CriarLabel("Sepulturas Ocupadas");
		CriarSpinner(txtSepOcupadas);
	}
	
	public void Adicionar() {
		Campos();
		BtnAdicionar();
	}
	
	public void Alterar() {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JLabel nome = new JLabel("Nome da Família:");
		JazigoController jazigoController = new JazigoController();
		ArrayList<Jazigo> jazigos = jazigoController.Consulta();
		for (Jazigo jazigo : jazigos) {
		    comboBoxModel.addElement(jazigo.getNomeFamilia());
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
		JLabel nome = new JLabel("Nome da Família:");
		JazigoController jazigoController = new JazigoController();
		ArrayList<Jazigo> jazigos = jazigoController.Consulta();
		for (Jazigo jazigo : jazigos) {
		    comboBoxModel.addElement(jazigo.getNomeFamilia());
		}

		txtExcluir.setModel(comboBoxModel);
		txtExcluir.setAlignmentX(0.1f);
		txtExcluir.setPreferredSize(new Dimension(300, 30));
		txtExcluir.setMaximumSize(new Dimension(300, 30));
		this.add(nome);
		this.add(txtExcluir);
	}
	
	public void Exibir() {
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 50, 400, 600);
		scrollPane_1.setPreferredSize(new Dimension(900, 600));
		add(scrollPane_1);	
		
		String colunas[] = { "Nome da Família", "Local", "Tamanho", "Quantidade de Sepulturas", "Sepulturas Ocupadas"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		JazigoController jazigoController = new JazigoController();
		ArrayList<Jazigo> jazigos = jazigoController.Consulta();
		JTable table = new JTable(100,jazigos.size());
		
		for(int i=0; i < jazigos.size() ; i++) {
			Integer tamanho = jazigos.get(i).getTamanho();
			Integer qtdSep = jazigos.get(i).getQtdSepulturas();
			Integer sepOcup = jazigos.get(i).getSepOcupadas();
			modelo.addRow(new String[]{jazigos.get(i).getNomeFamilia(), jazigos.get(i).getLocal(), tamanho.toString(), qtdSep.toString(), sepOcup.toString()});   
		}
		table.setModel(modelo);
		scrollPane_1.setViewportView(table);
	}

	public void BtnAdicionar() {
		JButton btnSalvar = new JButton("Salvar");
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JazigoController controller = new JazigoController();
				 boolean sucesso = controller.ObterDadosView(JazigoView.this, "ADICIONAR");
				 if(sucesso) JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
				 else JOptionPane.showMessageDialog(null, "Falha ao tentar salvar", "Fracasso!", JOptionPane.ERROR_MESSAGE);
				 cl.show(Menu.cards, "INICIO");
			}
		});
		Component espacamento = Box.createVerticalGlue();
        espacamento.setMaximumSize(new Dimension(0, 15));
        this.add(espacamento);
        
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalvar.setBounds(147, 300, 89, 23);
		this.add(btnSalvar);
	}
	
	public void BtnAlterar() {
		JButton btnAlterar = new JButton("Alterar");
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = "Tem certeza que deseja alterar " + txtAlterar.getSelectedItem().toString() + "?";
				int option = JOptionPane.showConfirmDialog(null, mensagem, "Confirmação de alteração", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					JazigoController controller = new JazigoController();
					boolean sucesso = controller.ObterDadosView(JazigoView.this, "ALTERAR");
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
					JazigoController controller = new JazigoController();
					boolean sucesso = controller.Exclui(JazigoView.this);
					if(sucesso) JOptionPane.showMessageDialog(null, "Excluído com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					else JOptionPane.showMessageDialog(null, "Falha ao tentar excluir", "Fracasso!", JOptionPane.ERROR_MESSAGE);
					cl.show(Menu.cards, "INICIO");
				} 
			}
		});
        
        btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnExcluir.setBounds(147, 300, 89, 23);
		this.add(btnExcluir);
	}
	
	public JComboBox<String> getTxtAlterar() { return txtAlterar; }
	public void setTxtAlterar(JComboBox<String> txtAlterar) { this.txtAlterar = txtAlterar; }
	
	public JComboBox<String> getTxtExcluir() { return txtExcluir; }
	public void setTxtExcluir(JComboBox<String> txtExcluir) { this.txtExcluir = txtExcluir; }
	
	public JTextField getTxtNomeFamilia() { return txtNomeFamilia; }
	public void setTxtNomeFamilia(JTextField txtNomeFamilia) { this.txtNomeFamilia = txtNomeFamilia; }

	public JTextField getTxtLocal() { return txtLocal; }
	public void setTxtLocal(JTextField txtLocal) { this.txtLocal = txtLocal; }

	public JSpinner getTxtTamanho() { return txtTamanho; }
	public void setTxtTamanho(JSpinner txtTamanho) { this.txtTamanho = txtTamanho; }

	public JSpinner getTxtQtdSepulturas() { return txtQtdSepulturas; }
	public void setTxtQtdSepulturas(JSpinner txtQtdSepulturas) { this.txtQtdSepulturas = txtQtdSepulturas; }

	public JSpinner getTxtSepOcupadas() { return txtSepOcupadas; }
	public void setTxtSepOcupadas(JSpinner txtSepOcupadas) { this.txtSepOcupadas = txtSepOcupadas; }
}
