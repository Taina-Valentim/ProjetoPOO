package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inicio extends JPanel{
	private static final long serialVersionUID = 1L;

	public Inicio(){
		this.setBackground(new Color(135,206,235));
		JLabel label = new JLabel("Bem vindo(a)!");
		this.add(label);
	}
	
}
