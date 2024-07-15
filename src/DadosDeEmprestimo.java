import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DadosDeEmprestimo {
    private static final String dadosDeEmprestimoTxt = "src/txt/llistaDeEmprestimos.txt";

    public DadosDeEmprestimo(){
        System.err.println("");
    }

    public int ultimoIdUsado() throws FileNotFoundException, IOException{
        int id = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(dadosDeEmprestimoTxt))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 3) {
                    continue; // Pula linhas que não têm o formato correto
                }

                id = Integer.parseInt(dados[0]);
            }
        }
        return id;
    }
    
    public void insereNovoUsuario(Usuario usuario) throws FileNotFoundException, IOException{
        int ID = ultimoIdUsado() + 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dadosDeEmprestimoTxt, true))) {
            writer.write(Integer.toString(ID) + "," + usuario.getId() + ","+ usuario.getLogin() +", ");
            writer.newLine();
            writer.close();
        }
    }

    public void substituiLinha(String oldLine, String newLine) throws IOException{
        Scanner sc = new Scanner(new FileReader(dadosDeEmprestimoTxt));
        StringBuffer buffer = new StringBuffer();

        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }

        String fileContents = buffer.toString();
        sc.close();

        fileContents = fileContents.replaceAll(oldLine, newLine);

        FileWriter writer = new FileWriter(dadosDeEmprestimoTxt);
        writer.append(fileContents);
        writer.flush();
    }
    
    public boolean salvaEmprestimos(Usuario usuario, Livro livro) throws FileNotFoundException, IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(dadosDeEmprestimoTxt))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 4) {
                    continue; // Pula linhas que não têm o formato correto
                }
                
                int id = Integer.parseInt(dados[0]);
                String usuarioID = dados[1];
                String usuarioLogin = dados[2];
                String livros = dados[3].replaceAll("^ ", "");

                if ((usuarioID.equalsIgnoreCase(usuario.getId())) && (usuarioLogin.equalsIgnoreCase(usuario.getLogin())) && (!livros.contains(livro.getTitulo()))){
                    String strOutput = Integer.toString(id) + "," + usuarioID + "," + usuarioLogin + "," + livros + " " + livro.getTitulo() + "";
                    substituiLinha(linha, strOutput);
                    return true;
                }
            }
            return false;
        }
    }

    public boolean devolverEmprestimos(Usuario usuario, Livro livro) throws FileNotFoundException, IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(dadosDeEmprestimoTxt))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 4) {
                    continue; // Pula linhas que não têm o formato correto
                }
                
                int id = Integer.parseInt(dados[0]);
                String usuarioID = dados[1];
                String usuarioLogin = dados[2];
                String livros = dados[3].replaceAll("^ ", "");

                if ((usuarioID.equalsIgnoreCase(usuario.getId())) && (usuarioLogin.equalsIgnoreCase(usuario.getLogin())) && (livros.contains(livro.getTitulo()))){
                    livros = livros.replace(livro.getTitulo(), "");
                    if (livros == ""){
                        livros = " ";
                    }
                    String strOutput = Integer.toString(id) + "," + usuarioID + "," + usuarioLogin + "," + livros;
                    substituiLinha(linha, strOutput);
                    return true;
                }
            }
            return false;
        }
    }
    
    public List<Livro> listaDeLivros(List<Livro> livrosCat, String livros){
        List<Livro> l = new ArrayList<>();
        for (Livro livro : livrosCat) {
            if (livro.getTitulo().contains(livros)){
                livros = livros.replace(livro.getTitulo(), "");
                l.add(livro);
            }

        }
        return l;
    }

    public List<Livro> retornaEmprestados(Usuario usuario, List<Livro> livrosCat) throws FileNotFoundException, IOException{
        List<Livro> l = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dadosDeEmprestimoTxt))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 4) {
                    continue; // Pula linhas que não têm o formato correto
                }
                
                int id = Integer.parseInt(dados[0]);
                String usuarioID = dados[1];
                String usuarioLogin = dados[2];
                String livros = dados[3].replaceAll("^ ", "");

                if ((usuarioID.equalsIgnoreCase(usuario.getId())) && (usuarioLogin.equalsIgnoreCase(usuario.getLogin()))){
                    return listaDeLivros(livrosCat, livros);
                }
            }
        }
        return l;
    }
}
