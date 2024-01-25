package starking.comercio.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import starking.comercio.model.Categoria;
import starking.comercio.model.Produto;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	private Produto produto;
	
	private List<Categoria> categoriasRaizes;
	
	public void inicializar(ComponentSystemEvent event) {
		System.out.println("Inicializando...");
		categoriasRaizes = manager.createQuery("from Categoria", Categoria.class).getResultList();
		manager.close();
	}
	
	public CadastroProdutoBean() {
		produto = new Produto();
	}
	
	public void salvar() {
	}

	public Produto getProduto() {
		return produto;
	}
	
	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}
}
