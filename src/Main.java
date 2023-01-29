import Model.Predica;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println(new Controller.Calendar().zi("2023" , "Ianuarie", "1").get("text"));
        var p = new Predica("C:\\Users\\Andrei\\Desktop\\Agenda-Preot\\src\\Model\\Predici.txt");
        p.AdaugaPredica("Andrei are gutui");
        p.AdaugaPredica("Ana are pere");
        p.AdaugaPredica("Marius are castraveti");
        p.EditeazaPredica(1, "Costel are pepeni");
        p.StergePredica(0);
    }
}