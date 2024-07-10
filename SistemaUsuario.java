import java.io.*;
import java.util.Scanner;

public class SistemaUsuario {

    private static final String ARQUIVO_USUARIOS = "Users.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Fazer login");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            try {
                if (opcao == 1) {
                    cadastrarUsuario(scanner);
                } else if (opcao == 2) {
                    //fazerLogin(scanner);
                } else if (opcao == 3) {
                    break;
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    public static void cadastrarUsuario(Scanner scanner) throws IOException {
        System.out.println("Cadastrar usuário:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Elegível (true/false): ");
        boolean elegivel = scanner.nextBoolean();
        scanner.nextLine(); // Consumir nova linha

        System.out.println("Tipo de usuário:");
        System.out.println("1. Estudante");
        System.out.println("2. Professor");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Usuario usuario = null;

        if (tipo == 1) {
            System.out.print("Número de Matrícula: ");
            String numeroMatricula = scanner.nextLine();
            usuario = new Estudante(id, nome, login, elegivel, numeroMatricula);
        } else if (tipo == 2) {
            System.out.print("Nome do Departamento: ");
            String nomeDepartamento = scanner.nextLine();
            usuario = new Professor(id, nome, login, elegivel, nomeDepartamento);
        } else {
            System.out.println("Tipo de usuário inválido.");
            return;
        }

           BufferedWriter writer = null;
           try {
               writer = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true));
               writer.write(usuario.toString());
               writer.newLine();
               System.out.println("Usuário cadastrado com sucesso!");
           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               if (writer != null) {
                   try {
                       writer.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                }
            }
        }
    }
}