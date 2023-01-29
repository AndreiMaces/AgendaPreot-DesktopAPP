package Model;

import java.io.IOException;
import java.util.ArrayList;

public interface IAnunt {
    public void AdaugaAnunt(String predica) throws IOException;
    public void EditeazaAnunt(int id, String predica) throws IOException;
    public String CitesteAnunt(int id);
    public void StergeAnunt(int id) throws IOException;

    public ArrayList<String> CitesteAnunturi();
}
