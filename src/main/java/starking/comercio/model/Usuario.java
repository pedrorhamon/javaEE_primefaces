package starking.comercio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@EqualsAndHashCode
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Boolean ativo = true;
	private List<Grupo> grupos = new ArrayList<>();
}