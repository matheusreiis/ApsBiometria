package bancoDeDados;

import java.util.List; 
import java.util.Scanner;

import org.apache.log4j.Logger;

import entities.Diretor;
import entities.Funcionario;
import validadores.ValidadorDeCadastroDoSistema;
import validadores.ValidadorDeCpf;
import validadores.ValidadorDeEstadoCivil;
import validadores.ValidadorDeIdade;
import validadores.ValidadorDeNomeESobrenome;

public class CadastroDiretorBancoDeDados {

	private static final Logger logger = Logger.getLogger(CadastroDiretorBancoDeDados.class);

	Scanner sc = new Scanner(System.in);
	BancoDeDadosDiretor bancoDeDadosDiretor = new BancoDeDadosDiretor();
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
	String mensagemNome = "Cadastre o nome do Diretor: ";
	String mensagemSobrenome = "Cadastre o sobrenome do Diretorr: ";
	String mensagemCpf = "Cadastre o cpf do Diretor: ";
	String mensagemIdade = "Cadastre uma idade do Diretor: ";
	String mensagemEstadoCivil = "Cadastre o Estado Civil do Diretor: ";
	String mensagemDeLoginCadastro = "Digite seu login (6 numeros): ";
	String mensagemDeSenhaCadastro = "Digite sua senha (6 numeros): ";
	
	public void cadastroDiretor(List<Funcionario> listaDiretor) throws Exception {

		boolean validaErroCatch = true;
		while (validaErroCatch) {
			Diretor diretor = new Diretor();
			boolean validaErroConfirma = true;

			logger.info("---------- CADASTRO DIRETOR ---------" + System.lineSeparator());

			logger.debug(mensagemNome);
			diretor.setNome(validaNomeESobrenome.validaNome(nome, mensagemNome));

			logger.debug(mensagemSobrenome);
			diretor.setSobrenome(validaNomeESobrenome.validaSobrenome(sobrenome, mensagemSobrenome));

			logger.debug(mensagemCpf);
			diretor.setCpf(validaCpf.validaCpf(cpf, mensagemCpf));

			logger.debug(mensagemIdade);
			diretor.setIdade(validaIdade.validaIdade(idade, mensagemIdade));

			logger.debug(mensagemEstadoCivil);
			diretor.setEstadoCivil(validaEstadoCivil.validaEstadoCivil(estadoCivil, mensagemEstadoCivil));

			logger.debug(mensagemDeLoginCadastro);
			diretor.setLoginDoCadastroDoSistema(
					validaCadastro.validacaoDoLoginDoCadastroDoSistema(loginCadastro, mensagemDeLoginCadastro));

			logger.debug(mensagemDeSenhaCadastro);
			diretor.setSenhaDoCadastroDoSistema(
					validaCadastro.validacaoDaSenhaDoCadastroDoSistema(senhaCadastro, mensagemDeSenhaCadastro));

			listaDiretor.add(diretor);
			bancoDeDadosDiretor.listaDeRegistroDiretor(listaDiretor, diretor);

			while (validaErroConfirma) {
				logger.debug("Confirmar dados do Funcionario (y/n)?");
				char confirmaDadosDiretor = sc.next().charAt(0);

				if (confirmaDadosDiretor == 'n') {
					logger.info("Cadastrando Diretor novamente!");
					listaDiretor.remove(diretor);
					validaErroConfirma = false;
					validaErroCatch = true;
				} else if (confirmaDadosDiretor == 'y') {
					logger.info("---------- CONECTANDO AO BANCO DE DADOS ----------");
					bancoDeDadosDiretor.inserirDadosBancoDiretor(diretor);
					bancoDeDadosDiretor.pegarDadosBancoDiretor(diretor);
					validaErroConfirma = false;
					validaErroCatch = false;
				} else if (confirmaDadosDiretor != 'y' && confirmaDadosDiretor != 'n') {
					logger.debug("Por favor, insira 'y' ou 'n' para confirmar os dados do Diretor: "
							+ System.lineSeparator());
					validaErroConfirma = true;
				}
			}
		}
		logger.info("********** DIRETOR CADASTRADO COM SUCESSO! **********\n");
	}
}
