package starking.comercio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import starking.comercio.model.Categoria;
import starking.comercio.model.Produto;
import starking.comercio.repository.CategoriasRepository;
import starking.comercio.service.CadastroProdutoService;
import starking.comercio.util.jsf.FacesUtil;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriasRepository repository;
	
	@Inject
	private CadastroProdutoService service;
	
	private Produto produto;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	
	private Categoria categoriaPai;
	
	public CadastroProdutoBean() {
		this.limpar();
	}
	
	public void inicializar() {
//		System.out.println("Inicializando...");
		
		if(FacesUtil.isNotPostback()) {
			categoriasRaizes = this.repository.buscarCategoria();
			
			if(this.categoriaPai !=null) {
				this.carregarSubCategoria();
			}
		}
	}
	
	public void carregarSubCategoria() {
		subcategorias = this.repository.subcategoriasDe(categoriaPai);
	}
	
	private void limpar() {
		this.produto = new Produto();
		this.categoriaPai = null;
		this.subcategorias = new ArrayList<>();
	}
	
	public void salvar() {
		this.produto = this.service.salvar(this.produto);
		
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
		this.limpar();
	}

	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if(this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
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

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
}
