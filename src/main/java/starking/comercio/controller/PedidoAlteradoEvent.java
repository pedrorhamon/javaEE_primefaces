package starking.comercio.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import starking.comercio.model.Pedido;

@Data
@AllArgsConstructor
public class PedidoAlteradoEvent {

	private Pedido pedido;

}