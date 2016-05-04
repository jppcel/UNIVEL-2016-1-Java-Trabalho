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
		
		// Cria a tabela de Cliente
		DC.criarTabela(new Cliente());
		
		// Insere os clientes no DB
		DC.salvar(Cl1);
		DC.salvar(Cl2);
		DC.salvar(Cl3);
		
		// Lista todos os clientes
		List<Cliente> LC = DC.listarTodos();

		System.out.println("Lista de Clientes:");
		for(Cliente c: LC){
			String E = c.getEstadoCivil().toString();
			System.out.println(c.getId()+": "+c.getNome()+" "+c.getEndereco()+"; "+c.getTelefone()+"; "+E);
		}
		
		// Busca o Cliente 1
//		DC.buscar(Cl1.getId());
		
		// Altera o Cliente 1
//		Cl1.setNome("Joao P.");
//		DC.atualizar(Cl1);
		
		// Exclui o Cliente 2
//		DC.excluir(Cl2.getId());
		
		// Lista todos os clientes
//		DC.listarTodos(Cl1);
	}
	
}
