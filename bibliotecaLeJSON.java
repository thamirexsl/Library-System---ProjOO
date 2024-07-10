import org.json.*;
import java.io.IOException;

public class bibliotecaLeJSON {

    public static void main(String[] args) throws IOException { 
        JSONObject obj = new JSONObject("{\"name\": \"John\"}");

        System.out.println(obj.getString("name"));
    } 

}


