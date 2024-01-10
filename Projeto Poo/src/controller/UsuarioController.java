package controller;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import model.entidade.Usuario;
import model.service.UsuarioService;
import view.UsuarioView;

public class UsuarioController {
	private Usuario usuario;

	public boolean ObterDadosView(UsuarioView frame, String acao) throws NoSuchAlgorithmException {
		usuario = new Usuario();
		usuario.setNome(frame.getTxtNome().getText());
		usuario.setSenha(frame.getTxtSenha().getText());
		usuario.setNivel(frame.getTxtNivel().getSelectedItem().toString());
		
		if(acao.equals("ADICIONAR")) return Salva(frame);
		else return Altera(frame);
		
    }
	
	public boolean Salva(UsuarioView frame) throws NoSuchAlgorithmException {
		UsuarioService service = new UsuarioService();
        return service.salvar(usuario);
	}
	
	public boolean Altera(UsuarioView frame) throws NoSuchAlgorithmException {
		UsuarioService service = new UsuarioService();
        return service.alterar(usuario, frame.getTxtAlterar().getSelectedItem().toString());
	}
	
	public boolean Exclui(UsuarioView frame) {
		UsuarioService service = new UsuarioService();
        return service.excluir(frame.getTxtExcluir().getSelectedItem().toString());
	}
	
	public ArrayList<Usuario> Consulta() {
		UsuarioService service = new UsuarioService();
        return service.consultar();
	}
}