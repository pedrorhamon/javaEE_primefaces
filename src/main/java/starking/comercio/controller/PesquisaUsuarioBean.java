package starking.comercio.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import starking.comercio.model.Usuario;
import starking.comercio.repository.UsuarioRepository;
import starking.comercio.repository.filter.UsuarioFilter;
import starking.comercio.util.jsf.FacesUtil;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioRepository usuarios;
	
	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;
	
	private Usuario usuarioSelecionado;
	
	public PesquisaUsuarioBean() {
		filtro = new UsuarioFilter();
	}
	
	public void excluir() {
		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Produto " + usuarioSelecionado.getNome() 
				+ " excluído com sucesso.");
	}
	
	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}
	
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
}
