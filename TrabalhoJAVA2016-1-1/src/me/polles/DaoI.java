package me.polles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DaoI<T, K> implements Dao<T, K> {
	
	private static Connection abrirConexao() throws SQLException {

		String url = "jdbc:h2:~/PollesDB";
		String user = "sa";
		String pass = "sa";
		Connection c = DriverManager.getConnection(url, user, pass);
		return c;

	}

	@Override
	public void salvar(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T buscar(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(K k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}