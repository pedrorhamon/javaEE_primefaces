package starking.comercio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import starking.comercio.model.Categoria;
import starking.comercio.repository.CategoriasRepository;

/**
 * @author pedroRhamon
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	private CategoriasRepository repository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = this.repository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? ((Categoria) value).getId().toString() : "";
	}

}
