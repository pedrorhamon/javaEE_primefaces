package starking.comercio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import starking.comercio.model.Pedido;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	// @Inject
	private PedidoRepository pedidoRepository;

	public PedidoConverter() {
		pedidoRepository = CDIServiceLocator.getBean(PedidoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = pedidoRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}

		return "";
	}

}