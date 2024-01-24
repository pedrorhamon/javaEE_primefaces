package starking.comercio.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import starking.comercio.model.Cliente;
import starking.comercio.model.Endereco;
import starking.comercio.model.enums.TipoPessoa;

/**
 * @author pedroRhamon
 */
public class TesteCliente {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("João das Couves");
		cliente.setEmail("joao@dascouves.com");
		cliente.setDocmentoReceitaFederal("123.123.123-12");
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua das Aboboras Vermelhas");
		endereco.setNumero("111");
		endereco.setCidade("Uberlândia");
		endereco.setUf("MG");
		endereco.setCep("38400-000");
		endereco.setCliente(cliente);
		
		cliente.getEndereco().add(endereco);
		
		manager.persist(cliente);
		
		trx.commit();
	}
}
