import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println(new Controller.Calendar().zi("2023" , "Ianuarie", "1").get("text"));
        Model.ServiciuReligios m = new Model.ServiciuReligios("C:\\Users\\Andrei\\Desktop\\Agenda-Preot\\src\\Model\\ServiciiReligioase");
        Data.ServiciuReligios m1 = new Data.ServiciuReligios("Maces Andrei", "11.10.2003","11","Brandusei", "787890");
        m.adaugaServiciu(m1, "C:\\Users\\Andrei\\Desktop\\Agenda-Preot\\src\\Model\\ServiciiReligioase");
    }
}