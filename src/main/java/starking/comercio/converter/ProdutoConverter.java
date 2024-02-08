package starking.comercio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import starking.comercio.model.Produto;
import starking.comercio.repository.ProdutoRepository;
import starking.comercio.util.cdi.CDIServiceLocator;

/**
 * @author pedroRhamon
 */
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	private ProdutoRepository repository;
	
	public ProdutoConverter() {
		repository = CDIServiceLocator.getBean(ProdutoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = this.repository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}

		return "";
	}

}
