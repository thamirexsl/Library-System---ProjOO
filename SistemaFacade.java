import java.io.IOException;
import java.util.Scanner;

public class SistemaFacade {

    private SistemaUsuario sistemaUsuario;
    private Usuario usuarioLogado;

    public SistemaFacade() {
        sistemaUsuario = new SistemaUsuario();
    }

    public void cadastrarUsuario() throws IOException {
        Scanner scanner = new Scanner(System.in);
        sistemaUsuario.cadastrarUsuario(scanner);
    }

    public boolean fazerLogin() throws IOException {
        Scanner scanner = new Scanner(System.in);
        usuarioLogado = sistemaUsuario.fazerLogin(scanner);
        if (usuarioLogado != null) {
            System.out.println("Olá, " + usuarioLogado.getNome());
            return true;
        } else {
            System.out.println("Login falhou.");
            return false;
        }
    }

    public void menuEstudante() {
        if (usuarioLogado != null && usuarioLogado.getTipo().equals("Estudante")) {
            Scanner scanner = new Scanner(System.in);
            sistemaUsuario.menuEstudante(scanner, usuarioLogado);
        } //else {
            //System.out.println("Acesso negado. Usuário não é estudante ou não está logado.");
        //}
    }

    public void menuProfessor() {
        if (usuarioLogado != null && usuarioLogado.getTipo().equals("Professor")) {
            Scanner scanner = new Scanner(System.in);
            sistemaUsuario.menuProfessor(scanner, usuarioLogado);
        } //else {
            //System.out.println("Acesso negado. Usuário não é professor ou não está logado.");
        //}
    }

    public void retornarMenuInicial() {
        usuarioLogado = null;
        System.out.println("Retornando ao menu inicial...");
    }

    public static void main(String[] args) {
        SistemaFacade facade = new SistemaFacade();
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
                    facade.cadastrarUsuario();
                } else if (opcao == 2) {
                    if (facade.fazerLogin()) {
                        if (facade.usuarioLogado.getTipo().equals("Estudante")) {
                            facade.menuEstudante();
                        } else if (facade.usuarioLogado.getTipo().equals("Professor")) {
                            facade.menuProfessor();
                        }
                        facade.retornarMenuInicial();
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
}
