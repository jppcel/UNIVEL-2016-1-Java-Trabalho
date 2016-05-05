package me.polles;

import java.sql.SQLException;
import java.util.List;

public class Execute {
	
	public static void main(String[] args) throws SQLException{
		Dao<Cliente, Integer> DC = new DaoI();
		
		//Instancia os objetos de clientes
		Cliente Cl1 = new Cliente(1,"Joao","Rua 1, 2","(93) 258475", EstadoCivil.SOLTEIRO);
		Cliente Cl2 = new Cliente(2,"Paulo","Rua A, 43", "(87) 452155", EstadoCivil.VIUVO);
		Cliente Cl3 = new Cliente(3,"Polles","Rua NULL, 94", "(78) 525821", EstadoCivil.CASADO);
		
		// Apaga a tabela relativa a cliente(se existir)
		DC.drop(new Cliente());
		System.out.println("=====");
		
		// Cria a tabela de Cliente
		DC.criarTabela(new Cliente());
		System.out.println("=====");
		
		// Insere os clientes no DB
		DC.salvar(Cl1);
		System.out.println("=====");
		DC.salvar(Cl2);
		System.out.println("=====");
		DC.salvar(Cl3);
		System.out.println("=====");
		
		// Lista todos os clientes
		List<Cliente> LC = DC.listarTodos();

		System.out.println("Lista de Clientes:");
		for(Cliente c: LC){
			String E = c.getEstadoCivil().toString();
			System.out.println(c.getId()+": "+c.getNome()+" "+c.getEndereco()+"; "+c.getTelefone()+"; "+E);
		}
		System.out.println("=====");
		
		// Busca o Cliente 1
		Cliente Busca = DC.buscar(Cl1.getId());
		

		System.out.println(Busca.getId()+": "+Busca.getNome()+" "+Busca.getEndereco()+"; "+Busca.getTelefone()+"; "+Busca.getEstadoCivil().name());
		System.out.println("=====");
		
		// Altera o Cliente 1
		Cl1.setNome("Joao P.");
		DC.atualizar(Cl1);
		System.out.println("=====");
		
		// Exclui o Cliente 2
		DC.excluir(Cl2.getId());
		System.out.println("=====");
		
		// Lista todos os clientes
		LC = DC.listarTodos();

		System.out.println("Lista de Clientes:");
		for(Cliente c: LC){
			String E = c.getEstadoCivil().toString();
			System.out.println(c.getId()+": "+c.getNome()+" "+c.getEndereco()+"; "+c.getTelefone()+"; "+E);
		}
	}
	
}
