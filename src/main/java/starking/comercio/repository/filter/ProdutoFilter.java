package starking.comercio.repository.filter;

import java.io.Serializable;

import lombok.Data;
import starking.comercio.validation.SKU;

@Data
public class ProdutoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	@SKU
	private String sku;
	private String nome;
	
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}
}