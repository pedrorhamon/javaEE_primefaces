package starking.comercio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author pedroRhamon
 */

@ManagedBean
@RequestScoped
public class PesquisaProdutoBean {

	private List<Integer> produtosFiltrados = new ArrayList<>();
	
	public PesquisaProdutoBean() {
		for(int i = 0; i<50; i++) {
			produtosFiltrados.add(i);
		}
	}
	
	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}
}
