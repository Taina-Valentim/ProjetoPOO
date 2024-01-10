package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	static JPanel cards;
	
	public Menu() {
		Font fonte = new Font("Consolas", Font.PLAIN, 20);

		JMenuBar barraMenu = new JMenuBar();
		barraMenu.setPreferredSize(new Dimension(WIDTH, 40));
		barraMenu.setBackground(new Color(0,191,255));
		
		JMenu adicionar = new JMenu("ADICIONAR");
		adicionar.setFont(fonte);
		adicionar.setForeground(new Color(0,0,139));
		
		JMenu alterar = new JMenu("ALTERAÇÃO");
		alterar.setFont(fonte);
		alterar.setForeground(new Color(0,0,139));
		
		JMenu excluir= new JMenu("EXCLUSÃO");
		excluir.setFont(fonte);
		excluir.setForeground(new Color(0,0,139));
		
		JMenu exibir = new JMenu("EXIBIÇÃO");
		exibir.setFont(fonte);
		exibir.setForeground(new Color(0,0,139));
		
		
		/*****************************************************************************/
		
		
		JMenuItem adicionarJazigo = new JMenuItem("Jazigo");
		adicionarJazigo.setFont(fonte);
		adicionarJazigo.setActionCommand("ADICIONARJAZIGO");
		adicionarJazigo.addActionListener(new ChangeCardlayoutListener());
		adicionarJazigo.setBackground(new Color(173,216,230));
		
		JMenuItem adicionarObito = new JMenuItem("Óbito");
		adicionarObito.setFont(fonte);
		adicionarObito.setActionCommand("ADICIONARÓBITO");
		adicionarObito.addActionListener(new ChangeCardlayoutListener());
		adicionarObito.setBackground(new Color(173,216,230));

		JMenuItem adicionarSepultamento = new JMenuItem("Sepultamento");
		adicionarSepultamento.setFont(fonte);
		adicionarSepultamento.setActionCommand("ADICIONARSEPULTAMENTO");
		adicionarSepultamento.addActionListener(new ChangeCardlayoutListener());
		adicionarSepultamento.setBackground(new Color(173,216,230));

		JMenuItem adicionarUsuario = new JMenuItem("Usuário");
		adicionarUsuario.setFont(fonte);
		adicionarUsuario.setActionCommand("ADICIONARUSUÁRIO");
		adicionarUsuario.addActionListener(new ChangeCardlayoutListener());
		adicionarUsuario.setBackground(new Color(173,216,230));
		
		
		/*****************************************************************************/
		
		
		JMenuItem alterarJazigo = new JMenuItem("Jazigo");
		alterarJazigo.setFont(fonte);
		alterarJazigo.setActionCommand("ALTERARJAZIGO");
		alterarJazigo.addActionListener(new ChangeCardlayoutListener());
		alterarJazigo.setBackground(new Color(173,216,230));
		
		JMenuItem alterarObito = new JMenuItem("Óbito");
		alterarObito.setFont(fonte);
		alterarObito.setActionCommand("ALTERARÓBITO");
		alterarObito.addActionListener(new ChangeCardlayoutListener());
		alterarObito.setBackground(new Color(173,216,230));

		JMenuItem alterarSepultamento = new JMenuItem("Sepultamento");
		alterarSepultamento.setFont(fonte);
		alterarSepultamento.setActionCommand("ALTERARSEPULTAMENTO");
		alterarSepultamento.addActionListener(new ChangeCardlayoutListener());
		alterarSepultamento.setBackground(new Color(173,216,230));

		JMenuItem alterarUsuario = new JMenuItem("Usuário");
		alterarUsuario.setFont(fonte);
		alterarUsuario.setActionCommand("ALTERARUSUÁRIO");
		alterarUsuario.addActionListener(new ChangeCardlayoutListener());
		alterarUsuario.setBackground(new Color(173,216,230));

		
		/*****************************************************************************/
		
		
		JMenuItem excluirJazigo = new JMenuItem("Jazigo");
		excluirJazigo.setFont(fonte);
		excluirJazigo.setActionCommand("EXCLUIRJAZIGO");
		excluirJazigo.addActionListener(new ChangeCardlayoutListener());
		excluirJazigo.setBackground(new Color(173,216,230));
		
		JMenuItem excluirObito = new JMenuItem("Óbito");
		excluirObito.setFont(fonte);
		excluirObito.setActionCommand("EXCLUIRÓBITO");
		excluirObito.addActionListener(new ChangeCardlayoutListener());
		excluirObito.setBackground(new Color(173,216,230));

		JMenuItem excluirSepultamento = new JMenuItem("Sepultamento");
		excluirSepultamento.setFont(fonte);
		excluirSepultamento.setActionCommand("EXCLUIRSEPULTAMENTO");
		excluirSepultamento.addActionListener(new ChangeCardlayoutListener());
		excluirSepultamento.setBackground(new Color(173,216,230));

		JMenuItem excluirUsuario = new JMenuItem("Usuário");
		excluirUsuario.setFont(fonte);
		excluirUsuario.setActionCommand("EXCLUIRUSUÁRIO");
		excluirUsuario.addActionListener(new ChangeCardlayoutListener());
		excluirUsuario.setBackground(new Color(173,216,230));

		
		/*****************************************************************************/
		
		
		JMenuItem exibirJazigo = new JMenuItem("Jazigo");
		exibirJazigo.setFont(fonte);
		exibirJazigo.setActionCommand("EXIBIRJAZIGO");
		exibirJazigo.addActionListener(new ChangeCardlayoutListener());
		exibirJazigo.setBackground(new Color(173,216,230));
		
		JMenuItem exibirObito = new JMenuItem("Óbito");
		exibirObito.setFont(fonte);
		exibirObito.setActionCommand("EXIBIRÓBITO");
		exibirObito.addActionListener(new ChangeCardlayoutListener());
		exibirObito.setBackground(new Color(173,216,230));

		JMenuItem exibirSepultamento = new JMenuItem("Sepultamento");
		exibirSepultamento.setFont(fonte);
		exibirSepultamento.setActionCommand("EXIBIRSEPULTAMENTO");
		exibirSepultamento.addActionListener(new ChangeCardlayoutListener());
		exibirSepultamento.setBackground(new Color(173,216,230));

		JMenuItem exibirUsuario = new JMenuItem("Usuário");
		exibirUsuario.setFont(fonte);
		exibirUsuario.setActionCommand("EXIBIRUSUÁRIO");
		exibirUsuario.addActionListener(new ChangeCardlayoutListener());
		exibirUsuario.setBackground(new Color(173,216,230));
		
		
		/*****************************************************************************/
		
		
		adicionar.add(adicionarJazigo);
		adicionar.add(adicionarObito);
		adicionar.add(adicionarSepultamento);
		adicionar.add(adicionarUsuario);
		
		alterar.add(alterarJazigo);
		alterar.add(alterarObito);
		alterar.add(alterarSepultamento);
		alterar.add(alterarUsuario);
		
		excluir.add(excluirJazigo);
		excluir.add(excluirObito);
		excluir.add(excluirSepultamento);
		excluir.add(excluirUsuario);
		
		exibir.add(exibirJazigo);
		exibir.add(exibirObito);
		exibir.add(exibirSepultamento);
		exibir.add(exibirUsuario);

		barraMenu.add(adicionar);
		barraMenu.add(alterar);
		barraMenu.add(excluir);
		barraMenu.add(exibir);
		
		setJMenuBar(barraMenu);
		Component[] components = adicionar.getMenuComponents();
        for (Component component : components) {
            component.setBackground(new Color(173,216,230));
        }
		/*****************************************************************************/
		
		
		cards = new JPanel(new CardLayout());
		cards.add(new Inicio(), "INICIO");
		
		cards.add(new JazigoView("ADICIONAR"), "ADICIONARJAZIGO");
		cards.add(new JazigoView("ALTERAR"), "ALTERARJAZIGO");
		cards.add(new JazigoView("EXCLUIR"), "EXCLUIRJAZIGO");
		cards.add(new JazigoView("EXIBIR"), "EXIBIRJAZIGO");
		
		cards.add(new ObitoView("ADICIONAR"), "ADICIONARÓBITO");
		cards.add(new ObitoView("ALTERAR"), "ALTERARÓBITO");
		cards.add(new ObitoView("EXCLUIR"), "EXCLUIRÓBITO");
		cards.add(new ObitoView("EXIBIR"), "EXIBIRÓBITO");
		
		cards.add(new SepultamentoView("ADICIONAR"), "ADICIONARSEPULTAMENTO");
		cards.add(new SepultamentoView("ALTERAR"), "ALTERARSEPULTAMENTO");
		cards.add(new SepultamentoView("EXCLUIR"), "EXCLUIRSEPULTAMENTO");
		cards.add(new SepultamentoView("EXIBIR"), "EXIBIRSEPULTAMENTO");
		
		cards.add(new UsuarioView("ADICIONAR"), "ADICIONARUSUÁRIO");
		cards.add(new UsuarioView("ALTERAR"), "ALTERARUSUÁRIO");
		cards.add(new UsuarioView("EXCLUIR"), "EXCLUIRUSUÁRIO");
		cards.add(new UsuarioView("EXIBIR"), "EXIBIRUSUÁRIO");
		
		getContentPane().add(cards);
		pack();
		setVisible(true);
	}
/*****************************************************************************/
	
	
	class ChangeCardlayoutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			CardLayout cl = (CardLayout) (cards.getLayout());
			cl.show(cards, (String) evt.getActionCommand());
		}
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(Menu::new);
	}
	
}
