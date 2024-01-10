package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginController;

public class Login extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private boolean logado;
	private JPanel panel;
	
	public Login() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(135,206,235));

		txtLogin = new JTextField();
		CriarLabel("Login:");
		CriarCaixaDeTexto(txtLogin);
		
		txtSenha = new JPasswordField();
		CriarLabel("Senha:");
		CriarCampoSenha(txtSenha);
		
		BtnLogar();
		this.add(panel);
	}
	
	public void CriarLabel(String campo) {
		Component espacamento = Box.createVerticalGlue();
        espacamento.setMaximumSize(new Dimension(0, 15));
        panel.add(espacamento);
        
        JLabel label = new JLabel(campo);
        label.setAlignmentX(0.1f);
        label.setPreferredSize(new Dimension(200, 30));
        label.setMaximumSize(new Dimension(200, 30));
        panel.add(label);
	}
	
	public void CriarCaixaDeTexto(JTextField cxTexto) {
		cxTexto.setAlignmentX(0.1f);
        cxTexto.setPreferredSize(new Dimension(200, 30));
        cxTexto.setMaximumSize(new Dimension(200, 30));
        cxTexto.setText("tata");
        panel.add(cxTexto);
	}
	
	public void CriarCampoSenha(JPasswordField senha) {
		senha.setAlignmentX(0.1f);
		senha.setPreferredSize(new Dimension(200, 30));
		senha.setMaximumSize(new Dimension(200, 30));
		senha.setText("Senha#123");
		panel.add(senha);
	}
	
	public void BtnLogar() {
		JButton btnLogar = new JButton("Logar");
		
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController controller = new LoginController();
				try {
					logado = controller.Logar(Login.this);
				} catch (NoSuchAlgorithmException e1) { e1.printStackTrace(); }
				 if(logado) {
					 Iniciar();
				 }
				 else JOptionPane.showMessageDialog(null, "Falha ao tentar logar", "Fracasso!", JOptionPane.ERROR_MESSAGE);
			}
		});
		Component espacamento = Box.createVerticalGlue();
        espacamento.setMaximumSize(new Dimension(0, 15));
        panel.add(espacamento);
        
        btnLogar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLogar.setBounds(147, 300, 89, 23);
        panel.add(btnLogar);
	}
	
	public void Iniciar() {
		JOptionPane.showMessageDialog(null, "Login bem sucedido!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		Login.this.dispose();
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setTitle("Login");
		menu.setBounds(70, 30, 1200, 700);
		menu.setVisible(true);
	}
	
	public JTextField getTxtLogin() { return txtLogin; }
	public void seTxtLogin(JTextField txtLogin) { this.txtLogin = txtLogin; }

	public JPasswordField getTxtSenha() { return txtSenha; }
	public void setTxtSenha(JPasswordField txtSenha) { this.txtSenha = txtSenha;}
	
	public boolean getLogado() { return logado; }
	public void setLogado(boolean logado) { this.logado = logado; }
}
