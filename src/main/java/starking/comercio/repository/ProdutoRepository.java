package starking.comercio.repository;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import starking.comercio.model.Produto;
import starking.comercio.repository.filter.ProdutoFilter;
import starking.comercio.service.NegocioException;
import starking.comercio.util.jpa.Transactional;

/**
 * @author pedroRhamon
 */
public class ProdutoRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Produto porId(Long id) {
		return this.manager.find(Produto.class, id);
	}
	
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
	
	public List<Produto> filtrados(ProdutoFilter filter) {
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if(isNotBlank(filter.getSku())) {
			criteria.add(Restrictions.eqOrIsNull("sku", filter.getSku()));
		}
		
		if (isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	
	@Transactional
	public void remover(Produto produto) {
		try {
			produto = this.porId(produto.getId());
			this.manager.remove(produto);
			this.manager.flush();
			
		} catch (Exception e) {
			throw new NegocioException("Produto não pode ser excluido");
		}
	}

}
