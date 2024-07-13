import java.util.ArrayList;
import java.util.List;

public class ExternalCatalogAdapter implements IBuscarLivro{
    List<Livro> livros;

    public ExternalCatalogAdapter() {
        this.livros = new ArrayList<>();
        List<Livro> livros2 = new ArrayList<>();
        
        livros = bibliotecaLeTXT.run();
        livros2 = bibliotecaLeJSON.run();
        
        for (Livro livro : livros2) {
            this.livros.add(livro);
        }
    }
    
    // Método para buscar livros pelo titulo
    public List<Livro> buscarTitulo(String _palavra) {
        List<Livro> livrosRet = new ArrayList<>();

        for (Livro livro : this.livros) {
            if (livro.getTitulo().contains(_palavra.toLowerCase())){
                livrosRet.add(livro);
            }
        }
        return livrosRet;
    }

    // Método para buscar livros pelo autor
    public List<Livro> buscarAutor(String _palavra) {
        List<Livro> livrosRet = new ArrayList<>();

        for (Livro livro : this.livros) {
            if (livro.getAutor().contains(_palavra.toLowerCase())){
                livrosRet.add(livro);
            }
        }
        return livrosRet;
    }

    // Método para buscar livros pelo descricao
    public List<Livro> buscarDescricao(String _palavra) {
        List<Livro> livrosRet = new ArrayList<>();

        for (Livro livro : this.livros) {
            if (livro.getDescricao().contains(_palavra.toLowerCase())){
                livrosRet.add(livro);
            }
        }
        return livrosRet;
    }
}