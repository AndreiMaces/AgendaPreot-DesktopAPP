package Model;

import java.io.*;
import java.util.ArrayList;

public class Anunt implements IAnunt{

    private final ArrayList<String> _anunturi;
    private final String _caleFisier;
    // C:\Users\Andrei\Desktop\Agenda-Preot\src\Model\Anunturi.txt

    public Anunt(String CaleFisier) throws IOException {
        _caleFisier = CaleFisier;
        _anunturi = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(CaleFisier));
        while(br.ready())
        {
            String predica = br.readLine();
            _anunturi.add(predica);
        }
    }

    @Override
    public void AdaugaAnunt(String predica) throws IOException {
        _anunturi.add(predica);
        RescriePredici();
    }

    @Override
    public void EditeazaAnunt(int id, String predica) throws IOException {
        _anunturi.set(id, predica);
        RescriePredici();
    }

    @Override
    public String CitesteAnunt(int id) {
        return _anunturi.get(id);
    }

    @Override
    public void StergeAnunt(int id) throws IOException {
        _anunturi.remove(id);
        RescriePredici();
    }

    @Override
    public ArrayList<String> CitesteAnunturi() {
        return _anunturi;
    }

    private void RescriePredici() throws IOException {
        FileWriter fw = new FileWriter(_caleFisier, false);
        for(var anunt : _anunturi)
            fw.write(anunt + "\n");
        fw.close();
    }
}
