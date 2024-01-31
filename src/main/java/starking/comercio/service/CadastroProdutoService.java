package starking.comercio.service;

import java.io.Serializable;

import javax.inject.Inject;

import starking.comercio.model.Produto;
import starking.comercio.repository.ProdutoRepository;

/**
 * @author pedroRhamon
 */
public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository repository;

	public Produto salvar(Produto produto) {
		Produto produtoExistente = this.repository.porSku(produto.getSku());

		if (produtoExistente != null) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}

		return this.repository.salvarProduto(produto);
	}

}
