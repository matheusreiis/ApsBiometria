package sistemas;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class SistemaGeral {

	private static final Logger logger = Logger.getLogger(SistemaGeral.class);
	Scanner sc = new Scanner(System.in);

	public void sistemaGeral() {

		logger.info("\n---------- LOGIN REALIZADO COM SUCESSO ----------\n");
		logger.debug(
				"Qual acao deseja realizar?\n \n 1 - Dados Funcionario Geral\n 2 - Biometria\n 3 - Pontos de Propriedades\n 4 - Encerrar Sessao\n");

		int acaoGeral = sc.nextInt();

		for (int i = acaoGeral; i == 1 || i == 2 || i == 3 || i == 4 || i > 5; i++) {
			if (acaoGeral == 1) {
				logger.info("DADOS FUNCIONARIO GERAL");
			} else if (acaoGeral == 2) {
				logger.info("BIOMETRIA");
			} else if (acaoGeral == 3) {
				logger.info("PONTOS DE PROPRIEDADES");
			} else if (acaoGeral == 4) {
				logger.info("Sessao Encerrada!");
				break;
			} else if (acaoGeral > 5) {
				logger.info("## ACAO NAO ENCONTRADA EM NOSSO SISTEMA, POR FAVOR DIGITE UM VALOR VALIDO ##\n");
			}
			logger.info(
					"Qual acao deseja realizar?\n \n 1 - Dados Funcionario Geral\n 2 - Biometria\n 3 - Pontos de Propriedades\n 4 - Encerrar Sessao\n");
			acaoGeral = sc.nextInt();
		}
	}
}
