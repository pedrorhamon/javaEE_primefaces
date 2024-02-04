package starking.comercio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import starking.comercio.model.Pedido;
import starking.comercio.model.enums.StatusPedido;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.repository.filter.PedidoFilter;

/**
 * @author pedroRhamon
 */

@Named
@ViewScoped
public class PesquisaPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository repository;
	
	private PedidoFilter filtro;
	private List<Pedido> pedidosFiltrados;
	
	public PesquisaPedidoBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		pedidosFiltrados = this.repository.filtrados(filtro);
	}
	
	public StatusPedido[] getStatus() {
		return StatusPedido.values();
	}
	
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}
}
