package Model;

import java.io.*;
import java.util.ArrayList;

public class Predica implements IPredica{

    private final ArrayList<String> _predici;
    private final String _caleFisier;
    // C:\Users\Andrei\Desktop\Agenda-Preot\src\Model\Predici.txt
    public Predica(String CaleFisier) throws IOException {
        _caleFisier = CaleFisier;
        _predici = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(CaleFisier));
        while(br.ready())
        {
            String predica = br.readLine();
            _predici.add(predica);
        }
    }

    @Override
    public void AdaugaPredica(String predica) throws IOException {
        _predici.add(predica);
        RescriePredici();
    }

    @Override
    public void EditeazaPredica(int id, String predica) throws IOException {
        _predici.set(id, predica);
        RescriePredici();
    }

    @Override
    public String CitestePredica(int id) {
        return _predici.get(id);
    }

    @Override
    public void StergePredica(int id) throws IOException {
        _predici.remove(id);
        RescriePredici();
    }

    @Override
    public ArrayList<String> CitestePredici() {
        return _predici;
    }

    private void RescriePredici() throws IOException {
        FileWriter fw = new FileWriter(_caleFisier, false);
        for(var predica : _predici)
            fw.write(predica + "\n");
        fw.close();
    }
}
