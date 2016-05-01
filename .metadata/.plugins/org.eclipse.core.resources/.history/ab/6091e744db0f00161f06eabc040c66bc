package me.polles;


@Tabela("CLIENTE")
public class Cliente {
	
	@Coluna(pk=true)
	private int id;
	
	@Coluna(nome="NOME")
	private String nome;
	
	@Coluna(nome="ENDERECO")
	private String endereco;
	
	@Coluna(nome="TELEFONE")
	private String telefone;

	@Coluna(nome="CLESTADOCIVIL")
	private EstadoCivil estadoCivil;

	public Cliente() {
		this(0, null, null, null, null);
	}
	
	public Cliente(int ID, String Nome, String Endereco, String Telefone, EstadoCivil estadoCivil){
		super();
		this.id = ID;
		this.nome = Nome;
		this.endereco = Endereco;
		this.telefone = Telefone;
		this.estadoCivil = estadoCivil;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
