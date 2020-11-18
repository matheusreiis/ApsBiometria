package sistemas;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class SistemaDiretor {

	private static final Logger logger = Logger.getLogger(SistemaDiretor.class);
	Scanner sc = new Scanner(System.in);

	public void sistemaDiretor() {

		logger.info("\n---------- LOGIN REALIZADO COM SUCESSO ----------\n");
		logger.debug(
				"Qual acao deseja realizar?\n \n 1 - Dados Diretor\n 2 - Biometria\n 3 - Agrotoxicos Proibidos\n 4 - Encerrar Sessao\n");

		int acaoDiretor = sc.nextInt();

		for (int i = acaoDiretor; i == 1 || i == 2 || i == 3 || i == 4 || i > 5; i++) {
			if (acaoDiretor == 1) {
				logger.info("DADOS DIRETOR");
			} else if (acaoDiretor == 2) {
				logger.info("BIOMETRIA");
			} else if (acaoDiretor == 3) {
				logger.info("AGROTOXICOS PROIBIDOS");
			} else if (acaoDiretor == 4) {
				logger.info("Sessao Encerrada!");
				break;
			} else if (acaoDiretor > 5) {
				logger.info("## ACAO NAO ENCONTRADA EM NOSSO SISTEMA, POR FAVOR DIGITE UM VALOR VALIDO ##\n");
			}
			logger.info(
					"Qual acao deseja realizar?\n \n 1 - Dados Diretor\n 2 - Biometria\n 3 - Agrotóxicos Proibidos\n 4 - Encerrar Sessao\n");
			acaoDiretor = sc.nextInt();
		}
	}
}
