package starking.comercio.service;

import java.io.Serializable;

import javax.inject.Inject;

import starking.comercio.model.Usuario;
import starking.comercio.repository.UsuarioRepository;
import starking.comercio.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository repository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return this.repository.guardar(usuario);
	}
}