package starking.comercio.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import starking.comercio.model.Cliente;
import starking.comercio.model.EnderecoEntrega;
import starking.comercio.model.Pedido;
import starking.comercio.model.Usuario;
import starking.comercio.model.enums.FormaPagamento;
import starking.comercio.repository.ClienteRepository;
import starking.comercio.repository.UsuarioRepository;
import starking.comercio.service.CadastroPedidoService;
import starking.comercio.util.jsf.FacesUtil;

/**
 * @author pedroRhamon
 */
@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private ClienteRepository clienteRepository;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	
	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.vendedores = this.usuarioRepository.vendedores();
		}
	}
	
	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void salvar() {
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}
	
	public void recalcularPedido() {
		this.pedido.recalcularValorTotal();
	}
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}
	
	public List<Cliente> completarCliente(String nome) {
		return this.clienteRepository.porNome(nome);
	}

	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}
	
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

}