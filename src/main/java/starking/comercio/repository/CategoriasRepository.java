package starking.comercio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import starking.comercio.model.Categoria;

/**
 * @author pedroRhamon
 */
public class CategoriasRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> buscarCategoria() {
		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}

}
