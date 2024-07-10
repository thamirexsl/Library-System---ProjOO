import java.io.*;
import java.util.Scanner;

public class SistemaUsuario {

    private static final String ARQUIVO_USUARIOS = "Users.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Empréstimo de livros");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            try {
                if (opcao == 1) {
                    cadastrarUsuario(scanner);
                } else if (opcao == 2) {
                    realizarEmprestimo(scanner);
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
            writer.write(usuario.toString());
            writer.newLine();
            System.out.println("Usuário cadastrado com sucesso!");
        }
    }

    public static void realizarEmprestimo(Scanner scanner) throws IOException {
        System.out.println("Fazer login para empréstimo de livros:");
        System.out.print("Login: ");
        String login = scanner.nextLine();

        Usuario usuario = fazerLogin(login);

        if (usuario != null) {
            System.out.println("Olá, " + usuario.getNome());
            // Aqui você pode adicionar a lógica para o processo de empréstimo de livros
        } else {
            System.out.println("Login falhou.");
        }
    }

    public static Usuario fazerLogin(String login) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String loginArquivo = dados[2];
                boolean elegivel = Boolean.parseBoolean(dados[3]);

                if (loginArquivo.equals(login)) {
                    if (dados.length == 5) { // Estudante
                        String numeroMatricula = dados[4];
                        return new Estudante(id, nome, login, elegivel, numeroMatricula);
                    } else if (dados.length == 6) { // Professor
                        String nomeDepartamento = dados[5];
                        return new Professor(id, nome, login, elegivel, nomeDepartamento);
                    }
                }
            }
        }
        return null;
    }
}
