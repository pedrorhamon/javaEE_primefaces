package starking.comercio.repository.filter;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private boolean ativo;
}