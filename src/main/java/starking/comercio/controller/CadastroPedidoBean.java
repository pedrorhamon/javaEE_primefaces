package starking.comercio.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import starking.comercio.model.Cliente;
import starking.comercio.model.EnderecoEntrega;
import starking.comercio.model.ItemPedido;
import starking.comercio.model.Pedido;
import starking.comercio.model.Produto;
import starking.comercio.model.Usuario;
import starking.comercio.model.enums.FormaPagamento;
import starking.comercio.repository.ClienteRepository;
import starking.comercio.repository.ProdutoRepository;
import starking.comercio.repository.UsuarioRepository;
import starking.comercio.service.CadastroPedidoService;
import starking.comercio.util.jsf.FacesUtil;
import starking.comercio.validation.PedidoEdicao;
import starking.comercio.validation.SKU;

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
	
	@Inject
	private ProdutoRepository produtoRepository;

	@Produces
	@PedidoEdicao
	private Pedido pedido;
	
	private List<Usuario> vendedores;
	
	private Produto produtoLinhaEditavel;
	
	private String sku;

	public CadastroPedidoBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.vendedores = this.usuarioRepository.vendedores();

			this.pedido.adicionarItemVazio();
			this.pedido.recalcularValorTotal();
		}
	}

	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}

	public void salvar() {
		this.pedido.removerItemVazio();
		
		try {
			this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
			FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}

	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}
	
	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);
		
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				
				this.pedido.recalcularValorTotal();
			}
		}
	}
	
	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtoRepository.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);
			}
		}
		
		this.pedido.recalcularValorTotal();
	}
	
	public List<Produto> completarProduto(String nome) {
		return this.produtoRepository.porNome(nome);
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
	
	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}