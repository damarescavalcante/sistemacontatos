package com.sistemacontatos.test;

import com.sistemacontatos.dao.ContatoDAO;
import com.sistemacontatos.model.Contato;

public class Teste {

	public static void main(String [] args) {
		
//		Contato c4 = new Contato();
		ContatoDAO cDAO = new ContatoDAO();
//		c4.setNome("Lorena Conceição");
//		c4.setEmail("lorena.con@gmail.com");
//		c4.setEndereco("Rua de Dentro");
//		
//		Calendar calendar = Calendar.getInstance();
//		c4.setDataNascimento(calendar);
//		
//		cDAO.adicionaContato(c4);
		
//		System.out.println(cDAO.listarContatos());
//		System.out.println(cDAO.buscarContato("Maria José", "maria.jose@gmail.com"));
//		Contato c = new Contato();
//		c.setId((long) 1);
//		c.setNome("Maria José de Carvalho");
//		c.setEndereco("Rua da Calma");
//		c.setEmail("maria.jose.carvalho@gmail.com");
//		cDAO.alterarDados(c);
		Contato c = new Contato();
		c.setId((long) 1);
		cDAO.removerContato(c);
	}
	
}
