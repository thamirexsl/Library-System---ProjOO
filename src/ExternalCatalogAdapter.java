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

    // Método para buscar livros pelo categoria
    public List<Livro> buscarCategoria(String _palavra) {
        List<Livro> livrosRet = new ArrayList<>();

        for (Livro livro : this.livros) {
            if (livro.getCategoria().contains(_palavra.toLowerCase())){
                livrosRet.add(livro);
            }
        }
        return livrosRet;
    }

    // Exibe a lista de livros e enumera
    public void enumCatalog(List<Livro> _listLiv){
        int index = 1;
        for (Livro livro : _listLiv) {
            System.out.println((index++)+ ". " + livro.toString());
        }
    }
}