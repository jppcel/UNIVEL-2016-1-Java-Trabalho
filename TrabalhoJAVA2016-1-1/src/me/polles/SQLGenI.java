package me.polles;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLGenI extends SqlGen {


	@Override
	protected String getCreateTable(Connection con, Object obj) {
		// TODO Auto-generated method stub
		// Pega a Classe do Objeto
		Class<?> Cla = obj.getClass();
		try{
			// Cria a variavel com o StringBuilder e a string com o nome da tabela
			StringBuilder StBu = new StringBuilder();
			String TableName;
			
			// Faz a verificacao para decidir o nome da tabela
			if(Cla.isAnnotationPresent(Tabela.class)){
			
				Tabela annotationTable = Cla.getAnnotation(Tabela.class);
				TableName = annotationTable.value();
			
			}else{
			
				TableName = Cla.getSimpleName().toUpperCase();
			
			}
			
			// Comeca de fato a criacao do SQL para gerar o SQL
			StBu.append("CREATE TABLE ").append(TableName).append(" (");
			Field[] attr = Cla.getDeclaredFields();

			// Criadas variaveis de controle e stringbuilder para armazenar as pks
			int found = 0;
			StringBuilder pk = new StringBuilder();
			int ColumnSize = 0;
			// For para varrer todos os atributos da classe
			for(int i = 0; i < attr.length; i++){
				// Pega o atributo
				Field fie = attr[i];
				
				// Cria variaveis de nome e tipo da coluna
				String ColumnName;
				String ColumnType;

				// Pega o Tipo da Coluna
				Class<?> AttrType = fie.getType();
				
				// Faz toda a verificacao para saber o nome da coluna, seu tipo e se a mesma é PK
				if(fie.isAnnotationPresent(Coluna.class)){
					Coluna annotationColumn = fie.getAnnotation(Coluna.class);
					if(annotationColumn.nome().isEmpty()){
						ColumnName = fie.getName().toUpperCase();
					}else{
						ColumnName = annotationColumn.nome();
					}
					
					if(annotationColumn.pk()){
						if(annotationColumn.nome().isEmpty()){
							pk.append(fie.getName().toUpperCase());
						}else{
							pk.append(annotationColumn.nome());
						}
						found++;
					}
					if(AttrType.equals(String.class)){
						ColumnSize = annotationColumn.tamanho();
					}
				}else{
					ColumnName = fie.getName().toUpperCase();
				}
				
				// Decide o tipo e o tamanho da coluna
				if(AttrType.equals(String.class)){
					ColumnType = "VARCHAR(" + ColumnSize + ")";
				}else if(AttrType.equals(EstadoCivil.class)){
					ColumnType = "INT";
				}else if(AttrType.equals(int.class)){
					ColumnType = "INT";
				}else{
					ColumnType = "UNKNOWN";
				}
				
				// Caso tenha mais atribuitos, ele coloca uma virgula para separar
				if(i > 0){
					StBu.append(",");
				}
				
				// Coloca as informacoes no SQL
				StBu.append(ColumnName).append(" ").append(ColumnType);
			}
			// Informa quais sao as PKs e finaliza o SQL
			StBu.append(", PRIMARY KEY(").append(pk.toString()).append(")");
			StBu.append(");");
			// Guarda o SQL em uma variável e mostra na tela qual o SQL.
			String Return = StBu.toString();
			System.out.println("SQL GENERATED: "+ Return);
			// Retorna o SQL...
			return Return;
		}catch(SecurityException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getDropTable(Connection con, Object obj) {
		// TODO Auto-generated method stub
		Class<?> Cla = obj.getClass();
		StringBuilder StBu = new StringBuilder();
		
		String TableName;

		if(Cla.isAnnotationPresent(Tabela.class)){
			Tabela annotationTable = Cla.getAnnotation(Tabela.class);
			TableName = annotationTable.value();
		}else{
			TableName = Cla.getSimpleName().toUpperCase();
		}
		
		StBu.append("DROP TABLE ").append(TableName).append(" IF EXISTS;");
		String Return = StBu.toString();
		System.out.println("SQL GENERATED: "+Return);
		return Return;
	}

	@Override
	protected PreparedStatement getSqlInsert(Connection con, Object obj) {
		// TODO Auto-generated method stub
		Class<?> Cla = obj.getClass();
		StringBuilder StBu = new StringBuilder();
		
		String TableName;
		if(Cla.isAnnotationPresent(Tabela.class)){
			
			Tabela annotationTable = Cla.getAnnotation(Tabela.class);
			TableName = annotationTable.value();
		
		}else{
		
			TableName = Cla.getSimpleName().toUpperCase();
		
		}
		
		StBu.append("INSERT INTO ").append(TableName).append(" (");
		
		Field[] attr = Cla.getDeclaredFields();
		for(int i = 0; i < attr.length; i++){
			Field fie = attr[i];
			
			String ColumnName;
			if(fie.isAnnotationPresent(Coluna.class)){
				Coluna ColumnAnnotation = fie.getAnnotation(Coluna.class);
				
				if(ColumnAnnotation.nome().isEmpty()){
					ColumnName = fie.getName().toUpperCase();
				}else{
					ColumnName = ColumnAnnotation.nome();
				}
				
			}else{
				ColumnName = fie.getName().toUpperCase();
			}
			
			if(i > 0){
				StBu.append(", ");
			}
			
			StBu.append(ColumnName);
		}
		StBu.append(") VALUES (");
		for(int i = 0; i < attr.length; i++){
			if(i > 0){
				StBu.append(", ");
			}
			StBu.append("?");
		}
		StBu.append(");");
		String SQL = StBu.toString();
		System.out.println(SQL);
				
		try {
			PreparedStatement PSIn = con.prepareStatement(SQL);
			for(int i = 0; i < attr.length; i++){
				Field fie = attr[i];
				
				fie.setAccessible(true);
				if(fie.getType().equals(String.class)){
					PSIn.setString(i+1, String.valueOf(fie.get(obj)));
				}else if(fie.getType().equals(int.class)){
					PSIn.setInt(i+1, fie.getInt(obj));
				}else if(fie.getType().equals(EstadoCivil.class)){
					String estadoCivil = String.valueOf(fie.get(obj));
					EstadoCivil EC = EstadoCivil.valueOf(estadoCivil);
					PSIn.setInt(i+1, EC.getID());
				} else {
					throw new RuntimeException("Unsupported type.");
				}
			}
			return PSIn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected PreparedStatement getSqlSelectAll(Connection con, Object obj) {
		// TODO Auto-generated method stub
		Class<?> Cla = obj.getClass();
		StringBuilder StBu = new StringBuilder();
		
		String TableName;
		if(Cla.isAnnotationPresent(Tabela.class)){
			
			Tabela annotationTable = Cla.getAnnotation(Tabela.class);
			TableName = annotationTable.value();
		
		}else{
		
			TableName = Cla.getSimpleName().toUpperCase();
		
		}
		
		StBu.append("SELECT * FROM ").append(TableName).append(";");
		System.out.println(StBu.toString());
		try {
			PreparedStatement PSAll = con.prepareStatement(StBu.toString());
			return PSAll;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected PreparedStatement getSqlSelectById(Connection con, Object obj) {
		// TODO Auto-generated method stub
		Class<?> Cla = obj.getClass();
		StringBuilder StBu = new StringBuilder();
		
		String TableName;
		
		if(Cla.isAnnotationPresent(Tabela.class)){
			
			Tabela annotationTable = Cla.getAnnotation(Tabela.class);
			TableName = annotationTable.value();
		
		}else{
		
			TableName = Cla.getSimpleName().toUpperCase();
		
		}
		
		StBu.append("SELECT * FROM ").append(TableName).append(" WHERE ");

		Field[] attr = Cla.getDeclaredFields();
		
		int found = 0;
		
		for(int i = 0; i < attr.length; i++){
			Field fie = attr[i];
			
			String ColumnName;
			
			Coluna annotationColumn = fie.getAnnotation(Coluna.class);

			ColumnName = annotationColumn.nome();
			if(fie.isAnnotationPresent(Coluna.class)){				
				if(annotationColumn.pk()){					
					if(annotationColumn.nome().isEmpty()){
						ColumnName = fie.getName().toUpperCase();
					}else{
						ColumnName = annotationColumn.nome();
					}
					found++;
				}
			}
			if(found == 1){
				StBu.append(ColumnName).append(" = ?");				
			}
		}
		
		StBu.append(";");
		String SQL = StBu.toString();
		System.out.println(SQL);
		

		PreparedStatement PSSea = null;
		try {
			PSSea = con.prepareStatement(SQL);
			for(int i = 0, j = 0; i < attr.length; i++){
				Field fie = attr[i];
				
				Coluna annotationColumn = fie.getAnnotation(Coluna.class);
				
				if(fie.isAnnotationPresent(Coluna.class)){				
					if(annotationColumn.pk()){	
						fie.setAccessible(true);
						
						if(fie.getType().equals(String.class)){
							PSSea.setString(j+1, String.valueOf(fie.get(obj)));
						}else if(fie.getType().equals(int.class)){
							PSSea.setInt(j+1, fie.getInt(obj));
						}else if(fie.getType().equals(EstadoCivil.class)){
							String estadoCivil = String.valueOf(fie.get(obj));
							EstadoCivil EC = EstadoCivil.valueOf(estadoCivil);
							PSSea.setInt(j+1, EC.getID());
						}
						j++;
					}
				}
			}
			return PSSea;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PSSea;
	}

	@Override
	protected PreparedStatement getSqlUpdateById(Connection con, Object obj) {
		return null;
		// TODO Auto-generated method stub
//		Class<?> Cla = obj.getClass();
//		StringBuilder StBu = new StringBuilder();
//		
//		String TableName;
//		if(Cla.isAnnotationPresent(Tabela.class)){
//			
//			Tabela annotationTable = Cla.getAnnotation(Tabela.class);
//			TableName = annotationTable.value();
//		
//		}else{
//		
//			TableName = Cla.getSimpleName().toUpperCase();
//		
//		}
//		
//		StBu.append("UPDATE ").append(TableName).append(" SET ");
//		
//		Field[] attr = Cla.getDeclaredFields();
//		StringBuilder pk = new StringBuilder();
//		int pks = 0;
//		pKs = null;
//		values = null;
//		for(int i = 0, P = 0, V = 0; i < attr.length; i++){
//			Field fie = attr[i];
//			int isPK = 0;
//			
//			String ColumnName;
//			if(fie.isAnnotationPresent(Coluna.class)){
//				Coluna ColumnAnnotation = fie.getAnnotation(Coluna.class);
//				if(ColumnAnnotation.pk()){
//					if(pks > 0){
//						pk.append(" && ");
//					}
//					if(ColumnAnnotation.nome().isEmpty()){
//						ColumnName = fie.getName().toUpperCase();
//					}else{
//						ColumnName = ColumnAnnotation.nome();
//					}
//					
//					pk.append(ColumnName).append(" = ?");
//					if(i+1 < attr.length){
//						StBu.append(", ");
//					}
//					
//					pks++;
//					isPK = 1;
//				}else{
//					if(ColumnAnnotation.nome().isEmpty()){
//						ColumnName = fie.getName().toUpperCase();
//					}else{
//						ColumnName = ColumnAnnotation.nome();
//					}
//					
//					if(i > 0){
//						StBu.append(", ");
//					}
//					
//					StBu.append(ColumnName).append(" = ?");
//					if(i+1 < attr.length){
//						StBu.append(", ");
//					}
//				}
//				
//			}else{
//				ColumnName = fie.getName().toUpperCase();
//				
//				if(i > 0){
//					StBu.append(", ");
//				}
//				
//				StBu.append(ColumnName).append(" = ?");
//				if(i+1 < attr.length){
//					StBu.append(", ");
//				}
//			}
//			
//			if(isPK == 1){
//				pKs[P] = fie;
//				P++;
//			}else{
//				values[V] = fie;
//				V++;
//			}
//		}
//		StBu.append(" WHERE ").append(pk.toString());
//		
//		
//		
//		String SQL = StBu.toString();
//		System.out.println(SQL);
//				
//		try {
//			PreparedStatement PSUp = con.prepareStatement(SQL);
//			for(int i = 0; i < values.length; i++){
//				Field fie = values[i];
//				
//				fie.setAccessible(true);
//				if(fie.getType().equals(String.class)){
//					PSUp.setString(i+1, String.valueOf(fie.get(obj)));
//				}else if(fie.getType().equals(int.class)){
//					PSUp.setInt(i+1, fie.getInt(obj));
//				}else if(fie.getType().equals(EstadoCivil.class)){
//					String estadoCivil = String.valueOf(fie.get(obj));
//					EstadoCivil EC = EstadoCivil.valueOf(estadoCivil);
//					PSUp.setInt(i+1, EC.getID());
//				} else {
//					throw new RuntimeException("Unsupported type.");
//				}
//			}
//			for(int i = 0; i < pKs.length; i++){
//				Field fie = pKs[i];
//				
//				fie.setAccessible(true);
//				if(fie.getType().equals(String.class)){
//					PSUp.setString(i+1, String.valueOf(fie.get(obj)));
//				}else if(fie.getType().equals(int.class)){
//					PSUp.setInt(i+1, fie.getInt(obj));
//				}else if(fie.getType().equals(EstadoCivil.class)){
//					String estadoCivil = String.valueOf(fie.get(obj));
//					EstadoCivil EC = EstadoCivil.valueOf(estadoCivil);
//					PSUp.setInt(i+1, EC.getID());
//				} else {
//					throw new RuntimeException("Unsupported type.");
//				}
//			}
//			return PSUp;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	protected PreparedStatement getSqlDeleteById(Connection con, Object obj) {
		// TODO Auto-generated method stub
		Class<?> Cla = obj.getClass();
		StringBuilder StBu = new StringBuilder();
		
		String TableName;
		
		if(Cla.isAnnotationPresent(Tabela.class)){
			
			Tabela annotationTable = Cla.getAnnotation(Tabela.class);
			TableName = annotationTable.value();
		
		}else{
		
			TableName = Cla.getSimpleName().toUpperCase();
		
		}
		
		StBu.append("DELETE FROM ").append(TableName).append(" WHERE ");

		Field[] attr = Cla.getDeclaredFields();
		
		int found = 0;
		
		Field pk = null;
		
		for(int i = 0; i < attr.length && found == 0; i++){
			Field fie = attr[i];
			
			String ColumnName;
			
			Coluna annotationColumn = fie.getAnnotation(Coluna.class);

			ColumnName = annotationColumn.nome();
			if(fie.isAnnotationPresent(Coluna.class)){				
				if(annotationColumn.pk()){					
					if(annotationColumn.nome().isEmpty()){
						ColumnName = fie.getName().toUpperCase();
					}else{
						ColumnName = annotationColumn.nome();
					}
					found++;
				}
			}
			if(found == 1){
				
				StBu.append(ColumnName).append(" = ?");
				pk = fie;
				
				
			}
		}
		
		StBu.append(";");
		String SQL = StBu.toString();
		System.out.println(SQL);
		

		PreparedStatement PSDel = null;
		try {
			PSDel = con.prepareStatement(SQL);
			PSDel.setInt(0, pk.getInt(obj));
			return PSDel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PSDel;
	}

}
