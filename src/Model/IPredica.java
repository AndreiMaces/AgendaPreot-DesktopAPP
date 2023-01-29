package Model;

import java.io.IOException;
import java.util.ArrayList;

public interface IPredica {
    public void AdaugaPredica(String predica) throws IOException;
    public void EditeazaPredica(int id, String predica) throws IOException;
    public String CitestePredica(int id);
    public void StergePredica(int id) throws IOException;

    public ArrayList<String> CitestePredici();
}
