package starking.comercio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @author pedroRhamon
 */
@Data
public class DataValor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date data;
	private BigDecimal valor;
}
