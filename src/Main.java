// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    IBuscarLivro exCat = new ExternalCatalogAdapter();

    List<Livro> livros = exCat.buscarDescricao("descricao3");

    for (Livro livro : livros) {
      System.out.println(livro.toString());
    }
  }
}