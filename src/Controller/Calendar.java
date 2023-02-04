package Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Calendar {

    Model.Calendar InformatiiCalendar;
    public Calendar() {
        InformatiiCalendar = new Model.Calendar("src/Model/Calendar.json");
    }

    public JSONObject an(String a) {
        return (JSONObject) InformatiiCalendar.ani.get(a);
    }

    public JSONArray luna(String a, String l)
    {
        return (JSONArray)an(a).get(l);
    }

    public JSONObject zi(String a , String l , String z) {
        return (JSONObject) luna(a , l).get(Integer.parseInt(z) - 1);
    }
}
