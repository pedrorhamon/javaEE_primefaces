package starking.comercio.service;

import java.io.Serializable;

import javax.inject.Inject;

import starking.comercio.model.ItemPedido;
import starking.comercio.model.Pedido;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository pedidoRepository;

	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidoRepository.porId(pedido.getId());

		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidoRepository.porId(pedido.getId());

		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
	}

}