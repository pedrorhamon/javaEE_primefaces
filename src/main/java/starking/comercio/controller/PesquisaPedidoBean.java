package starking.comercio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import starking.comercio.model.Pedido;
import starking.comercio.model.enums.StatusPedido;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.repository.filter.PedidoFilter;

/**
 * @author pedroRhamon
 */

@ManagedBean
@RequestScoped
public class PesquisaPedidoBean {

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
	
	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}
	
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}
}
