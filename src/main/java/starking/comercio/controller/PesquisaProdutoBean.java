package starking.comercio.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import starking.comercio.model.Produto;
import starking.comercio.repository.ProdutoRepository;
import starking.comercio.repository.filter.ProdutoFilter;
import starking.comercio.util.jsf.FacesUtil;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Produto> produtosFiltrados;
	
	@Inject
	private ProdutoRepository repository;
	
	private ProdutoFilter filter;
	
	private Produto produtoSelecionado;
	
	public PesquisaProdutoBean() {
		filter = new ProdutoFilter();
	}
	
	public void excluir() {
		this.repository.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
		
		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() 
				+ " excluído com sucesso.");
	}
	
	public void pesquisar() {
		produtosFiltrados = this.repository.filtrados(filter);
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFilter() {
		return filter;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
}
