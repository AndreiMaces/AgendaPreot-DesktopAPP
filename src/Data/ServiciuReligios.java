package Data;

public class ServiciuReligios {
    private String data, nume, ora, adresa, nrTelefon;

    public ServiciuReligios(String nume, String data, String ora, String adresa, String nrTelefon) {
        this.data = data;
        this.nume = nume;
        this.ora = ora;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getData() {
        return data;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public String getNume() {
        return nume;
    }

    public String getOra() {
        return ora;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }
}
