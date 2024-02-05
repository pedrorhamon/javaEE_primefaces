package starking.comercio.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import starking.comercio.model.Pedido;

@Getter
@AllArgsConstructor
public class PedidoAlteradoEvent {

	private Pedido pedido;

}