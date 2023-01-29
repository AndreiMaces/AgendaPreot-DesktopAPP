package Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;
import java.io.IOException;

public class ServiciuReligios {
   private ArrayList<String> servicii = new ArrayList<String>();

   public ServiciuReligios(String path) throws IOException{
       try {
          BufferedReader file = new BufferedReader(new FileReader(path));
          while(file.ready()) {
              servicii.add(file.readLine());
          }
           for (String st: servicii) {
               System.out.println(st);
           }
       } catch (IOException  e) {
           e.printStackTrace();
       }
   }
}
