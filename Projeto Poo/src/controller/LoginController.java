package controller;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import model.service.LoginService;
import view.Login;

public class LoginController {
	public boolean Logar(Login frame) throws NoSuchAlgorithmException {
		String login = frame.getTxtLogin().getText();
		char[] password = frame.getTxtSenha().getPassword();
		String senha = new String(password);
		Arrays.fill(password, '0');
		LoginService service = new LoginService();
		return service.logar(login, senha);
	}
}
