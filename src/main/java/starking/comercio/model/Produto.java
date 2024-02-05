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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import starking.comercio.service.NegocioException;
import starking.comercio.validation.SKU;

/**
 * @author pedroRhamon
 */
@Entity
@Table(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	private String nome;
	
	@NotBlank @SKU
	@Column(nullable = false, length = 20, unique = true)
	private String sku;
	
	@NotNull(message = "é obrigatório")
	@Column(name="valor_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorUnitario;
	
	@NotNull @Min(0) @Max(value = 9999, message = "tem um valor muito alto")
	@Column(name="quantidade_estoque", nullable = false, length = 5)
	private Integer quantidadeEstoque;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
	
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}

	public void baixarEstoque(Integer quantidade) {
		int novaQuantidade = this.getQuantidadeEstoque() - quantidade;
		
		if (novaQuantidade < 0) {
			throw new NegocioException("Não há disponibilidade no estoque de "
					+ quantidade + " itens do produto " + this.getSku() + ".");
		}
		
		this.setQuantidadeEstoque(novaQuantidade);
	}
}
