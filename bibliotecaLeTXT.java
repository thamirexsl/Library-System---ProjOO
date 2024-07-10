import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class bibliotecaLeTXT {
  public static String[] run() {
    String arquivoCSV = "src/main/java/test.txt";
    BufferedReader br = null;
    String linha = "";
    String csvDivisor = ",";
    String[] livros = new String[0];

    try {
      br = new BufferedReader(new FileReader(arquivoCSV));
      while ((linha = br.readLine()) != null) {
        String[] temp = linha.split(csvDivisor);
        livros = Arrays.copyOf(livros, livros.length + temp.length);
        System.arraycopy(temp, 0, livros, livros.length - temp.length, temp.length);
        // System.out.println("Livro [Nome= " + temp[temp.length-3] + " , Autor=" +
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

}