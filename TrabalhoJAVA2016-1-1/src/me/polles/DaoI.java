package me.polles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoI<T, K> implements Dao<T, K> {
	
	private static Connection abrirConexao() throws SQLException {

		// Abre a conexão
		String url = "jdbc:h2:~/PollesDB2";
		String user = "sa";
		String pass = "sa";
		Connection c = DriverManager.getConnection(url, user, pass);
		return c;

	}

	@Override
	public void salvar(T t) {
		// TODO Auto-generated method stub
		// Instancia o SqlGen e o Connection
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			// Abre a conexão e faz as devidas consultas
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
		// Instancia o SqlGen e o Connection
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			// Abre a conexão e faz as devidas consultas
			Con = abrirConexao();
			PreparedStatement PSSea = sql.getSqlSelectById(Con, k);
			PSSea.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void atualizar(T t) {
		// TODO Auto-generated method stub
		// Instancia o SqlGen e o Connection
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			// Abre a conexão e faz as devidas consultas
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
		// Instancia o SqlGen e o Connection
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			// Abre a conexão e faz as devidas consultas
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
		// Instancia o SqlGen e o Connection
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			// Abre a conexão e faz as devidas consultas
			Con = abrirConexao();
			String SDr = sql.getDropTable(Con, t);
			PreparedStatement PSDr = Con.prepareStatement(SDr);
			PSDr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> listarTodos() {
//		// TODO Auto-generated method stub
		// Instancia o SqlGen e o Connection
//		SqlGen sql = new SQLGenI();
//		Connection Con = null;
//		List<T> LR = new ArrayList<T>();
//		LR.add(t);
//		String C = t.getClass().getSimpleName();
////		try {
		// Abre a conexão e faz as devidas consultas
////			Con = abrirConexao();
////			PreparedStatement PSAll = sql.getSqlSelectAll(Con, t);
////			PSAll.executeQuery();
////			ResultSet RS = PSAll.getResultSet();
////			boolean VRS = true;
////			int i = 0;
////			do{
////				if(i > 0){
////					RS.next();
////				}
////				
//////				Object<C> obj = new Object();
////				
////			}while(!RS.isLast());
////			RS.close();
////			return LR;
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
		return null;
	}

	@Override
	public void criarTabela(T t) {
		// TODO Auto-generated method stub
		// Instancia o SqlGen e o Connection
		SqlGen sql = new SQLGenI();
		Connection Con = null;
		try {
			// Abre a conexão e faz as devidas consultas
			Con = abrirConexao();
			String SCr = sql.getCreateTable(Con, t);
			PreparedStatement PSCr = Con.prepareStatement(SCr);
			PSCr.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
