package view;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

import controller.ObitoController;
import model.entidade.Obito;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
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
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent; 

public class ObitoView extends Tela {
	private static final long serialVersionUID = 1L;
	JComboBox<String> txtAlterar = new JComboBox<>();
	JComboBox<String> txtExcluir = new JComboBox<>();
	CardLayout cl = (CardLayout) (Menu.cards.getLayout());
	private JTextField txtNome;
	private JSpinner txtDataObito;
	private JFormattedTextField txtCpf;
	private JTextField txtCidade;
	private JTextField txtCartorio;
	private JTextField txtLivro;
	private JTextField txtFolha;
	private JTextField txtTermo;
	private JTextField txtMedico;
	private JTextField txtCrm;
	private JComboBox<String> txtNomeFamiliaJazigo;

	public ObitoView(String acao) {
		super();
		super.setBackground(new Color(135,206,235));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Acao(acao);
	}
	
	public void Campos() {
		txtNome = new JTextField();
		CriarLabel("Nome:");
		CriarCaixaDeTexto(txtNome);

		txtDataObito = new JSpinner(new SpinnerDateModel());
		CriarLabel("Data do Óbito:");
		CriarSpinnerData(txtDataObito);

		txtCpf = new JFormattedTextField(super.FormatoCPF());
		CriarLabel("CPF:");
		CriarFormatoCPF(txtCpf);
		
		txtCidade = new JTextField();
		CriarLabel("Cidade:");
		CriarCaixaDeTexto(txtCidade);
		
		txtCartorio = new JTextField();
		CriarLabel("Cartório:");
		CriarCaixaDeTexto(txtCartorio);
		
		txtLivro = new JTextField();
		CriarLabel("Livro:");
		CriarCaixaDeTexto(txtLivro);
		
		txtFolha = new JTextField();
		CriarLabel("Folha:");
		CriarCaixaDeTexto(txtFolha);
		
		txtTermo = new JTextField();
		CriarLabel("Termo:");
		CriarCaixaDeTexto(txtTermo);
		
		txtMedico = new JTextField();
		CriarLabel("Médico:");
		CriarCaixaDeTexto(txtMedico);
		
		txtCrm = new JTextField();
		CriarLabel("CRM:");
		CriarCaixaDeTexto(txtCrm);
		
		txtNomeFamiliaJazigo = new JComboBox<String>();
		CriarJComboBoxJazigo(txtNomeFamiliaJazigo);
	}
	
	public void Adicionar() {
		Campos();
		BtnAdicionar();
	}
	
	public void Alterar()	{
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JLabel nome = new JLabel("Nome do Falecido:");
		ObitoController obitoController = new ObitoController();
		ArrayList<Obito> obitos = obitoController.Consulta();
		for (Obito obito : obitos) {
		    comboBoxModel.addElement(obito.getNome());
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
		ObitoController obitoController = new ObitoController();
		ArrayList<Obito> obitos = obitoController.Consulta();
		for (Obito obito : obitos) {
		    comboBoxModel.addElement(obito.getNome());
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
		
		String colunas[] = { "Nome", "Data do Óbito", "CPF", "Cidade", "Cartório", "Livro", "Folha", "Termo", "Médico", "CRM", "Nome da Família do Jazigo"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		ObitoController obitoController = new ObitoController();
		ArrayList<Obito> obitos = obitoController.Consulta();
		JTable table = new JTable(100,obitos.size());
		
		for(int i=0; i < obitos.size() ; i++) {
			Integer livro = obitos.get(i).getLivro();
			Integer folha = obitos.get(i).getFolha();
			Integer termo = obitos.get(i).getTermo();
			Integer crm = obitos.get(i).getCrm();
			String nomeFamilia = obitoController.ObterNomeFamiliaJazigo(obitos.get(i).getNome());
			modelo.addRow(new String[]{obitos.get(i).getNome(), obitos.get(i).getDataObito().toString(), obitos.get(i).getCpf(), obitos.get(i).getCidade(), obitos.get(i).getCartorio(), livro.toString(), folha.toString(), termo.toString(), obitos.get(i).getMedico(), crm.toString(), nomeFamilia });   
		}
		table.setModel(modelo);
		scrollPane_1.setViewportView(table);
	}
	
	public void BtnAdicionar() {
		JButton btnSalvar = new JButton("Salvar");
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObitoController controller = new ObitoController();
		        boolean sucesso = controller.ObterDadosView(ObitoView.this, "ADICIONAR");
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
					ObitoController controller = new ObitoController();
			        boolean sucesso = controller.ObterDadosView(ObitoView.this, "ALTERAR");
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
					ObitoController controller = new ObitoController();
					boolean sucesso = controller.Exclui(ObitoView.this);
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
	
	public JTextField getTxtNome() { return txtNome; }
	public void setTxtNome(JTextField txtNome) { this.txtNome = txtNome; }

	public JSpinner getTxtDataObito() { return txtDataObito; }
	public void setTxtDataObito(JSpinner txtDataObito) { this.txtDataObito = txtDataObito; }

	public JFormattedTextField getTxtCpf() { return txtCpf; }
	public void setTxtCpf(JFormattedTextField txtCpf) { this.txtCpf = txtCpf; }

	public JTextField getTxtCidade() { return txtCidade; }
	public void setTxtCidade(JTextField txtCidade) { this.txtCidade = txtCidade; }

	public JTextField getTxtCartorio() { return txtCartorio; }
	public void setTxtCartorio(JTextField txtCartorio) { this.txtCartorio = txtCartorio; }

	public JTextField getTxtLivro() { return txtLivro; }
	public void setTxtLivro(JTextField txtLivro) { this.txtLivro = txtLivro; }

	public JTextField getTxtFolha() { return txtFolha; }
	public void setTxtFolha(JTextField txtFolha) { this.txtFolha = txtFolha; }

	public JTextField getTxtTermo() { return txtTermo; }
	public void setTxtTermo(JTextField txtTermo) { this.txtTermo = txtTermo; }

	public JTextField getTxtMedico() { return txtMedico; }
	public void setTxtMedico(JTextField txtMedico) { this.txtMedico = txtMedico; }

	public JTextField getTxtCrm() { return txtCrm; }
	public void setTxtCrm(JTextField txtCrm) { this.txtCrm = txtCrm; }
	
	public JComboBox<String> getTxtNomeFamiliaJazigo() { return txtNomeFamiliaJazigo; }
	public void setTxtNomeFamiliaJazigo(JComboBox<String> txtNomeFamiliaJazigo) { this.txtNomeFamiliaJazigo = txtNomeFamiliaJazigo; }

}
