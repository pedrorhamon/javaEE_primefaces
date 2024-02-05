package starking.comercio.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pedroRhamon
 */

@Getter
@AllArgsConstructor
public enum StatusPedido {

	ORCAMENTO("Orçamento"), 
	EMITIDO("Emitido"),
	CANCELADO("Cancelado");

	private String descricao;
}
