package principal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

import bancoDeDados.BancoDeDadosDiretor;
import bancoDeDados.BancoDeDadosGeral;
import bancoDeDados.BancoDeDadosMinistro;
import bancoDeDados.CadastroDiretorBancoDeDados;
import bancoDeDados.CadastroGeralBancoDeDados;
import bancoDeDados.CadastroMinistroBancoDeDados;
import entities.Diretor;
import entities.Funcionario;
import entities.Geral;
import entities.Ministro;
import sistemas.AutenticacaoSistema;

public class PrincipalMain {

	private final static Logger logger = Logger.getLogger(PrincipalMain.class);
	static Ministro ministro;
	static Diretor diretor;
	static Geral geral;

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Matheus\\eclipse-workspace\\ApsBiometria\\src\\main\\resources\\dados.properties");
		props.load(file);
		return props;
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		CadastroMinistroBancoDeDados cadastrarMinistro = new CadastroMinistroBancoDeDados();
		CadastroDiretorBancoDeDados cadastrarDiretor = new CadastroDiretorBancoDeDados();
		CadastroGeralBancoDeDados cadastrarGeral = new CadastroGeralBancoDeDados();
		AutenticacaoSistema autenticaSistema = new AutenticacaoSistema();
		BancoDeDadosMinistro bancoDeDadosMinistro = new BancoDeDadosMinistro();
		BancoDeDadosDiretor bancoDeDadosDiretor = new BancoDeDadosDiretor();
		BancoDeDadosGeral bancoDeDadosGeral = new BancoDeDadosGeral();
		List<Funcionario> listaMinistro = new ArrayList<>();
		List<Funcionario> listaDiretor = new ArrayList<>();
		List<Funcionario> listaGeral = new ArrayList<>();

		int loginAutenticacao = 0;
		int senhaAutenticacao = 0;
		boolean validaErroCatch = true;

		logger.info("BEM VINDO AO SISTEMA DA EMPRESA SISTEMA DE AUTENTICACAO BIOMETRICA!" + System.lineSeparator());

		while (validaErroCatch) {
			logger.debug(
					"---------- Digite o Seu Cargo ----------\n \n 1 - Ministro\n 2 - Diretor\n 3 - Geral\n 4 - Cadastrar Funcionario\n 5 - Encerrar Sessao!");
			try {
				int acaoLobby = sc.nextInt();

				for (int i = acaoLobby; i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i > 5; i++) {

					if (acaoLobby == 1) {
						logger.info("---------- CONTA MINISTRO ----------" + System.lineSeparator());

						boolean validaErroMinistro = true;
						while (validaErroMinistro) {
							if (listaMinistro.size() != 0) {
								bancoDeDadosMinistro.listaDeRegistroMinistro(listaMinistro, ministro);
								validaErroMinistro = false;
							} else {
								logger.info(" Contas Ministros ativas:\n ");
								bancoDeDadosMinistro.mostrarDadosBancoMinistro(ministro);
								try {
									logger.info("Solicite um sistema Ministro para entrar: ");

									int acaoLobbyMinistro = sc.nextInt();
									autenticaSistema.autenticaSistemaMinistro(loginAutenticacao, senhaAutenticacao,
											acaoLobbyMinistro, listaMinistro);
								} catch (Exception e) {
									logger.error("#### Comando invalido, por favor insira apenas numeros! ####"
											+ System.lineSeparator());
									sc.next();
									validaErroMinistro = true;
								}
							}
						}
						break;
					} else if (acaoLobby == 2) {
						logger.info("---------- CONTA DIRETOR ----------" + System.lineSeparator());

						boolean validaErroDiretor = true;
						while (validaErroDiretor) {
							if (listaDiretor.size() == 0) {
								bancoDeDadosDiretor.listaDeRegistroDiretor(listaDiretor, diretor);
								validaErroDiretor = false;
							} else {
								logger.info(" Contas Diretores ativas:\n ");
								bancoDeDadosDiretor.mostrarDadosBancoDiretor();
								try {
									logger.info("Solicite um sistema Diretor para entrar: ");

									int acaoLobbyDiretor = sc.nextInt();
									autenticaSistema.autenticaSistemaDiretor(loginAutenticacao, senhaAutenticacao, acaoLobbyDiretor, listaDiretor);
								} catch (Exception e) {
									logger.error("#### Comando invalido, por favor insira apenas numeros! ####"
											+ System.lineSeparator());
									sc.next();
									validaErroDiretor = true;
								}
							}
						}
						break;
					} else if (acaoLobby == 3) {
						logger.info("---------- CONTA GERAL ----------" + System.lineSeparator());

						boolean validaErroGeral = true;
						while (validaErroGeral) {
							if (listaGeral.size() == 0) {
								bancoDeDadosGeral.listaDeRegistroGeral(listaGeral, geral);
								validaErroGeral = false;
							} else {
								logger.info(" Contas Gerais ativas:\n ");
								bancoDeDadosGeral.mostrarDadosBancoGeral();
								try {
									logger.info("Solicite um sistema Geral para entrar: ");

									int acaoLobbyGeral = sc.nextInt();
									autenticaSistema.autenticaSistemaGeral(loginAutenticacao, senhaAutenticacao, acaoLobbyGeral, listaGeral);
								} catch (Exception e) {
									logger.error("#### Comando invalido, por favor insira apenas numeros! ####"
											+ System.lineSeparator());
									sc.next();
									validaErroGeral = true;
								}
							}
						}
						break;
					} else if (acaoLobby == 4) {

						logger.info("---------- CADASTRO DE FUNCIONARIO ----------" + System.lineSeparator());
						logger.debug("Digite o cargo do funcionario:\n 1.Ministro / 2.Diretor / 3.Geral ");
						int cargoCadastro = sc.nextInt();

						if (cargoCadastro == 1) {
							cadastrarMinistro.cadastroMinistro(listaMinistro);

						} else if (cargoCadastro == 2) {
							cadastrarDiretor.cadastroDiretor(listaDiretor);

						} else if (cargoCadastro == 3) {
							cadastrarGeral.cadastroGeral(listaGeral);
						}
						break;
					} else if (acaoLobby == 5) {
						logger.info("Encerrando Sessao!" + "\n." + "\n." + "\n.");
						logger.info("Sessao Encerrada!");
						System.exit(0);
					} else if (acaoLobby > 5) {
						logger.error("\n### Comando Invalido ### Por favor insira um valor valido! "
								+ System.lineSeparator());
						break;
					}
				}
			} catch (Exception e) {
				logger.error("##### Erro! Reconectando ao sistema ##### " + "\n." + "\n." + "\n.");
				logger.info("Sistema reconectado!");
				sc.nextLine();
				validaErroCatch = true;
			}
		}
		sc.close();
	}
}
