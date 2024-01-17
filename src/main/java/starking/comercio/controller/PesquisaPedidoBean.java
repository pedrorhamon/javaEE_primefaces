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
public class PesquisaPedidoBean {

	private List<Integer> pedidosFiltrados = new ArrayList<>();
	
	public PesquisaPedidoBean() {
		for(int i = 0; i<50; i++) {
			pedidosFiltrados.add(i);
		}
	}
	
	public List<Integer> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
}
