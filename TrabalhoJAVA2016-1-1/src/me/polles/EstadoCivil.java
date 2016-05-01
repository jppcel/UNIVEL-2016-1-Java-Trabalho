package me.polles;

public enum EstadoCivil {
	
	SOLTEIRO("Solteiro", 1), 
	CASADO("Casado", 2),
	VIUVO("Viuvo", 3);
	
	private String estadoCivil;
	private final int id;
	
	public String getEstadoCivil(){
		return estadoCivil;
	}
	
	public String getID(){
		return id;
	}

}
