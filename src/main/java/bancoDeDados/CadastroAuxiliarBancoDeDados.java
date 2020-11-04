package bancoDeDados;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import arquivos.ArquivoDeAuxiliar;
import entities.Auxiliar;
import entities.Funcionario;
import util.GeradorDeId;
import validadores.ValidadorDeCadastroDoSistema;
import validadores.ValidadorDeCpf;
import validadores.ValidadorDeEstadoCivil;
import validadores.ValidadorDeId;
import validadores.ValidadorDeIdade;
import validadores.ValidadorDeNomeESobrenome;
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
	ValidadorDeNomeESobrenome validaNomeESobrenome = new ValidadorDeNomeESobrenome();
	GeradorDeId statusId = new GeradorDeId();
	ArquivoDeAuxiliar arquivoAuxiliar = new ArquivoDeAuxiliar();

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
	String mensagemNome = "Cadastre o nome do Auxiliar: ";
	String mensagemSobrenome = "Cadastre o sobrenome do Auxiliar: ";
	String mensagemCpf = "Cadastre o cpf do Auxiliar: ";
	String mensagemSalario = "Cadastre um salario do Auxiliar: ";
	String mensagemIdade = "Cadastre uma idade do Auxiliar: ";
	String mensagemEstadoCivil = "Cadastre o Estado Civil do Auxiliar: ";
	String mensagemDeLoginCadastro = "Digite seu login (6 numeros): ";
	String mensagemDeSenhaCadastro = "Digite sua senha (6 numeros): ";
	boolean validaErroCatch = true;
	boolean validaErroConfirma = true;

	public void cadastroAuxiliar(List<Funcionario> listaAuxiliar) throws Exception {

		while (validaErroCatch) {
			Auxiliar auxiliar = new Auxiliar();

			logger.info("---------- CADASTRO AUXILIAR ---------" + System.lineSeparator());

			auxiliar.setId(statusId.gerarId());

			logger.debug(mensagemNome);
			nome = sc.next();
			auxiliar.setNome(validaNomeESobrenome.validaNome(nome, mensagemNome));

			logger.debug(mensagemSobrenome);
			sobrenome = sc.next();
			auxiliar.setSobrenome(validaNomeESobrenome.validaSobrenome(sobrenome, mensagemSobrenome));

			logger.debug(mensagemCpf);
			auxiliar.setCpf(validaCpf.validaCpf(cpf, mensagemCpf));

			logger.debug(mensagemSalario);
			salario = sc.nextDouble();
			auxiliar.setSalario(validaSalario.validaSalario(salario, mensagemSalario));

			logger.debug(mensagemIdade);
			idade = sc.nextInt();
			auxiliar.setIdade(validaIdade.validaIdade(idade, mensagemIdade));

			logger.debug(mensagemEstadoCivil);
			estadoCivil = sc.next();
			auxiliar.setEstadoCivil(validaEstadoCivil.validaEstadoCivil(estadoCivil, mensagemEstadoCivil));

			logger.debug(mensagemDeLoginCadastro);
			loginCadastro = sc.nextInt();
			auxiliar.setLoginDoCadastroDoSistema(
					validaCadastro.validacaoDoLoginDoCadastroDoSistema(loginCadastro, mensagemDeLoginCadastro));

			logger.debug(mensagemDeSenhaCadastro);
			senhaCadastro = sc.nextInt();
			auxiliar.setSenhaDoCadastroDoSistema(
					validaCadastro.validacaoDaSenhaDoCadastroDoSistema(senhaCadastro, mensagemDeSenhaCadastro));

			listaAuxiliar.add(auxiliar);
			bancoDeDadosFuncionario.listaDeRegistroAuxiliar(listaAuxiliar);

			logger.debug("Confirmar dados do Funcionario (y/n)?");
			char confirmaDadosAuxiliar = sc.next().charAt(0);

			while (validaErroConfirma) {
				if (confirmaDadosAuxiliar == 'n') {
					logger.info("Cadastrando Auxiliar novamente!");
					listaAuxiliar.remove(auxiliar);
					auxiliar.setId(statusId.removeId());
					validaErroCatch = true;
				} else if (confirmaDadosAuxiliar == 'y') {
					arquivoAuxiliar.listaDeAuxiliaresAtivos(listaAuxiliar);
					validaErroConfirma = false;
					validaErroCatch = false;
				} else if (confirmaDadosAuxiliar != 'y' && confirmaDadosAuxiliar != 'n') {
					logger.debug("Por favor, insira 'y' ou 'n' para confirmar os dados do Auxiliar: "
							+ System.lineSeparator());
					validaErroCatch = true;
				}
			}
		}
		logger.info("********** AUXILIAR CADASTRADO COM SUCESSO! **********\n");
	}
}
