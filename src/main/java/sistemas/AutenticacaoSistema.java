package sistemas;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import bancoDeDados.ConexaoBancoDeDados;
import entities.Diretor;
import entities.Funcionario;
import entities.Geral;
import entities.Ministro;
import validadores.ValidadorDeAutenticacaoDoSistema;

public class AutenticacaoSistema {

	private Logger logger = Logger.getLogger(AutenticacaoSistema.class);

	SistemaMinistro sistemaMinistro = new SistemaMinistro();
	SistemaDiretor sistemaDiretor = new SistemaDiretor();
	SistemaGeral sistemaGeral = new SistemaGeral();
	ConexaoBancoDeDados conexaoBancoDeDados = new ConexaoBancoDeDados();
	ValidadorDeAutenticacaoDoSistema validaSistema = new ValidadorDeAutenticacaoDoSistema();
	Ministro ministro;
	Diretor diretor;
	Geral geral;
	boolean validaErro = true;
	int id;
	int loginDoSistema;
	int senhaDoSistema;
	String mensagemDeLogin = "Digite seu login: ";
	String mensagemDeSenha = "Digite sua senha: ";

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Matheus\\eclipse-workspace\\ApsBiometria\\src\\main\\resources\\dados.properties");
		props.load(file);
		return props;
	}

	public void autenticaSistemaMinistro(int loginAutenticacao, int senhaAutenticacao, int acaoLobbyMinistro,
			List<Funcionario> listaMinistro) throws IOException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaMinistro"));

			ResultSet rs = stmt.executeQuery();
			while (validaErro) {
				rs.next();
				id = rs.getInt("id");

				if (acaoLobbyMinistro == id) {
					logger.info("---------- AUTENTICANDO SISTEMA MINISTRO ----------" + "\n." + "\n." + "\n.");
					logger.info("Bem vindo(a) novamente Sr. " + rs.getString("nome"));

					logger.info(mensagemDeLogin);
					loginAutenticacao = validaSistema.validacaoDoLoginDoSistema(loginAutenticacao, mensagemDeLogin);

					logger.info(mensagemDeSenha);
					senhaAutenticacao = validaSistema.validacaoDaSenhaDoSistema(senhaAutenticacao, mensagemDeSenha);

					validaErro = false;
				}
			}
			validaErro = true;
			while (validaErro) {
				rs.next();
				loginDoSistema = rs.getInt("Login_do_Sistema");
				senhaDoSistema = rs.getInt("Senha_do_Sistema");

				if (loginAutenticacao == loginDoSistema && senhaAutenticacao == senhaDoSistema) {
					sistemaMinistro.sistemaMinistro();
					validaErro = false;
				} else {
					logger.error("########## LOGIN INCORRETO ##########");
					logger.error("Desconectando do sistema!" + "\n." + "\n." + "\n.");
					logger.error("Desconectado!" + System.lineSeparator());
					break;
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("#### Id inexistente, por favor insira um id existente para entrar no sistema! ####"
					+ System.lineSeparator());
		}
	}

	public void autenticaSistemaDiretor(int loginAutenticacao, int senhaAutenticacao, int acaoLobbyDiretor,
			List<Funcionario> listaAuxiliar) throws IOException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaDiretor"));

			ResultSet rs = stmt.executeQuery();
			while (validaErro) {
				rs.next();
				id = rs.getInt("id");

				if (acaoLobbyDiretor == id) {
					logger.info("---------- AUTENTICANDO SISTEMA DIRETOR ----------" + "\n." + "\n." + "\n.");
					logger.info("Bem vindo(a) novamente Sr. " + rs.getString("nome"));

					logger.info(mensagemDeLogin);
					loginAutenticacao = validaSistema.validacaoDoLoginDoSistema(loginAutenticacao, mensagemDeLogin);

					logger.info(mensagemDeSenha);
					senhaAutenticacao = validaSistema.validacaoDaSenhaDoSistema(senhaAutenticacao, mensagemDeSenha);

					validaErro = false;
				}
			}
			validaErro = true;
			while (validaErro) {
				rs.next();
				loginDoSistema = rs.getInt("Login_do_Sistema");
				senhaDoSistema = rs.getInt("Senha_do_Sistema");

				if (loginAutenticacao == loginDoSistema && senhaAutenticacao == senhaDoSistema) {
					sistemaDiretor.sistemaDiretor();
					validaErro = false;
				} else {
					logger.error("########## LOGIN INCORRETO ##########");
					logger.error("Desconectando do sistema!" + "\n." + "\n." + "\n.");
					logger.error("Desconectado!" + System.lineSeparator());
					break;
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("#### Id inexistente, por favor insira um id existente para entrar no sistema! ####"
					+ System.lineSeparator());
		}
	}

	public void autenticaSistemaGeral(int loginAutenticacao, int senhaAutenticacao, int acaoLobbyGeral,
			List<Funcionario> listaEstagiario) throws IOException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaGeral"));

			ResultSet rs = stmt.executeQuery();
			while (validaErro) {
				rs.next();
				id = rs.getInt("id");

				if (acaoLobbyGeral == id) {
					logger.info("---------- AUTENTICANDO SISTEMA GERAL ----------" + "\n." + "\n." + "\n.");
					logger.info("Bem vindo(a) novamente Sr. " + rs.getString("nome"));

					logger.info(mensagemDeLogin);
					loginAutenticacao = validaSistema.validacaoDoLoginDoSistema(loginAutenticacao, mensagemDeLogin);

					logger.info(mensagemDeSenha);
					senhaAutenticacao = validaSistema.validacaoDaSenhaDoSistema(senhaAutenticacao, mensagemDeSenha);

					validaErro = false;
				}
			}
			validaErro = true;
			while (validaErro) {
				rs.next();
				loginDoSistema = rs.getInt("Login_do_Sistema");
				senhaDoSistema = rs.getInt("Senha_do_Sistema");

				if (loginAutenticacao == loginDoSistema && senhaAutenticacao == senhaDoSistema) {
					sistemaGeral.sistemaGeral();
					validaErro = false;
				} else {
					logger.error("########## LOGIN INCORRETO ##########");
					logger.error("Desconectando do sistema!" + "\n." + "\n." + "\n.");
					logger.error("Desconectado!" + System.lineSeparator());
					break;
				}
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("#### Id inexistente, por favor insira um id existente para entrar no sistema! ####"
					+ System.lineSeparator());
		}
	}
}
