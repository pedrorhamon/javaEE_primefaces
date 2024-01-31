package starking.comercio.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import starking.comercio.model.Categoria;
import starking.comercio.model.Produto;
import starking.comercio.repository.CategoriasRepository;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriasRepository repository;
	
	private Produto produto;
	
	private List<Categoria> categoriasRaizes;
	
	private Categoria categoriaPai;
	
	public CadastroProdutoBean() {
		produto = new Produto();
	}
	
	public void inicializar() {
		System.out.println("Inicializando...");
		
		categoriasRaizes = this.repository.buscarCategoria();
	}
	
	public void salvar() {
		System.out.println("Categoria pai selecionada: " + categoriaPai.getDescricao());
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
}
