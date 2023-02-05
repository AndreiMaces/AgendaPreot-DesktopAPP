package Enums;

public enum LabelServiciu {
    MetodaAdaugare("adaugare"),

    MetodaEditare("editare"),

    TitluVizualizareServiciu("Servicii Religioase"),

    TitluAdaugareServiciu("Adauga serviciu religios"),

    titluEditareServiciu("Editare Serviciu Religios"),

    ConfirmareStergere("Sunteti sigur ca vreti sa stergeti serviciul?"),

    TitluMesajStergere("Sterge Serviciu"),

    MesajValidareNume("Va rog introduceti un nume valid.\nNumele nu poate fi gol,sa contina cifre sau caractere speciala sau ca lungimea lui sa fie mai mare de 40 de caractere."),

    MesajValidareOra("Va rog introduceti o ora valida.\n Formatul orei este h sau hh sau hh:mm."),

    MesajValidareAdresa("Va rog introduceti o adresa valida.\nAdresa nu poate fi goala sau sa contina mai mult de 80 de caractere."),

    MesajValidareTelefon("Va rog introduceti un numar de telefon valid.\n Formatul este 07xxxxxxxx sau +407xxxxxxxx."),

    MesajSuccesEditare("Editare efectuata cu succes."),

    MesajSuccesAdaugare("Adaugare efectuata cu succes."),

    OptiuneEditare("Editeaza"),

    OptiuneStergere("Sterge");




    private final String _label;

    LabelServiciu(String label) {
        _label = label;
    }

    public String getLabel() {
        return _label;
    }
}
