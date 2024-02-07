package starking.comercio.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import lombok.Getter;
import starking.comercio.repository.PedidoRepository;
import starking.comercio.util.security.UsuarioSistema;

/**
 * @author pedroRhamon
 */

@Named
@RequestScoped
@Getter
public class GraficoPedidosCriadosBean {
	
	private CartesianChartModel model;
	
	@Inject
	private PedidoRepository pedidoRepository;
	
	@Inject
	private UsuarioSistema usuarioLogado;
	
	public void preRender() {
		this.model = new CartesianChartModel();
		
		this.adicionarSerie("Todos os pedidos");
		this.adicionarSerie("Meus pedidos");
	}

	private void adicionarSerie(String string) {
		ChartSeries series = new ChartSeries();
		
		series.set("1", Math.random() * 1000);
		series.set("2", Math.random() * 1000);
		series.set("3", Math.random() * 1000);
		series.set("4", Math.random() * 1000);
		series.set("5", Math.random() * 1000);
		
		this.model.addSeries(series);
	}

}
