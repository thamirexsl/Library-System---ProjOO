import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class bibliotecaLeTXT {

  // Método para transformar o BD em objetos
  public static List<Livro> run() {
    String arquivoCSV = "txt/livrosCatalogo.txt";
    BufferedReader br = null;
    String linha = "";
    String csvDivisor = ",";
    List<Livro> livros = new ArrayList<>();

    try {
      br = new BufferedReader(new FileReader(arquivoCSV));
      while ((linha = br.readLine()) != null) {
        String[] temp = linha.split(csvDivisor);
        livros.add(fromString( Integer.valueOf(temp[temp.length-5]),  temp[temp.length-4],  temp[temp.length-3],  temp[temp.length-2],  Integer.valueOf(temp[temp.length-1])));
        // System.out.println("Livro [id= " + temp[temp.length-5] + ", Nome= " + temp[temp.length-4] + " , Autor=" +
        // temp[temp.length-3] + " , Categoria=" + temp[temp.length-2] + ", Quantidade=" + temp[temp.length-1] + "]");
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return livros;
  }

    // Método para converter a string de volta para o objeto
    public static Livro fromString(int _id, String _titulo, String _autor, String _categoria, int _quantidade) {
      // System.out.println(_id + "; " + _titulo + "; " + _autor + "; " + _categoria + "; " + _quantidade);
      return new Livro(_id, _titulo, _autor, _categoria, _quantidade);
    }

}