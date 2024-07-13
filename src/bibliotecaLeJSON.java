import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class bibliotecaLeJSON {

    // Método para transformar o BD em objetos
    public static List<Livro> run() {
        String arquivoJSON = "src/json/livrosCatalogo.json";
        BufferedReader br = null;
        String linha = "";
        List<Livro> livros = new ArrayList<>();
    
        try {
            br = new BufferedReader(new FileReader(arquivoJSON));
            while ((linha = br.readLine()) != null) {
                if ((linha.contains("{\"Livros\": [")) || (linha.contains("]}"))) {
                    // System.out.println("inicio");
                }  else {
                    Livro l = fromString(linha);
                    // System.out.println(l.toString());
                    livros.add(fromString(linha));
                }
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
    public static Livro fromString(String _str) {

            String[] parts = _str.split(",");
            int id = Integer.parseInt(parts[0].replace("{\"id\": ", ""));
            String titulo  = parts[1].replace("\"titulo\":\"", "").replace("\"", "");
            String autor = parts[2].replace("\"autor\":\"", "").replace("\"", "");
            String categoria = parts[3].replace("\"categoria\":\"", "").replace("\"", "");
            int quantidade = Integer.parseInt(parts[4].replace("quantidade", "").replace("\"", "").replace(":", "").replace("}", "").replace(" ", ""));

            // System.out.println(id + "; " + titulo + "; " + autor + "; " + categoria + "; " + quantidade);
            return new Livro(id, titulo, autor, categoria, quantidade);
    }
}


