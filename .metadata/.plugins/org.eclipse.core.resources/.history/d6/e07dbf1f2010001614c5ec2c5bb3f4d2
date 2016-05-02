package me.polles;

import java.sql.SQLException;

public class Execute {
	
	public static void main(String[] args) throws SQLException{
		Dao<Cliente, Cliente> DC = new DaoI<Cliente, Cliente>();
		
		//Instancia os objetos de clientes
		Cliente Cl1 = new Cliente(1,"João","Rua 1, 2","93258475", EstadoCivil.SOLTEIRO);
		Cliente Cl2 = new Cliente(2,"Paulo","Rua A, 43", "87452155", EstadoCivil.VIUVO);
		Cliente Cl3 = new Cliente(3,"Polles","Rua NULL, 94", "78525821", EstadoCivil.CASADO);
		
		// Apaga a tabela relativa a cliente(se existir)
		DC.drop(new Cliente());
		
		// Cria a tabela de Cliente
		DC.criarTabela(new Cliente());
		
		// Insere os clientes no DB
		DC.salvar(Cl1);
		DC.salvar(Cl2);
		DC.salvar(Cl3);
		
		// Lista todos os clientes
//		DC.listarTodos(Cl1);
		
		// Busca o Cliente 1
		DC.buscar(Cl1);
		
		// Altera o Cliente 1
		Cl1.setNome("João P.");
		DC.atualizar(Cl1);
		
		// Exclui o Cliente 2
		DC.excluir(Cl2);
		
		// Lista todos os clientes
//		DC.listarTodos(Cl1);
	}
	
}
