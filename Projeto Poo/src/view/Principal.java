package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Principal {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					login.setTitle("Login");
					login.setBounds(70, 30, 1200, 700);
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}