package view;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.UsuarioController;
import model.entidade.Usuario;

import javax.swing.JTextField;
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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.awt.event.ActionEvent; 

public class UsuarioView extends Tela {
	private static final long serialVersionUID = 1L;
	JComboBox<String> txtAlterar = new JComboBox<>();
	JComboBox<String> txtExcluir = new JComboBox<>();
	CardLayout cl = (CardLayout) (Menu.cards.getLayout());
	private JTextField txtNome;
	private JTextField txtSenha;
	private JComboBox<String> txtNivel;
	
	public UsuarioView(String acao) {
		super();
		super.setBackground(new Color(135,206,235));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Acao(acao);
	}
	
	public void Campos() {
		txtNome = new JTextField();
		CriarLabel("Nome:");
		CriarCaixaDeTexto(txtNome);
		
		txtSenha = new JTextField();
		CriarLabel("Senha:");
		CriarCaixaDeTexto(txtSenha);
		
		txtNivel = new JComboBox<String>();
		CriarLabel("Nível de Operação:");
		CriarJComboBox(txtNivel);
	}
	
	public void Adicionar() {
		Campos();
		BtnAdicionar();
	}
	
	public void Alterar() {
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		JLabel nome = new JLabel("Nome:");
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<Usuario> usuarios = usuarioController.Consulta();
		for (Usuario usuario : usuarios) {
		    comboBoxModel.addElement(usuario.getNome());
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
		JLabel nome = new JLabel("Nome:");
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<Usuario> usuarios = usuarioController.Consulta();
		for (Usuario usuario : usuarios) {
		    comboBoxModel.addElement(usuario.getNome());
		}
		txtExcluir.setModel(comboBoxModel);
		txtExcluir.setPreferredSize(new Dimension(300, 30));
		txtExcluir.setMaximumSize(new Dimension(300, 30));
		txtExcluir.setAlignmentX(0.1f);
		this.add(nome);
		this.add(txtExcluir);
		
		BtnExcluir();
	}
	
	public void Exibir() {
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 50, 400, 600);
		scrollPane_1.setPreferredSize(new Dimension(900, 600));
		add(scrollPane_1);	

		String colunas[] = { "Nome", "Senha", "Nível"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<Usuario> usuarios = usuarioController.Consulta();
		JTable table = new JTable(100,usuarios.size());
		
		for(int i=0; i < usuarios.size() ; i++) {
			modelo.addRow(new String[]{usuarios.get(i).getNome(), usuarios.get(i).getSenha(), usuarios.get(i).getNivel()});   
		}
		table.setModel(modelo);
		scrollPane_1.setViewportView(table);
	}
	
	public void BtnAdicionar() {
		JButton btnSalvar = new JButton("Salvar");
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				boolean sucesso = false;
				try { sucesso = controller.ObterDadosView(UsuarioView.this, "ADICIONAR"); }
				catch (NoSuchAlgorithmException e1) { e1.printStackTrace(); }
				
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
					UsuarioController controller = new UsuarioController();
					boolean sucesso = false;
					try { sucesso = controller.ObterDadosView(UsuarioView.this, "ALTERAR"); }
					catch (NoSuchAlgorithmException e1) { e1.printStackTrace(); }
					
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
					UsuarioController controller = new UsuarioController();
					boolean sucesso = controller.Exclui(UsuarioView.this);
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

	public JTextField getTxtSenha() { return txtSenha; }
	public void setTxtSenha(JTextField txtSenha) { this.txtSenha = txtSenha; }

	public JComboBox<String> getTxtNivel() { return txtNivel; }
	public void setTxtNivel(JComboBox<String> txtNivel) { this.txtNivel = txtNivel; }

	

}
