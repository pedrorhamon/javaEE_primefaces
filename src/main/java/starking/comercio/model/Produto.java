package starking.comercio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author pedroRhamon
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String sku;
	private BigDecimal valorUnitario;
	private Integer quantidadeEstoque;
	private Categoria categoria;
}
