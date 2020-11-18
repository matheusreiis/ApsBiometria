package entities;


public abstract class Funcionario {

	int id;
	String nome;
	String sobrenome;
	long cpf;
	int idade;
	String estadoCivil;
	int loginDoCadastroDoSistema;
	int senhaDoCadastroDoSistema;

	public String getNome() {
		return nome;
	}

	public String setNome(String nome) {
		return this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public int getLoginDoCadastroDoSistema() {
		return loginDoCadastroDoSistema;
	}

	public void setLoginDoCadastroDoSistema(int loginDoCadastroDoSistema) {
		this.loginDoCadastroDoSistema = loginDoCadastroDoSistema;
	}

	public int getSenhaDoCadastroDoSistema() {
		return senhaDoCadastroDoSistema;
	}

	public void setSenhaDoCadastroDoSistema(int senhaDoCadastroDoSistema) {
		this.senhaDoCadastroDoSistema = senhaDoCadastroDoSistema;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
