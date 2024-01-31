package starking.comercio.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import starking.comercio.model.Categoria;

/**
 * @author pedroRhamon
 */
public class CategoriasRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Categoria porId(Long id) {
		return this.manager.find(Categoria.class, id);
	}

	public List<Categoria> buscarCategoria() {
		return this.manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}

	public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
		return this.manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
				.setParameter("raiz", categoriaPai).getResultList();
	}

}
