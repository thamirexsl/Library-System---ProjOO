import java.util.List;

public interface IBuscarLivro {
    public List<Livro> buscarTitulo(String _palavra);
    public List<Livro> buscarAutor(String _palavra);
    public List<Livro> buscarDescricao(String _palavra);
}
