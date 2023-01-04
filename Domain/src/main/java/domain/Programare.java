package domain;

import lombok.*;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Programare implements Serializable {
    private String numeClient;
    private String cnp;
    private Date data;
    private int idLocatie;
    private int idTratament;
    private Date dataTratament;

    @Override
    public String toString() {
        return "Programare{" +
                "numeClient='" + numeClient + '\'' +
                ", cnp='" + cnp + '\'' +
                ", data=" + data +
                ", idLocatie=" + idLocatie +
                ", idTratament=" + idTratament +
                ", dataTratament=" + dataTratament +
                '}';
    }

    public Programare(String numeClient, String cnp, Date data, int idLocatie, int idTratament, Date dataTratament) {
        this.numeClient = numeClient;
        this.cnp = cnp;
        this.data = data;
        this.idLocatie = idLocatie;
        this.idTratament = idTratament;
        this.dataTratament = dataTratament;
    }

    public static Programare genereazaProgramare(int nrLocatii,int nrTratamente){
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String numeClient = new String(array, StandardCharsets.UTF_8);
        StringBuilder cnp = new StringBuilder();
        for(int i=0; i<13; i++){
            int cifra = new Random().nextInt(10);
            cnp.append(cifra);
        }
        Date data = new Date();
        int idTratament = new Random().nextInt(nrTratamente)+1;
        int idLocatie = new Random().nextInt(nrLocatii)+1;
        Date dataTratament = new Date();
        return new Programare(numeClient, cnp.toString(), data, idLocatie, idTratament, dataTratament);
    }
}

