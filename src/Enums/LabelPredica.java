package Enums;

public enum LabelPredica {
    ButonAdaugarePredica("Adauga predica"),
    ButonVizualizarePredica("Vizualizeaza predica"),
    ButonEditarePredica("Editeaza predica"),
    ButonStergerePredica("Sterge predica"),
    ButonVizualizareListaPredici("Vizualizare lista predici"),
    TitluVizualizareListaPredici("Vizualizare lista predici"),
    TitluVizualizarePredica("Vizualizare predica"),
    TitluAdaugarePredica("Adaugare predica"),
    TitluEditarePredica("Editare predica"),
    MesajEroareLimitaCaractere("Predica trebuie sa aibe minim 10 de caractere!"),
    MesajEroareDelimitator("Predica nu poate contine caracterul @!");
    private final String _label;

    LabelPredica(String label) {
        _label = label;
    }

    public String getLabel() {
        return _label;
    }
}
