package starking.comercio.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author pedroRhamon
 */

@ManagedBean
@RequestScoped
public class CadastroProdutoBean {

	public void salvar() {
		throw new RuntimeException("Teste de exceção.");
	}
}
