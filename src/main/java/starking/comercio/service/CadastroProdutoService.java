package starking.comercio.service;

import java.io.Serializable;

import starking.comercio.model.Produto;

/**
 * @author pedroRhamon
 */
public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Produto salvar(Produto produto) {
		return produto;
	}

}
