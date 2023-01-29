package Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Calendar {
    public JSONObject ani;
    public Calendar(String path) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(path));
            ani =  (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
