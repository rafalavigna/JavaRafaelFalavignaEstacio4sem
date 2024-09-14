import java.util.Scanner;

public class ControleMaterialDidatico {
    public static void main(String[] args) {
        final int TOTAL_ALUNOS = 330;
        boolean[] apostilaUmRetirada = new boolean[TOTAL_ALUNOS];
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Sistema de Controle de Entrega de Material Didático ---");
            System.out.println("1. Registrar retirada de material");
            System.out.println("2. Verificar alunos que já retiraram");
            System.out.println("3. Verificar alunos que ainda não retiraram");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    registrarRetirada(apostilaUmRetirada, scanner);
                    break;
                case 2:
                    listarAlunos(apostilaUmRetirada, true);
                    break;
                case 3:
                    listarAlunos(apostilaUmRetirada, false);
                    break;
                case 4:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    public static void registrarRetirada(boolean[] apostilaUmRetirada, Scanner scanner) {
        System.out.print("Digite o número de matrícula (4 dígitos): ");
        String matriculaStr = scanner.next();

        // Verifica se a matrícula tem 4 dígitos
        if (matriculaStr.length() != 4) {
            System.out.println("Número de matrícula inválido. Deve conter 4 dígitos.");
            return;
        }

        int matricula;
        try {
            matricula = Integer.parseInt(matriculaStr);
        } catch (NumberFormatException e) {
            System.out.println("Número de matrícula inválido. Deve ser numérico.");
            return;
        }

        // Verifica se a matrícula está no intervalo válido
        if (matricula < 1 || matricula > 330) {
            System.out.println("Número de matrícula fora do intervalo. Deve ser entre 0001 e 0330.");
            return;
        }

        // Solicita o nome do aluno
        System.out.print("Digite o nome do aluno: ");
        scanner.nextLine(); // Consumir a quebra de linha pendente
        String nomeAluno = scanner.nextLine();

        // Marca que o aluno retirou a apostila
        apostilaUmRetirada[matricula - 1] = true;
        System.out.println("Retirada registrada para o aluno " + nomeAluno + " (Matrícula: " + matriculaStr + ").");
    }

    public static void listarAlunos(boolean[] apostilaUmRetirada, boolean retiraram) {
        System.out.println(retiraram ? "\nAlunos que já retiraram a apostilaUm:" : "\nAlunos que ainda não retiraram a apostilaUm:");

        boolean encontrou = false;
        for (int i = 0; i < apostilaUmRetirada.length; i++) {
            if (apostilaUmRetirada[i] == retiraram) {
                String matriculaFormatada = String.format("%04d", i + 1);
                System.out.println("Matrícula: " + matriculaFormatada);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum aluno encontrado.");
        }
    }
}
