package starking.comercio.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author pedroRhamon
 */
@Getter
@AllArgsConstructor
public enum FormaPagamento {

	DINHEIRO("Dinheiro"), 
	CARTAO_CREDITO("Cartão de crédito"), 
	CARTAO_DEBITO("Cartão de débito"), 
	CHEQUE("Cheque"), 
	BOLETO_BANCARIO("Boleto bancário"), 
	DEPOSITO_BANCARIO("Depósito bancário");
	
	private String descricao;
}
