package bancoDeDados;

import java.util.List; 
import java.util.Scanner;

import org.apache.log4j.Logger;

import entities.Funcionario;
import entities.Ministro;
import validadores.ValidadorDeCadastroDoSistema;
import validadores.ValidadorDeCpf;
import validadores.ValidadorDeEstadoCivil;
import validadores.ValidadorDeIdade;
import validadores.ValidadorDeNomeESobrenome;

public class CadastroMinistroBancoDeDados {

	private static final Logger logger = Logger.getLogger(CadastroMinistroBancoDeDados.class);

	Scanner sc = new Scanner(System.in);
	BancoDeDadosMinistro bancoDeDadosMinistro = new BancoDeDadosMinistro();
	ValidadorDeCpf validaCpf = new ValidadorDeCpf();
	ValidadorDeEstadoCivil validaEstadoCivil = new ValidadorDeEstadoCivil();
	ValidadorDeIdade validaIdade = new ValidadorDeIdade();
	ValidadorDeCadastroDoSistema validaCadastro = new ValidadorDeCadastroDoSistema();
	ValidadorDeNomeESobrenome validaNomeESobrenome = new ValidadorDeNomeESobrenome();

	int id;
	String nome;
	String sobrenome;
	long cpf;
	int idade;
	String estadoCivil;
	int loginCadastro;
	int senhaCadastro;
	String mensagemNome = "Cadastre o nome do Ministro: ";
	String mensagemSobrenome = "Cadastre o sobrenome do Ministro: ";
	String mensagemCpf = "Cadastre o cpf do Ministro: ";
	String mensagemIdade = "Cadastre uma idade do Ministro: ";
	String mensagemEstadoCivil = "Cadastre o Estado Civil do Ministro: ";
	String mensagemDeLoginCadastro = "Digite seu login (6 digitos): ";
	String mensagemDeSenhaCadastro = "Digite sua senha (6 digitos): ";

	public void cadastroMinistro(List<Funcionario> listaMinistro) throws Exception {
		
		boolean validaErroCatch = true;
		while (validaErroCatch) {
			Ministro ministro = new Ministro();
			boolean validaErroConfirma = true;

			logger.info("---------- CADASTRO MINISTRO ---------" + System.lineSeparator());
			
			logger.debug(mensagemNome);
			ministro.setNome(validaNomeESobrenome.validaNome(nome, mensagemNome));

			logger.debug(mensagemSobrenome);
			ministro.setSobrenome(validaNomeESobrenome.validaSobrenome(sobrenome, mensagemSobrenome));

			logger.debug(mensagemCpf);
			ministro.setCpf(validaCpf.validaCpf(cpf, mensagemCpf));

			logger.debug(mensagemIdade);
			ministro.setIdade(validaIdade.validaIdade(idade, mensagemIdade));

			logger.debug(mensagemEstadoCivil);
			ministro.setEstadoCivil(validaEstadoCivil.validaEstadoCivil(estadoCivil, mensagemEstadoCivil));

			logger.debug(mensagemDeLoginCadastro);
			ministro.setLoginDoCadastroDoSistema(
					validaCadastro.validacaoDoLoginDoCadastroDoSistema(loginCadastro, mensagemDeLoginCadastro));

			logger.debug(mensagemDeSenhaCadastro);
			ministro.setSenhaDoCadastroDoSistema(
					validaCadastro.validacaoDaSenhaDoCadastroDoSistema(senhaCadastro, mensagemDeSenhaCadastro));

			listaMinistro.add(ministro);
			bancoDeDadosMinistro.listaDeRegistroMinistro(listaMinistro, ministro);

			while (validaErroConfirma) {
				logger.debug("Confirmar dados do Ministro (y/n)?");
				char confirmaDadosMinistro = sc.next().charAt(0);

				if (confirmaDadosMinistro == 'n') {
					logger.info("Cadastrando Ministro novamente!");
					listaMinistro.remove(ministro);
					validaErroConfirma = false;
					validaErroCatch = true;
				} else if (confirmaDadosMinistro == 'y') {
					logger.info("---------- CONECTANDO AO BANCO DE DADOS ----------");
					bancoDeDadosMinistro.inserirDadosBancoMinistro(ministro);
					bancoDeDadosMinistro.pegarDadosBancoMinistro(ministro);
					validaErroConfirma = false;
					validaErroCatch = false;
				} else if (confirmaDadosMinistro != 'y' && confirmaDadosMinistro != 'n') {
					logger.debug("Por favor, insira 'y' ou 'n' para confirmar os dados do Ministro: "
							+ System.lineSeparator());
					validaErroConfirma = true;
				}
			}
		}
		logger.info("********** MINISTRO CADASTRADO COM SUCESSO! **********\n");
	}
}

