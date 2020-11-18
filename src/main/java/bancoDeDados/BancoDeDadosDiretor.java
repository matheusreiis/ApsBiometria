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

import entities.Diretor;
import entities.Funcionario;

public class BancoDeDadosDiretor {

	ConexaoBancoDeDados conexaoBancoDeDados = new ConexaoBancoDeDados();
	Logger logger = Logger.getLogger(BancoDeDadosDiretor.class);
	NumberFormat formatter = new DecimalFormat("#0.00");

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Matheus\\eclipse-workspace\\ApsBiometria\\src\\main\\resources\\dados.properties");
		props.load(file);
		return props;
	}

	public void listaDeRegistroDiretor(List<Funcionario> listaDiretor, Diretor diretor) throws IOException {

		if (listaDiretor.size() == 0) {
			logger.info("Nao ha Diretores cadastrados!");
			logger.info("Retornando ao lobby." + "\n." + "\n." + "\n.");
		} else {
			logger.info("--------- DADOS DO DIRETOR ---------" + System.lineSeparator());
			logger.info("Nome do Diretor: " + diretor.getNome() + " " + diretor.getSobrenome());
			logger.info("cpf do Diretor: " + diretor.getCpf());
			logger.info("Idade do Diretor: " + diretor.getIdade());
			logger.info("Estado Civil do Diretor: " + diretor.getEstadoCivil());
			logger.info("Login do Diretor: " + diretor.getLoginDoCadastroDoSistema());
			logger.info("Senha do Diretor: **************" + System.lineSeparator());
		}
	}

	public void inserirDadosBancoDiretor(Diretor diretor) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = new ConexaoBancoDeDados().conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.inserirDadosListaDiretor"));

			stmt.setString(1, diretor.getNome() + " " + diretor.getSobrenome());
			stmt.setLong(2, diretor.getCpf());
			stmt.setInt(3, diretor.getIdade());
			stmt.setString(4, diretor.getEstadoCivil());
			stmt.setInt(5, diretor.getLoginDoCadastroDoSistema());
			stmt.setInt(6, diretor.getSenhaDoCadastroDoSistema());
			stmt.execute();
			
			logger.info(
					"########## Inserindo dados ao banco de dados de funcionarios ##########" + "\n." + "\n." + "\n.");

			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar adicionar dados do Diretor no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}
	
	public void pegarDadosBancoDiretor(Diretor diretor) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaDiretor"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				diretor.setId(rs.getInt("id"));
			}
			logger.info("########## Gerando seu id automatico ##########" + "\n." + "\n." + "\n.");
			logger.info("Seu novo id do Diretor (use seu id para se conectar ao sistema): " + diretor.getId());
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Diretor no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}
	
	public void mostrarDadosBancoDiretor() throws IOException {
		
		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaDiretor"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				logger.info(rs.getInt("id") + " - " + rs.getString("nome"));
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Diretor no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}
}

