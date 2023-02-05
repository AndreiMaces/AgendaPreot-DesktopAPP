package Model;

import java.io.*;
import java.util.ArrayList;

public class Anunt implements IAnunt{

    private final ArrayList<String> _Anunturi;
    private final String _caleFisier;
    public Anunt(String CaleFisier) throws IOException {
        _caleFisier = CaleFisier;
        _Anunturi = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(CaleFisier));
        while(br.ready())
        {
            String linie = br.readLine();
            String Anunt = "";
            while(!linie.contains("@") && br.ready())
            {
                Anunt += linie + " \n";
                linie = br.readLine();
            }
            _Anunturi.add(Anunt);
        }
    }

    @Override
    public void AdaugaAnunt(String Anunt) throws IOException {
        _Anunturi.add(0,Anunt);
        RescrieAnunturi();
    }

    @Override
    public void EditeazaAnunt(int id, String Anunt) throws IOException {
        _Anunturi.set(id, Anunt);
        RescrieAnunturi();
    }

    @Override
    public String CitesteAnunt(int id) {
        return _Anunturi.get(id);
    }

    @Override
    public void StergeAnunt(int id) throws IOException {
        _Anunturi.remove(id);
        RescrieAnunturi();
    }

    @Override
    public ArrayList<String> CitesteAnunturi() {
        return _Anunturi;
    }

    private void RescrieAnunturi() throws IOException {
        FileWriter fw = new FileWriter(_caleFisier, false);
        for(var Anunt : _Anunturi)
            fw.write(Anunt + "\n@\n");
        fw.close();
    }
}
