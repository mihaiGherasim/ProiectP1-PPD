package domain;

public class TratamentLocatie {
    private int idLocatie;
    private int idTratament;
    private int locuriOcupate;
    private int ora;

    public TratamentLocatie(int idLocatie, int idTratament, int ora, int locuriOcupate) {
        this.idLocatie = idLocatie;
        this.idTratament = idTratament;
        this.locuriOcupate = locuriOcupate;
        this.ora = ora;
    }

    public int getIdLocatie() {
        return idLocatie;
    }

    public void setIdLocatie(int idLocatie) {
        this.idLocatie = idLocatie;
    }

    public int getIdTratament() {
        return idTratament;
    }

    public void setIdTratament(int idTratament) {
        this.idTratament = idTratament;
    }

    public int getLocuriOcupate() {
        return locuriOcupate;
    }

    public void setLocuriOcupate(int locuriOcupate) {
        this.locuriOcupate = locuriOcupate;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }
}
