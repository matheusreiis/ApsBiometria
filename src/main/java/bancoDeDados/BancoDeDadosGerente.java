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
import entities.Gerente;

public class BancoDeDadosGerente {

	Logger logger = Logger.getLogger(BancoDeDadosGerente.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	ConexaoBancoDeDados conexaoBancoDeDados = new ConexaoBancoDeDados();

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\DataCore\\eclipse-workspace\\FuncionarioBanco\\src\\main\\resources\\dados.properties");
		props.load(file);
		return props;
	}

	public void listaDeRegistroGerente(List<Funcionario> listaGerente, Gerente gerente)
			throws IOException {

			if (listaGerente.size() == 0) {
				logger.info("Nao ha Gerentes cadastrados!");
				logger.info("Retornando ao lobby." + "\n." + "\n." + "\n.");
			} else {
				logger.info("--------- DADOS DO GERENTE ---------" + System.lineSeparator());
				logger.info("Nome do Gerente: " + gerente.getNome() + " " + gerente.getSobrenome());
				logger.info("cpf do Gerente: " + gerente.getCpf());
				logger.info("Salario do Gerente: R$" + formatter.format(gerente.getSalario()));
				logger.info("Idade do Gerente: " + gerente.getIdade());
				logger.info("Estado Civil do Gerente: " + gerente.getEstadoCivil());
				logger.info("Login do Gerente: " + gerente.getLoginDoCadastroDoSistema());
				logger.info("Senha do Gerente: **************" + System.lineSeparator());
			}
		}

	public void inserirDadosBancoGerente(Gerente gerente) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.inserirDadosListaGerente"));

			stmt.setString(1, gerente.getNome() + " " + gerente.getSobrenome());
			stmt.setLong(2, gerente.getCpf());
			stmt.setDouble(3, gerente.getSalario());
			stmt.setInt(4, gerente.getIdade());
			stmt.setString(5, gerente.getEstadoCivil());
			stmt.setInt(6, gerente.getLoginDoCadastroDoSistema());
			stmt.setInt(7, gerente.getSenhaDoCadastroDoSistema());
			stmt.execute();

			logger.info(
					"########## Inserindo dados ao banco de dados de funcionarios ##########" + "\n." + "\n." + "\n.");

			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar adicionar dados do Gerente no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}

	public void pegarDadosBancoGerente(Gerente gerente) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaGerente"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				gerente.setId(rs.getInt("id"));
			}
			logger.info("########## Gerando seu id automatico ##########" + "\n." + "\n." + "\n.");
			logger.info("Seu novo id do Gerente (use seu id para se conectar ao sistema): " + gerente.getId());
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Gerente no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}

	public void excluirDadosBancoGerente() {

	}
	
	public void mostrarDadosBancoGerente(Gerente gerente) throws IOException {
		
		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaGerente"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				logger.info(rs.getInt("id") + " - " + rs.getString("nome"));
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Gerente no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}
}
