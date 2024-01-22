package starking.comercio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author pedroRhamon
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "item_Pedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 3)
	private Integer quantidade;
	
	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorUnitario;
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;

}
