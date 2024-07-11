// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {

    String livros[] = bibliotecaLeTXT.run();
    System.out.println("Livro [Nome= " + livros[livros.length - 6] + " , Autor=" + livros[livros.length - 5]
        + " , Descricao=" + livros[livros.length - 4] + "]");

    bibliotecaLeJSON.main(args);
  }

  // @Test
  // void addition() {
  // assertEquals(2, 1 + 1);
  // }
}