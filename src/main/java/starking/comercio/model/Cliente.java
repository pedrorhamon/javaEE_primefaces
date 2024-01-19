package starking.comercio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import starking.comercio.model.enums.TipoPessoa;

/**
 * @author pedroRhamon
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String email;
	private String docmentoReceitaFederal;
	private TipoPessoa tipoPessoa;
	private List<Endereco> endereco = new ArrayList<>();

}
