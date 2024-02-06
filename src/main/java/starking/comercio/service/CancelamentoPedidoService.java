package starking.comercio.service;

import java.io.Serializable;

import javax.inject.Inject;

import starking.comercio.model.Pedido;
import starking.comercio.model.enums.StatusPedido;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.util.jpa.Transactional;

/**
 * @author pedroRhamon
 */
public class CancelamentoPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository pedidoRepository;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = this.pedidoRepository.porId(pedido.getId());
		
		if (pedido.isNaoCancelavel()) {
			throw new NegocioException("Pedido não pode ser cancelado no status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		if (pedido.isEmitido()) {
			this.estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		
		pedido = this.pedidoRepository.guardar(pedido);
		
		return pedido;
	}

}
