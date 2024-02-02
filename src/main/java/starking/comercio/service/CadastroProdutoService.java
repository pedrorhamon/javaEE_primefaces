package starking.comercio.service;

import java.io.Serializable;

import javax.inject.Inject;

import starking.comercio.model.Produto;
import starking.comercio.repository.ProdutoRepository;
import starking.comercio.util.jpa.Transactional;

/**
 * @author pedroRhamon
 */
public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository repository;

	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = this.repository.porSku(produto.getSku());

		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}

		return this.repository.salvarProduto(produto);
	}

}
