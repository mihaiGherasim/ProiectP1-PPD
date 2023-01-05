package domain;

public class TratamentLocatie {
    private int idLocatie;
    private int idTratament;
    private int locuriOcupate;

    public TratamentLocatie(int idLocatie, int idTratament, int locuriOcupate) {
        this.idLocatie = idLocatie;
        this.idTratament = idTratament;
        this.locuriOcupate = locuriOcupate;
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
}
