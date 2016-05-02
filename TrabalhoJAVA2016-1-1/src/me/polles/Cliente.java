package me.polles;

// Annotation para informar o nome da tabela que corresponde a essa classe...
@Tabela("CLIENTE")
public class Cliente {
	
	
	//Atributos da Classe e a annotation para informar se é a PK ou se é um outro campo e seu devido nome...
	@Coluna(pk=true)
	private int id;
	
	@Coluna(nome="NOME", tamanho=150)
	private String nome;
	
	@Coluna(nome="ENDERECO", tamanho=150)
	private String endereco;
	
	@Coluna(nome="TELEFONE", tamanho=15)
	private String telefone;

	@Coluna(nome="ESTADOCIVIL")
	private EstadoCivil estadoCivil;

	//Função para instanciar o objeto sem os dados...
	public Cliente() {
		this(0, null, null, null, null);
	}
	
	// Função para instanciar com os dados necessários...
	public Cliente(int ID, String Nome, String Endereco, String Telefone, EstadoCivil estadoCivil){
		super();
		this.id = ID;
		this.nome = Nome;
		this.endereco = Endereco;
		this.telefone = Telefone;
		this.estadoCivil = estadoCivil;
	}
	

	//Getters and setters de cada atributo
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
