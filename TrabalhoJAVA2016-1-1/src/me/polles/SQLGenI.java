package me.polles;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SQLGenI extends SqlGen {

	@Override
	protected String getCreateTable(Connection con, Object obj) {
		Class<?> Cla = obj.getClass();
		// TODO Auto-generated method stub
		try{
			StringBuilder StBu = new StringBuilder();
			String TableName;
			
			if(Cla.isAnnotationPresent(Tabela.class)){
			
				Tabela annotationTable = Cla.getAnnotation(Tabela.class);
				TableName = annotationTable.value();
			
			}else{
			
				TableName = Cla.getSimpleName().toUpperCase();
			
			}
			
			StBu.append("CREATE TABLE ").append(TableName).append(" (");
			Field[] attr = Cla.getDeclaredFields();

			int found = 0;
			StringBuilder pk = new StringBuilder();
			for(int i = 0; i < attr.length; i++){
				Field fie = attr[i];
				
				String ColumnName;
				String ColumnType;
				
				if(fie.isAnnotationPresent(Coluna.class)){
					Coluna annotationColumn = fie.getAnnotation(Coluna.class);
					if(annotationColumn.nome().isEmpty()){
						ColumnName = fie.getName().toUpperCase();
					}else{
						ColumnName = annotationColumn.nome();
					}
					
					if(annotationColumn.pk()){
						if(found > 0){
							pk.append(", ");
						}
						
						if(annotationColumn.nome().isEmpty()){
							pk.append(fie.getName().toUpperCase());
						}else{
							pk.append(annotationColumn.nome());
						}
						found++;
					}
				}else{
					ColumnName = fie.getName().toUpperCase();
				}
				
				Class<?> AttrType = fie.getType();
				
				if(AttrType.equals(String.class)){
					ColumnType = "VARCHAR(150)";
				}else if(AttrType.equals(EstadoCivil.class)){
					ColumnType = "INT";
				}else if(AttrType.equals(int.class)){
					ColumnType = "INT";
				}else{
					ColumnType = "UNKNOWN";
				}
				
				if(i > 0){
					StBu.append(",");
				}
				StBu.append(ColumnName).append(" ").append(ColumnType);
			}
			StBu.append(", PRIMARY KEY(").append(pk.toString()).append(")");
			StBu.append(");");
			String Return = StBu.toString();
			System.out.println("SQL GENERATED: "+ Return);
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
		
		return null;
	}

	@Override
	protected PreparedStatement getSqlSelectAll(Connection con, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement getSqlSelectById(Connection con, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement getSqlUpdateById(Connection con, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement getSqlDeleteById(Connection con, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
