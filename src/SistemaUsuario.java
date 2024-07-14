import java.io.*;
import java.util.List;
import java.util.Scanner;

public class SistemaUsuario {

    private static final String ARQUIVO_USUARIOS = "src/txt/Users.txt";

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
        IBuscarLivro exCat = new ExternalCatalogAdapter();

        Usuario usuario = fazerLogin(login);
        
        if (usuario != null) {
            System.out.println("Olá, " + usuario.getNome());

    
            List<Livro> livros;
            while (true) {
                System.out.println("Escolha uma opção para encontrar seu livro:");
                System.out.println("1. Buscar pelo título");
                System.out.println("2. Buscar pelo autor");
                System.out.println("3. Buscar pela categoria");
                System.out.println("4. Sair");
                int opcaoBusca = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

    
                if (opcaoBusca == 1) {
                    System.out.println("Qual título do livro que busca?");
                    String tituloLivro = scanner.nextLine();

                    List<Livro> livroEncontrados = exCat.buscarTitulo(tituloLivro);
                    exCat.enumCatalog(livroEncontrados);
                    System.out.println((livroEncontrados.size() + 1) + ". Continuar buscando.\nEstes são os resultados de sua busca.\nEscolha uma opção:");
                    int numLivro = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha

                    if ((numLivro > 0) && (numLivro != livroEncontrados.size() + 1)){
                        livroEncontrados.get(numLivro-1).emprestarLivro();
                    } else {
                        continue;
                    }
                } else if (opcaoBusca == 2) {
                    System.out.println("Qual autor do livro que busca?");
                    String autorLivro = scanner.nextLine();

                    List<Livro> livroEncontrados = exCat.buscarAutor(autorLivro);
                    exCat.enumCatalog(livroEncontrados);
                    System.out.println((livroEncontrados.size() + 1) + ". Continuar buscando.\nEstes são os resultados de sua busca.\nEscolha uma opção:");
                    int numLivro = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha

                    if ((numLivro > 0) && (numLivro != livroEncontrados.size() + 1)){
                        livroEncontrados.get(numLivro-1).emprestarLivro();
                    } else {
                        continue;
                    }
                } else if (opcaoBusca == 3) {
                    System.out.println("Qual categoria do livro que busca?");
                    String categoriaLivro = scanner.nextLine();

                    List<Livro> livroEncontrados = exCat.buscarCategoria(categoriaLivro);
                    exCat.enumCatalog(livroEncontrados);
                    System.out.println((livroEncontrados.size() + 1) + ". Continuar buscando.\nEstes são os resultados de sua busca.\nEscolha uma opção:");
                    int numLivro = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha

                    if ((numLivro > 0) && (numLivro != livroEncontrados.size() + 1)){
                        livroEncontrados.get(numLivro-1).emprestarLivro();
                    } else {
                        continue;
                    }
                } else if (opcaoBusca == 4) {
                    System.out.println("Retornando ao menu!");
                    break;
                }else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }

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
                String tipo = dados[4];

                if (loginArquivo.equals(login)) {
                    if (tipo.equals("Estudante")) { // Estudante
                        String numeroMatricula = dados[5];
                        return new Estudante(id, nome, login, elegivel, numeroMatricula);
                    } else if (tipo.equals("Professor")) { // Professor
                        String nomeDepartamento = dados[5];
                        return new Professor(id, nome, login, elegivel, nomeDepartamento);
                    }
                }
            }
        }
        return null;
    }
}


