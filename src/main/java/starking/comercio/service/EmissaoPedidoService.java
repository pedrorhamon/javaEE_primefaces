package starking.comercio.service;

import java.io.Serializable;

import javax.inject.Inject;

import starking.comercio.model.Pedido;
import starking.comercio.model.enums.StatusPedido;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.util.jpa.Transactional;


public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPedidoService cadastroPedidoService;

	@Inject
	private EstoqueService estoqueService;

	@Inject
	private PedidoRepository pedidoRepository;

	@Transactional
	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);

		if (pedido.isNaoEmissivel()) {
			throw new NegocioException(
					"Pedido não pode ser emitido com status " + pedido.getStatus().getDescricao() + ".");
		}

		this.estoqueService.baixarItensEstoque(pedido);

		pedido.setStatus(StatusPedido.EMITIDO);

		pedido = this.pedidoRepository.guardar(pedido);

		return pedido;
	}

}