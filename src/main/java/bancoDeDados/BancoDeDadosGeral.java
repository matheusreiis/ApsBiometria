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
import entities.Geral;

public class BancoDeDadosGeral {

	Logger logger = Logger.getLogger(BancoDeDadosGeral.class);
	ConexaoBancoDeDados conexaoBancoDeDados = new ConexaoBancoDeDados();
	NumberFormat formatter = new DecimalFormat("#0.00");

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Matheus\\eclipse-workspace\\ApsBiometria\\src\\main\\resources\\dados.properties");
		props.load(file);
		return props;
	}

	public void listaDeRegistroGeral(List<Funcionario> listaGeral, Geral geral) throws IOException{

		if (listaGeral.size() == 0) {
			logger.info("Nao ha Funcionarios Gerais cadastrados!");
			logger.info("Retornando ao lobby." + "\n." + "\n." + "\n.");
		} else {
			logger.info("--------- DADOS DO FUNCIONARIOS GERAIS -------	--" + System.lineSeparator());
			logger.info("Nome do Funcionario: " + geral.getNome() + " " + geral.getSobrenome());
			logger.info("cpf do Funcionario: " + geral.getCpf());
			logger.info("Idade do Funcionario: " + geral.getIdade());
			logger.info("Estado Civil do Funcionario: " + geral.getEstadoCivil());
			logger.info("Login do Funcionario: " + geral.getLoginDoCadastroDoSistema());
			logger.info("Senha do Funcionario: **************" + System.lineSeparator());
		}
	}

	public void inserirDadosBancoGeral(Geral geral) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = new ConexaoBancoDeDados().conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.inserirDadosListaGeral"));

			stmt.setString(1, geral.getNome() + " " + geral.getSobrenome());
			stmt.setLong(2, geral.getCpf());
			stmt.setInt(3, geral.getIdade());
			stmt.setString(4, geral.getEstadoCivil());
			stmt.setInt(5, geral.getLoginDoCadastroDoSistema());
			stmt.setInt(6, geral.getSenhaDoCadastroDoSistema());
			stmt.execute();
			
			logger.info(
					"########## Inserindo dados ao banco de dados de funcionarios ##########" + "\n." + "\n." + "\n.");
			
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar adicionar dados do Funcionario Geral no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}
	
	public void pegarDadosBancoGeral(Geral geral) throws IOException, SQLException {

		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();

		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaGeral"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				geral.setId(rs.getInt("id"));
			}
			logger.info("########## Gerando seu id automatico ##########" + "\n." + "\n." + "\n.");
			logger.info("Seu novo id do Funcionario Geral (use seu id para se conectar ao sistema): " + geral.getId());
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Funcionario Geral no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}

	public void mostrarDadosBancoGeral() throws IOException {
		
		Properties props = getProp();
		Connection connection = conexaoBancoDeDados.conexaoJDBC();
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement(props.getProperty("path.bancoDeDados.pegarDadosListaGeral"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				logger.info(rs.getInt("id") + " - " + rs.getString("nome"));
			}
			stmt.close();
		} catch (Exception e) {
			logger.error("Erro ao tentar pegar dados do Funcionario Gerl no banco de dados, por favor tente novamente!");
			throw new RuntimeException(e);
		}
	}
}

