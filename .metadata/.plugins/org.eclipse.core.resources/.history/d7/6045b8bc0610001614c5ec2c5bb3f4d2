package me.polles;

import java.sql.SQLException;

public class Execute {
	
	public static void main(String[] args) throws SQLException{
		Dao<Cliente, Cliente> DC = new DaoI<Cliente, Cliente>();
		
		Cliente Cl1 = new Cliente(1,"João","Rua 1, 2","93258475", EstadoCivil.SOLTEIRO);
		Cliente Cl2 = new Cliente(2,"Paulo","Rua A, 43", "87452155", EstadoCivil.VIUVO);
		Cliente Cl3 = new Cliente(3,"Polles","Rua NULL, 94", "78525821", EstadoCivil.CASADO);

		DC.drop(Cl1);
		DC.criarTabela(Cl1);
	}
	
}
