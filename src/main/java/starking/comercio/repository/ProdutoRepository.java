package starking.comercio.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import starking.comercio.model.Produto;

/**
 * @author pedroRhamon
 */
public class ProdutoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Produto salvarProduto(Produto produto) {
		return this.manager.merge(produto);
		
	}
	
	public Produto porSku(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
