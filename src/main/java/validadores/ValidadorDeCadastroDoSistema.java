package validadores;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

import bancoDeDados.ConexaoBancoDeDados;
import interfaces.ICadastroDoSistema;

public class ValidadorDeCadastroDoSistema implements ICadastroDoSistema {

	ConexaoBancoDeDados conexaoBancoDeDados = new ConexaoBancoDeDados();
	private static final Logger logger = Logger.getLogger(ValidadorDeCadastroDoSistema.class);
	Scanner sc = new Scanner(System.in);
	int loginBanco;

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Matheus\\eclipse-workspace\\ApsBiometria\\src\\main\\resources\\dados.properties");
		props.load(file);
		return props;
	}

	public int validacaoDoLoginDoCadastroDoSistema(int loginCadastro, String mensagemDeLoginCadastro)
			throws IOException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		boolean validaErroCatch = true;
		while (validaErroCatch) {
			validaErroCatch = false;
			try {
				PreparedStatement stmtMinistro = connection
						.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaMinistro"));
				PreparedStatement stmtDiretor = connection
						.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaDiretor"));
				PreparedStatement stmtGeral = connection
						.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaGeral"));

				loginCadastro = sc.nextInt();

				ResultSet rsMinistro = stmtMinistro.executeQuery();
				ResultSet rsDiretor = stmtDiretor.executeQuery();
				ResultSet rsGeral = stmtGeral.executeQuery();
				if (rsMinistro.next()) {
					loginBanco = rsMinistro.getInt("Login_do_Sistema");
				}
				if (rsDiretor.next()) {
					loginBanco = rsDiretor.getInt("Login_do_Sistema");
				}
				if (rsGeral.next()) {
					loginBanco = rsGeral.getInt("Login_do_Sistema");
				}

				if (loginCadastro != loginBanco) {
					String valueOf = "";
					valueOf = extracted(loginCadastro, loginCadastro);
					while (valueOf.length() != 6) {
						logger.debug("##### Por favor insira 6 digitos para seu login. #####" + System.lineSeparator());
						logger.info(mensagemDeLoginCadastro);
						valueOf = extracted(loginCadastro, loginCadastro);
						validaErroCatch = true;
						break;
					}
				} else {
					logger.error("##### LOGIN JÁ EXISTENTE ##### Por favor utilize outro login para cadastro! "
							+ System.lineSeparator());
					sc.nextLine();
					logger.info(mensagemDeLoginCadastro);
					validaErroCatch = true;
				}
			} catch (Exception e) {
				logger.error("##### VALOR INVALIDO ##### Por favor insira um valor valido! " + System.lineSeparator());
				sc.nextLine();
				logger.info(mensagemDeLoginCadastro);
				validaErroCatch = true;
			}
		}
		return loginCadastro;
	}

	public int validacaoDaSenhaDoCadastroDoSistema(int senhaCadastro, String mensagemDeSenhaCadastro) {
		boolean validaErroCatch = true;
		while (validaErroCatch) {
			try {
				senhaCadastro = sc.nextInt();
				String valueOf = "";
				valueOf = extracted(senhaCadastro, senhaCadastro);
				while (valueOf.length() != 6) {
					logger.debug(
							"\n##### Por favor insira 6 digitos para sua nova senha. #####" + System.lineSeparator());
					logger.info(mensagemDeSenhaCadastro);
					senhaCadastro = sc.nextInt();
					valueOf = extracted(senhaCadastro, senhaCadastro);
				}
				validaErroCatch = false;
			} catch (Exception e) {
				logger.debug("##### SENHA INVALIDA ##### Por favor insira uma senha valida! " + System.lineSeparator());
				sc.nextLine();
				logger.info(mensagemDeSenhaCadastro);
				validaErroCatch = true;
			}
		}
		return senhaCadastro;
	}

	private String extracted(int loginCadastro, int senhaCadastro) {
		String valueOf;
		valueOf = String.valueOf(loginCadastro);
		valueOf = String.valueOf(senhaCadastro);
		valueOf.length();
		return valueOf;
	}

}
