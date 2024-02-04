package starking.comercio.model.enums;

import lombok.Getter;

/**
 * @author pedroRhamon
 */

@Getter
public enum StatusPedido {

	ORCAMENTO("Orçamento"), 
	EMITIDO("Emitido"),
	CANCELADO("Cancelado");

	private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}
}
