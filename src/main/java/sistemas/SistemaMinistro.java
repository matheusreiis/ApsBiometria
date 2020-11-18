package sistemas;

import java.util.Scanner;

import org.apache.log4j.Logger;


public class SistemaMinistro {

	private static final Logger logger = Logger.getLogger(SistemaMinistro.class);
	Scanner sc = new Scanner(System.in);

	public void sistemaMinistro() {

		logger.info("\n---------- LOGIN REALIZADO COM SUCESSO ----------\n");
		logger.debug(
				"Qual acao deseja realizar?\n \n 1 - Dados Ministro\n 2 - Biometria\n 3 - Propriedades Rurais\n 4 - Encerrar Sessao\n");
		
		int acaoMinistro = sc.nextInt();

		for (int i = acaoMinistro; i == 1 || i == 2 || i == 3 || i == 4 || i > 5; i++) {
			if (acaoMinistro == 1) {
				logger.info("DADOS MINISTRO");
			} else if (acaoMinistro == 2) {
				logger.info("BIOMETRIA");
			} else if (acaoMinistro == 3) {
				logger.info("PROPRIEDADES RURAIS");
			} else if (acaoMinistro == 4) {
				logger.info("Sessao Finalizada!");
				break;
			} else if (acaoMinistro > 5) {
				logger.info("## ACAO NAO ENCONTRADA EM NOSSO SISTEMA, POR FAVOR DIGITE UM VALOR VALIDO ##\n");
			}

			logger.info(
					"Qual acao deseja realizar?\n \n 1 - Dados Ministro\n 2 - Biometria\n 3 - Propriedades Rurais\n 4 - Encerrar Sessao\n");
			acaoMinistro = sc.nextInt();
		}
	}
}

