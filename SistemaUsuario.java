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
                    Usuario usuario = fazerLogin(scanner);
                    if (usuario != null) {
                        System.out.println("Olá, " + usuario.getNome());
                        if (usuario.getTipo().equals("Estudante")) {
                            menuEstudante(scanner, usuario);
                        } else if (usuario.getTipo().equals("Professor")) {
                            menuProfessor(scanner, usuario);
                        }
                    } else {
                        System.out.println("Login falhou.");
                    }
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

    public static Usuario fazerLogin(Scanner scanner) throws IOException {
        System.out.println("Fazer login:");
        System.out.print("Login: ");
        String login = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 6) {
                    continue; // Pula linhas que não têm o formato correto
                }

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String loginArquivo = dados[2];
                boolean elegivel = Boolean.parseBoolean(dados[3]);
                String tipo = dados[4];

                if (loginArquivo.equals(login)) {
                    if (tipo.equals("Estudante")) {
                        String numeroMatricula = dados[5];
                        return new Estudante(id, nome, login, elegivel, numeroMatricula);
                    } else if (tipo.equals("Professor")) {
                        String nomeDepartamento = dados[5];
                        return new Professor(id, nome, login, elegivel, nomeDepartamento);
                    }
                }
            }
        }

        return null;
    }

    public static void menuEstudante(Scanner scanner, Usuario usuario) {
        while (true) {
            System.out.println("Menu Estudante:");
            System.out.println("1. Empréstimo de livros");
            System.out.println("2. Devolução de livros");
            System.out.println("3. Retornar ao menu inicial");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (opcao == 1) {
                System.out.println("Realizando empréstimo de livros...");
                // Adicione aqui a lógica para empréstimo de livros
            } else if (opcao == 2) {
                System.out.println("Realizando devolução de livros...");
                // Adicione aqui a lógica para devolução de livros
            } else if (opcao == 3) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void menuProfessor(Scanner scanner, Usuario usuario) {
        while (true) {
            System.out.println("Menu Professor:");
            System.out.println("1. Empréstimo de livros");
            System.out.println("2. Devolução de livros");
            System.out.println("3. Editar acervo");
            System.out.println("4. Retornar ao menu inicial");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (opcao == 1) {
                System.out.println("Realizando empréstimo de livros...");
                // Adicione aqui a lógica para empréstimo de livros
            } else if (opcao == 2) {
                System.out.println("Realizando devolução de livros...");
                // Adicione aqui a lógica para devolução de livros
            } else if (opcao == 3) {
                System.out.println("Realizando devolução de livros...");
                break;
            }else if (opcao == 4) {
                break; 
            }else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}