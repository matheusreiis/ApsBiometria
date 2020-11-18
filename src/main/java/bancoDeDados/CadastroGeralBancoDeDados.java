package bancoDeDados;

import java.util.List;  
import java.util.Scanner;

import org.apache.log4j.Logger;

import entities.Funcionario;
import entities.Geral;
import validadores.ValidadorDeCadastroDoSistema;
import validadores.ValidadorDeCpf;
import validadores.ValidadorDeEstadoCivil;
import validadores.ValidadorDeIdade;
import validadores.ValidadorDeNomeESobrenome;

public class CadastroGeralBancoDeDados {

	private static final Logger logger = Logger.getLogger(CadastroGeralBancoDeDados.class);

	Scanner sc = new Scanner(System.in);
	BancoDeDadosGeral bancoDeDadosGeral = new BancoDeDadosGeral();
	ValidadorDeCpf validaCpf = new ValidadorDeCpf();
	ValidadorDeEstadoCivil validaEstadoCivil = new ValidadorDeEstadoCivil();
	ValidadorDeIdade validaIdade = new ValidadorDeIdade();
	ValidadorDeCadastroDoSistema validaCadastro = new ValidadorDeCadastroDoSistema();
	ValidadorDeNomeESobrenome validaNomeESobrenome = new ValidadorDeNomeESobrenome();

	int id;
	String nome;
	String sobrenome;
	long cpf;
	String mensagem;
	int idade;
	int loginCadastro;
	int senhaCadastro;
	String estadoCivil;
	String mensagemNome = "Cadastre o nome do Funcionario Geral: ";
	String mensagemSobrenome = "Cadastre o sobrenome do Funcionario Geral: ";
	String mensagemCpf = "Cadastre o cpf do Funcionario Geral: ";
	String mensagemIdade = "Cadastre uma idade do Funcionario Geral: ";
	String mensagemEstadoCivil = "Cadastre o Estado Civil do Funcionario Geral: ";
	String mensagemDeLoginCadastro = "Digite seu login (6 numeros): ";
	String mensagemDeSenhaCadastro = "Digite sua senha (6 numeros): ";
	
	public void cadastroGeral(List<Funcionario> listaGeral) throws Exception {

		boolean validaErroCatch = true;
		while (validaErroCatch) {
			Geral geral = new Geral();
			boolean validaErroConfirma = true;

			logger.info("---------- CADASTRO FUNCIONARIO GERAL ---------" + System.lineSeparator());

			logger.debug(mensagemNome);
			geral.setNome(validaNomeESobrenome.validaNome(nome, mensagemNome));

			logger.debug(mensagemSobrenome);
			geral.setSobrenome(validaNomeESobrenome.validaSobrenome(sobrenome, mensagemSobrenome));

			logger.debug(mensagemCpf);
			geral.setCpf(validaCpf.validaCpf(cpf, mensagemCpf));

			logger.debug(mensagemIdade);
			geral.setIdade(validaIdade.validaIdade(idade, mensagemIdade));

			logger.debug(mensagemEstadoCivil);
			geral.setEstadoCivil(validaEstadoCivil.validaEstadoCivil(estadoCivil, mensagemEstadoCivil));

			logger.debug(mensagemDeLoginCadastro);
			geral.setLoginDoCadastroDoSistema(
					validaCadastro.validacaoDoLoginDoCadastroDoSistema(loginCadastro, mensagemDeLoginCadastro));

			logger.debug(mensagemDeSenhaCadastro);
			geral.setSenhaDoCadastroDoSistema(
					validaCadastro.validacaoDaSenhaDoCadastroDoSistema(senhaCadastro, mensagemDeSenhaCadastro));

			listaGeral.add(geral);
			bancoDeDadosGeral.listaDeRegistroGeral(listaGeral, geral);
			while (validaErroConfirma) {
				logger.debug("Confirmar dados do Funcionario (y/n)?");
				char confirmaDadosGeral = sc.next().charAt(0);

				if (confirmaDadosGeral == 'n') {
					logger.info("Cadastrando Funcionario Geral novamente!");
					listaGeral.remove(geral);
					validaErroConfirma = false;
					validaErroCatch = true;
				} else if (confirmaDadosGeral == 'y') {
					logger.info("---------- CONECTANDO AO BANCO DE DADOS ----------");
					bancoDeDadosGeral.inserirDadosBancoGeral(geral);
					bancoDeDadosGeral.pegarDadosBancoGeral(geral);
					validaErroConfirma = false;
					validaErroCatch = false;
				} else if (confirmaDadosGeral != 'y' && confirmaDadosGeral != 'n') {
					logger.debug("Por favor, insira 'y' ou 'n' para confirmar os dados do Funcionario Geral: "
							+ System.lineSeparator());
					validaErroConfirma = true;
				}
			}
		}
		logger.info("********** FUNCIONARIO GERAL CADASTRADO COM SUCESSO! **********\n");
	}
}

