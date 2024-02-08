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

import starking.comercio.model.Pedido;
import starking.comercio.model.Produto;
import starking.comercio.model.Usuario;
import starking.comercio.repository.filter.UsuarioFilter;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	
	public Usuario guardar(Usuario usuario) {
		return this.manager.merge(usuario);
	}
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}

	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;

		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {

		}

		return usuario;
	}

	public List<Usuario> filtrados(UsuarioFilter filtro) {
	    Session session = this.manager.unwrap(Session.class);
	    Criteria criteria = session.createCriteria(Usuario.class);

	    if (isNotBlank(filtro.getNome())) {
	        criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
	    }

	    if (isNotBlank(filtro.getEmail())) {
	        criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
	    }

	    return criteria.addOrder(Order.asc("nome")).list();
	}


}