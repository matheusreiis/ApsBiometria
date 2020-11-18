package bancoDeDados;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import entities.Funcionario;
import entities.Ministro;

public class BancoDeDadosMinistro {

	Logger logger = Logger.getLogger(BancoDeDadosMinistro.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	ConexaoBancoDeDados conexaoBancoDeDados = new ConexaoBancoDeDados();

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Matheus\\eclipse-workspace\\ApsBiometria\\src\\main\\resources\\dados.properties");
		props.load(file);
		return props;
	}

	public void listaDeRegistroMinistro(List<Funcionario> listaMinistro, Ministro Ministro)
			throws IOException {

			if (listaMinistro.size() == 0) {
				logger.info("Nao ha Ministro cadastrados!");
				logger.info("Retornando ao lobby." + "\n." + "\n." + "\n.");
			} else {
				logger.info("--------- DADOS DO MINISTRO ---------" + System.lineSeparator());
				logger.info("Nome do Ministro: " + Ministro.getNome() + " " + Ministro.getSobrenome());
				logger.info("cpf do Ministro: " + Ministro.getCpf());
				logger.info("Idade do Ministro: " + Ministro.getIdade());
				logger.info("Estado Civil do Ministro: " + Ministro.getEstadoCivil());
				logger.info("Login do Ministro: " + Ministro.getLoginDoCadastroDoSistema());
				logger.info("Senha do Ministro: **************" + System.lineSeparator());
			}
		}

	public void inserirDadosBancoMinistro(Ministro Ministro) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.inserirDadosListaMinistro"));

			stmt.setString(1, Ministro.getNome() + " " + Ministro.getSobrenome());
			stmt.setLong(2, Ministro.getCpf());
			stmt.setInt(3, Ministro.getIdade());
			stmt.setString(4, Ministro.getEstadoCivil());
			stmt.setInt(5, Ministro.getLoginDoCadastroDoSistema());
			stmt.setInt(6, Ministro.getSenhaDoCadastroDoSistema());
			stmt.execute();

			logger.info(
					"########## Inserindo dados ao banco de dados de funcionarios ##########" + "\n." + "\n." + "\n.");

			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar adicionar dados do Ministro no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}

	public void pegarDadosBancoMinistro(Ministro ministro) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaMinistro"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ministro.setId(rs.getInt("id"));
			}
			logger.info("########## Gerando seu id automatico ##########" + "\n." + "\n." + "\n.");
			logger.info("Seu novo id do Ministro (use seu id para se conectar ao sistema): " + ministro.getId());
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Ministro no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}

	public void mostrarDadosBancoMinistro(Ministro ministro) throws IOException {
		
		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaMinistro"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				logger.info(rs.getInt("id") + " - " + rs.getString("nome"));
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Ministro no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}
}
