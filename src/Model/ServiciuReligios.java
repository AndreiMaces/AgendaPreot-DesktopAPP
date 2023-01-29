package Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class ServiciuReligios {
    private final String _caleFisier = "C:\\Users\\Andrei\\Desktop\\Agenda-Preot\\src\\Model\\ServiciiReligioase";
   private ArrayList<Data.ServiciuReligios> servicii = new ArrayList<Data.ServiciuReligios>();

   public ServiciuReligios() throws IOException{
       try {
          BufferedReader file = new BufferedReader(new FileReader(_caleFisier));
          while(file.ready()) {
              String[] linie = file.readLine().split("@");
              Data.ServiciuReligios serviciu = new Data.ServiciuReligios(linie[0],linie[1],linie[2],linie[3],linie[4]);
              servicii.add(serviciu);
          }
           file.close();
       } catch (IOException  e) {
           e.printStackTrace();
       }
   }

   public void adaugaServiciu(Data.ServiciuReligios serviciu, String path) {
       try {
           BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
           StringBuilder sb = new StringBuilder(serviciu.getNume());
           sb.append("@");
           sb.append(serviciu.getData());
           sb.append("@");
           sb.append(serviciu.getOra());
           sb.append("@");
           sb.append(serviciu.getAdresa());
           sb.append("@");
           sb.append(serviciu.getNrTelefon());
           writer.write("\n");
           writer.write(sb.toString());
           servicii.add(serviciu);
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public ArrayList<Data.ServiciuReligios> afisare() {

       return servicii;
   }
}
