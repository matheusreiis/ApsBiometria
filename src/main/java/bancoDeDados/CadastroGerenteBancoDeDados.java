package bancoDeDados;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import entities.Funcionario;
import entities.Gerente;
import validadores.ValidadorDeCadastroDoSistema;
import validadores.ValidadorDeCpf;
import validadores.ValidadorDeEstadoCivil;
import validadores.ValidadorDeId;
import validadores.ValidadorDeIdade;
import validadores.ValidadorDeSalario;

public class CadastroGerenteBancoDeDados {

	private static final Logger logger = Logger.getLogger(CadastroGerenteBancoDeDados.class);

	Scanner sc = new Scanner(System.in);
	BancoDeDadosFuncionario bancoDeDadosFuncionario = new BancoDeDadosFuncionario();
	ValidadorDeCpf validaCpf = new ValidadorDeCpf();
	ValidadorDeEstadoCivil validaEstadoCivil = new ValidadorDeEstadoCivil();
	ValidadorDeIdade validaIdade = new ValidadorDeIdade();
	ValidadorDeSalario validaSalario = new ValidadorDeSalario();
	ValidadorDeCadastroDoSistema validaCadastro = new ValidadorDeCadastroDoSistema();
	ValidadorDeId validaId = new ValidadorDeId();

	int id;
	String nome;
	String sobrenome;
	long cpf;
	double salario;
	int idade;
	String estadoCivil;
	int loginCadastro;
	int senhaCadastro;
	String mensagemId = "Cadastre o Id do Gerente: ";
	String mensagemNome = "Cadastre o nome e sobrenome do Gerente: ";
	String mensagemCpf = "Cadastre o cpf do Gerente: ";
	String mensagemSalario = "Cadastre um salario do Gerente: ";
	String mensagemIdade = "Cadastre uma idade do Gerente: ";
	String mensagemEstadoCivil = "Cadastre o Estado Civil do Gerente: ";
	String mensagemDeLoginCadastro = "Digite seu login (6 digitos): ";
	String mensagemDeSenhaCadastro = "Digite sua senha (6 digitos): ";

	public void cadastroGerente(List<Funcionario> listaGerente) throws Exception {
		
		Gerente gerente = new Gerente();
		
		logger.info("---------- CADASTRO GERENTE ---------" + System.lineSeparator());
		
		logger.debug(mensagemId);
		id = sc.nextInt();
		gerente.setId(id);
		gerente.setId(validaId.validacaoDeId(id, mensagemId));

		logger.debug(mensagemNome);
		nome = sc.next();
		sobrenome = sc.next();
		gerente.setNome(nome);
		gerente.setSobrenome(sobrenome);

		logger.debug(mensagemCpf);
		cpf = sc.nextLong();
		gerente.setCpf(validaCpf.validaCpf(cpf, mensagemCpf));

		logger.debug(mensagemSalario);
		salario = sc.nextDouble();
		gerente.setSalario(validaSalario.validaSalario(salario, mensagemSalario));

		logger.debug(mensagemIdade);
		idade = sc.nextInt();
		gerente.setIdade(validaIdade.validaIdade(idade, mensagemIdade));

		logger.debug(mensagemEstadoCivil);
		gerente.setEstadoCivil(validaEstadoCivil.validaEstadoCivil(estadoCivil, mensagemEstadoCivil));

		logger.debug(mensagemDeLoginCadastro);
		loginCadastro = sc.nextInt();
		gerente.setLoginDoCadastroDoSistema(
				validaCadastro.validacaoDoLoginDoCadastroDoSistema(loginCadastro, mensagemDeLoginCadastro));

		logger.debug(mensagemDeSenhaCadastro);
		senhaCadastro = sc.nextInt();
		gerente.setSenhaDoCadastroDoSistema(
				validaCadastro.validacaoDaSenhaDoCadastroDoSistema(senhaCadastro, mensagemDeSenhaCadastro));

		listaGerente.add(gerente);
		
		bancoDeDadosFuncionario.listaDeRegistroGerente(listaGerente);
		
		logger.info("********** GERENTE CADASTRADO COM SUCESSO! **********\n");
	}
}
