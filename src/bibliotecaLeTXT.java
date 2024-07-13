import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class bibliotecaLeTXT {

  // Método para transformar o BD em objetos
  public static List<Livro> run() {
    String arquivoCSV = "src/txt/test.txt";
    BufferedReader br = null;
    String linha = "";
    String csvDivisor = ",";
    List<Livro> livros = new ArrayList<>();

    try {
      br = new BufferedReader(new FileReader(arquivoCSV));
      while ((linha = br.readLine()) != null) {
        String[] temp = linha.split(csvDivisor);
        livros.add(fromString( Integer.valueOf(temp[temp.length-4]),  temp[temp.length-3],  temp[temp.length-2],  temp[temp.length-1]));
        // System.out.println("Livro [id= " + temp[temp.length-4] + ", Nome= " + temp[temp.length-3] + " , Autor=" +
        // temp[temp.length-2] + " , Descricao=" + temp[temp.length-1] + "]");
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
    public static Livro fromString(int _id, String _titulo, String _autor, String _descricao) {
      // System.out.println(id + "; " + titulo + "; " + autor + "; " + descricao);
      return new Livro(_id, _titulo, _autor, _descricao);
    }

}