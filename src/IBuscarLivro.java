import java.util.List;

public interface IBuscarLivro {
    public List<Livro> buscarTitulo(String _palavra);
    public List<Livro> buscarAutor(String _palavra);
    public List<Livro> buscarCategoria(String _palavra);
    public void enumCatalog(List<Livro> _listLiv);
}
