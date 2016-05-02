package me.polles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoI<T, K> implements Dao<T, K> {
	
	private static Connection abrirConexao() throws SQLException {

//        Class.forName("org.h2.Driver");
		String url = "jdbc:h2:~/PollesDB2";
		String user = "sa";
		String pass = "sa";
		Connection c = DriverManager.getConnection(url, user, pass);
		return c;

	}

	@Override
	public void salvar(T t) {
		// TODO Auto-generated method stub
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			Con = abrirConexao();
			PreparedStatement PSIns = sql.getSqlInsert(Con, t);
			PSIns.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T buscar(K k) {
		// TODO Auto-generated method stub
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			Con = abrirConexao();
			PreparedStatement PSSea = sql.getSqlSelectAll(Con, k);
			PSSea.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void atualizar(T t) {
		// TODO Auto-generated method stub
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			Con = abrirConexao();
			PreparedStatement PSUpd = sql.getSqlUpdateById(Con, t);
			PSUpd.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(K k) {
		// TODO Auto-generated method stub
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			Con = abrirConexao();
			PreparedStatement PSExc = sql.getSqlDeleteById(Con, k);
			PSExc.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void drop(T t) {
		// TODO Auto-generated method stub
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			Con = abrirConexao();
			sql.getDropTable(Con, t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarTabela(T t) {
		// TODO Auto-generated method stub
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			Con = abrirConexao();
			sql.getCreateTable(Con, t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
