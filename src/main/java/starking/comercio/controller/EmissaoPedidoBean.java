package starking.comercio.controller;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import starking.comercio.model.Pedido;
import starking.comercio.service.EmissaoPedidoService;
import starking.comercio.util.jsf.FacesUtil;
import starking.comercio.validation.PedidoEdicao;

@Named
@ViewScoped
public class EmissaoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmissaoPedidoService emissaoPedidoService;

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

	public void emitirPedido() {
		this.pedido.removerItemVazio();

		try {
			this.pedido = this.emissaoPedidoService.emitir(this.pedido);
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));

			FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}

}