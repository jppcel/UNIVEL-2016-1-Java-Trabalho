package me.polles;

// Enum de EstadoCivil
public enum EstadoCivil {
	
	// Itens da Enum
	CASADO("Casado", 1),
	SOLTEIRO("Solteiro", 2), 
	VIUVO("Viuvo", 3);
	
	// Atributos de cada Enum
	private String estadoCivil;
	private final int id;
	
	// Metodo principal para retornar os dados da enum
	private EstadoCivil(String estadoCivil, int id){
		this.estadoCivil = estadoCivil;
		this.id = id;
	}
	
	// Getters para os itens da enum
	public String getEstadoCivil(){
		return estadoCivil;
	}
	
	public int getID(){
		return id;
	}

}
