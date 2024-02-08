package starking.comercio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import starking.comercio.model.Categoria;
import starking.comercio.model.Produto;
import starking.comercio.model.Usuario;
import starking.comercio.service.CadastroUsuarioService;
import starking.comercio.util.jsf.FacesUtil;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroUsuarioService service;
	
	private Usuario usuario;
	
	public CadastroUsuarioBean() {
		this.limpar();
	}
	
	public void inicializar() {
//		System.out.println("Inicializando...");
		
	}
	
	
	private void limpar() {
		this.usuario = new Usuario();
	}
	
	public void salvar() {
		this.usuario = this.service.salvar(this.usuario);
		
		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
		this.limpar();
	}
	
	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
