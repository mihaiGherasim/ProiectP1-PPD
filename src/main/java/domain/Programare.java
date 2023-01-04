package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Programare {
    private String numeClient;
    private String cnp;
    private Date data;
    private int idLocatie;
    private int idTratament;
    private Date dataTratament;

    public Programare(String numeClient, String cnp, Date data, int idLocatie, int idTratament, Date dataTratament) {
        this.numeClient = numeClient;
        this.cnp = cnp;
        this.data = data;
        this.idLocatie = idLocatie;
        this.idTratament = idTratament;
        this.dataTratament = dataTratament;
    }
}
