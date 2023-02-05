package Enums;

public enum LabelAnunt {
    ButonAdaugareAnunt("Adauga Anunt"),
    ButonVizualizareAnunt("Vizualizeaza Anunt"),
    ButonEditareAnunt("Editeaza Anunt"),
    ButonStergereAnunt("Sterge Anunt"),
    ButonVizualizareListaAnunturi("Vizualizare lista Anunturi"),
    TitluVizualizareListaAnunturi("Vizualizare lista Anunturi"),
    TitluVizualizareAnunt("Vizualizare Anunt"),
    TitluAdaugareAnunt("Adaugare Anunt"),
    TitluEditareAnunt("Editare Anunt"),
    MesajEroareLimitaCaractere("Anunt trebuie sa aibe minim 10 de caractere!\nSpatiile de la inceput si sfarsit nu sunt luate in considerare."),
    MesajEroareDelimitator("Anunt nu poate contine caracterul @!");
    private final String _label;

    LabelAnunt(String label) {
        _label = label;
    }

    public String getLabel() {
        return _label;
    }
}
