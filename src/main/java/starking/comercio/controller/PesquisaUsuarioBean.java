package starking.comercio.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Data;
import starking.comercio.model.Usuario;
import starking.comercio.repository.UsuarioRepository;
import starking.comercio.repository.filter.UsuarioFilter;
import starking.comercio.util.jsf.FacesUtil;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
@Data
public class PesquisaUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository repository;
	
	private UsuarioFilter filter;
	private List<Usuario> usuarioFiltrados;
	
	private Usuario usuarioSelecionado;
	
	public PesquisaUsuarioBean() {
		filter = new UsuarioFilter();
	}

	public void pesquisar() {
		usuarioFiltrados = this.repository.filtrados(filter);
	}
	
	public void excluir() {
		this.repository.remover(usuarioSelecionado);
		usuarioFiltrados.remove(usuarioSelecionado);
		
		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome() 
				+ " excluído com sucesso.");
	}
}
