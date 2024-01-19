package starking.comercio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import starking.comercio.service.NegocioException;

/**
 * @author pedroRhamon
 */

@ManagedBean
@RequestScoped
public class CadastroPedidoBean {

	private List<Integer> itens = new ArrayList<>();

	public CadastroPedidoBean() {
		itens = new ArrayList<>();
		itens.add(1);
	}

	public List<Integer> getItens() {
		return itens;
	}
	
	public void salvar() {
		throw new NegocioException("");
	}

}
