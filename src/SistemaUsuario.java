import java.io.*;
import java.util.List;
import java.util.Scanner;
public class SistemaUsuario {

    private static final String ARQUIVO_USUARIOS = "txt/Users.txt";

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
                        menuEmprestimo(scanner, usuario);
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
        String id = scanner.nextLine();
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

        DadosDeEmprestimo dados = new DadosDeEmprestimo();
        dados.insereNovoUsuario(usuario);
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

                String id = dados[0];
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

    public static void menuEmprestimo(Scanner scanner, Usuario usuario) throws IOException {
        while (true) {
            if (usuario.getTipo().equals("Estudante")) {
                System.out.println("Menu Estudante:");
            } else if (usuario.getTipo().equals("Professor")){
                System.out.println("Menu Estudante:");
            }
            System.out.println("1. Empréstimo de livros");
            System.out.println("2. Devolução de livros");
            System.out.println("3. Retornar ao menu inicial");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if (opcao == 1) {
                System.out.println("Realizando empréstimo de livros...");
                realizarEmprestimo(scanner, usuario);
            } else if (opcao == 2) {
                System.out.println("Realizando devolução de livros...");
                devolverEmprestimo(scanner, usuario);
            } else if (opcao == 3) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void realizarEmprestimo(Scanner scanner, Usuario usuario) throws IOException {
        IBuscarLivro exCat = new ExternalCatalogAdapter();
        DadosDeEmprestimo dados = new DadosDeEmprestimo();

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
                    boolean result = dados.salvaEmprestimos(usuario, livroEncontrados.get(numLivro-1));
                    if (result) {
                        System.out.println("Livro emprestado!");
                    } else {
                        System.out.println("Livro não pode ser emprestado!");
                    }
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
                    boolean result = dados.salvaEmprestimos(usuario, livroEncontrados.get(numLivro-1));
                    if (result) {
                        System.out.println("Livro emprestado!");
                    } else {
                        System.out.println("Livro não pode ser emprestado!");
                    }
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
                    boolean result = dados.salvaEmprestimos(usuario, livroEncontrados.get(numLivro-1));
                    if (result) {
                        System.out.println("Livro emprestado!");
                    } else {
                        System.out.println("Livro não pode ser emprestado!");
                    }
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
    }

    public static void devolverEmprestimo(Scanner scanner, Usuario usuario) throws IOException {
        IBuscarLivro exCat = new ExternalCatalogAdapter();
        DadosDeEmprestimo dados = new DadosDeEmprestimo();

        List<Livro> livrosEmprestados;
        while (true) {
            System.out.println("Qual livro gostaria de devolver?");
            livrosEmprestados = dados.retornaEmprestados(usuario, exCat.buscarTitulo(""));

            for (int i=0; i<livrosEmprestados.size(); i++) {
                System.out.println((i + 1) + ". " + livrosEmprestados.get(i).getTitulo());
            }
            System.out.println((livrosEmprestados.size() + 1) + ". Retornar.\nEscolha uma opção:");
            int numLivro = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            if ((numLivro > 0) && (numLivro != livrosEmprestados.size() + 1)){
                livrosEmprestados.get(numLivro-1).devolverLivro();
                boolean result = dados.devolverEmprestimos(usuario, livrosEmprestados.get(numLivro-1));
                if (result) {
                    System.out.println("Livro Devolvido!");
                } else {
                    System.out.println("Livro não pode ser Devolvido!");
                }
            } else if (numLivro == livrosEmprestados.size() + 1){
                break;
            }else {
                continue;
            }
        }
    }
}