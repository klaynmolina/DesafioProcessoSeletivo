import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {

	public static void main(String[] args) {

		Double salarioOferecido = 2000.0;

		List<String> candidatos = Arrays.asList("Felipe", "Marcia", "Julia", "Paulo", "Augusto", "Monica", "Fabricio",
				"Mirela", "Daniela", "Jorge");

//		Análise 
		System.out.println("SISTEMA DE ANÁLISE PARA PROCESSO SELETIVO");
		selecaoCandidaturas(candidatos, salarioOferecido);

	}

//	Analisar Candidatos
	public static String analisarCandidaduras(double salarioOferecido, double pretensaoSalarial) {
		if (salarioOferecido > pretensaoSalarial) {
			return "Ligar para Candidato e Marcar Entrevista.";
		} else if (salarioOferecido == pretensaoSalarial) {
			return "Ligar para Candidato e Oferecer Proposta.";
		} else {
			return "Enviar E-mail Cancelando Candidatura.";
		}
	}

//	Selecionar Candidatos
	public static void selecaoCandidaturas(List<String> listaDeCandidatos, double salarioOferecido) {
		int selecionado = 1;
		while (selecionado <= 5) {
			for (int i = 0; i < listaDeCandidatos.size(); i++) {
				String candidatoEmAnalise = listaDeCandidatos.get(i);
				double pretensaoSalarial = geradorPretensaoSalarial();
				String todo = analisarCandidaduras(salarioOferecido, pretensaoSalarial);

				System.out.println("\n"+selecionado + "º Candidato: " + candidatoEmAnalise);
				System.out.printf("Pretensão Salarial: R$%.2f\n", pretensaoSalarial);

				if (salarioOferecido >= pretensaoSalarial) {
					System.out.println("STATUS: Aprovado Para Próxima Etapa.");
					System.out.printf("TODO: %s\n", todo);
					System.out.print("CONTATO: "); realizarContato();
				} else {
					System.out.println("Status: Não Selecionado.");
					System.out.printf("TODO: %s\n", todo);
				}

				selecionado++;
			}
		}
	}

//	Tentativa de contato 
	public static void realizarContato() {
		int contatosRealizados = 0;
		Boolean novaTentativa = true;
		Boolean tentativa = null;

		do {
			tentativa = geradorTentativaContato();
			if (!tentativa) {
				novaTentativa = true;
				contatosRealizados++;
			} else {
				novaTentativa = false;
			}
		} while (contatosRealizados < 3 && novaTentativa);

		if (contatosRealizados < 3 && tentativa) {
			System.out.printf("Contato Realizado com Sucesso, (%dª tentativa).\n", contatosRealizados + 1);
		} else {
			System.out.println("Não Foi Possível Realizar Contato.");
		}
	}

//	Gerador de Pretensão Salarial
	public static double geradorPretensaoSalarial() {
		double pretensaoSalarial = ThreadLocalRandom.current().nextDouble(1800, 2200);
		return pretensaoSalarial;
	}

//	Gerador de Contato
	public static boolean geradorTentativaContato() {
		boolean tentativaContato = ThreadLocalRandom.current().nextBoolean();
		return tentativaContato;
	}

}
