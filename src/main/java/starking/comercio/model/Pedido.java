package starking.comercio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import starking.comercio.model.enums.FormaPagamento;
import starking.comercio.model.enums.StatusPedido;

/**
 * @author pedroRhamon
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	private Date dataCriacao;
	private String observacao;
	private Date dataEntrega;
	private BigDecimal valorFrete;
	private BigDecimal valorDesconto;
	private BigDecimal valorTotal;
	private StatusPedido status;
	private FormaPagamento formaPagamento;
	private Usuario vendedor;
	private Cliente cliente;
	private EnderecoEntrega enderecoEntrega;
	private List<ItemPedido> itens = new ArrayList<>();
}
