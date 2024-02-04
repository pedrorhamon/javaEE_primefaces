package starking.comercio.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import starking.comercio.model.Pedido;
import starking.comercio.model.enums.StatusPedido;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository pedidos;

	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}

		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}

}