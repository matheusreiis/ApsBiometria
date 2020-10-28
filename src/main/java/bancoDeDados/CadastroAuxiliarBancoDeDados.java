package bancoDeDados;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import entities.Auxiliar;
import entities.Funcionario;
import validadores.ValidadorDeCadastroDoSistema;
import validadores.ValidadorDeCpf;
import validadores.ValidadorDeEstadoCivil;
import validadores.ValidadorDeId;
import validadores.ValidadorDeIdade;
import validadores.ValidadorDeSalario;

public class CadastroAuxiliarBancoDeDados {

	private static final Logger logger = Logger.getLogger(CadastroAuxiliarBancoDeDados.class);

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
	String mensagem;
	double salario;
	int idade;
	int loginCadastro;
	int senhaCadastro;
	String estadoCivil;
	String mensagemId = "Cadastre o Id do Auxiliar: ";
	String mensagemNome = "Cadastre o nome e sobrenome do Auxiliar: ";
	String mensagemCpf = "Cadastre o cpf do Auxiliar: ";
	String mensagemSalario = "Cadastre um salario do Auxiliar: ";
	String mensagemIdade = "Cadastre uma idade do Auxiliar: ";
	String mensagemEstadoCivil = "Cadastre o Estado Civil do Auxiliar: ";
	String mensagemDeLoginCadastro = "Digite seu login (6 digitos): ";
	String mensagemDeSenhaCadastro = "Digite sua senha (6 digitos): ";
	

	public void cadastroAuxiliar(List<Funcionario> listaAuxiliar) {

		Auxiliar auxiliar = new Auxiliar();

		logger.info("\n---------- CADASTRO AUXILIAR ---------" + System.lineSeparator());
		
		logger.debug(mensagemId);
		id = sc.nextInt();
		auxiliar.setId(validaId.validacaoDeId(id, mensagemId));
		
		logger.debug(mensagemNome);
		nome = sc.next();
		sobrenome = sc.next();
		auxiliar.setNome(nome);
		auxiliar.setSobrenome(sobrenome);

		logger.debug(mensagemCpf);
		auxiliar.setCpf(validaCpf.validaCpf(cpf, mensagemCpf));

		logger.debug(mensagemSalario);
		auxiliar.setSalario(validaSalario.validaSalario(salario, mensagemSalario));

		logger.debug(mensagemIdade);
		auxiliar.setIdade(validaIdade.validaIdade(idade, mensagemIdade));

		logger.debug(mensagemEstadoCivil);
		auxiliar.setEstadoCivil(validaEstadoCivil.validaEstadoCivil(estadoCivil, mensagemEstadoCivil));

		logger.debug("Cadastre um login com ate 6 digitos: ");
		loginCadastro = sc.nextInt();
		auxiliar.setLoginDoCadastroDoSistema(
				validaCadastro.validacaoDoLoginDoCadastroDoSistema(loginCadastro, mensagemDeLoginCadastro));

		logger.debug("Cadastre uma senha com ate 6 digitos: ");
		senhaCadastro = sc.nextInt();
		auxiliar.setSenhaDoCadastroDoSistema(
				validaCadastro.validacaoDaSenhaDoCadastroDoSistema(senhaCadastro, mensagemDeSenhaCadastro));

		listaAuxiliar.add(auxiliar);
		
		bancoDeDadosFuncionario.listaDeRegistroAuxiliar(listaAuxiliar);

		logger.info("\n********** AUXILIAR CADASTRADO COM SUCESSO! **********\n");
	}
}
