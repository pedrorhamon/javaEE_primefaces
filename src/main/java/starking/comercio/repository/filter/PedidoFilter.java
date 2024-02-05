package starking.comercio.repository.filter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import starking.comercio.model.enums.StatusPedido;

@Data
public class PedidoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numeroDe;
	private Long numeroAte;
	private Date dataCriacaoDe;
	private Date dataCriacaoAte;
	private String nomeVendedor;
	private String nomeCliente;
	private StatusPedido[] status;

}