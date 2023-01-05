package domain;

import lombok.*;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
@ToString
public class Programare implements Serializable {
    private String numeClient;
    private String cnp;
    private Date data;
    private int idLocatie;
    private int idTratament;
    private Date dataTratament;
    private int anulare;
    private int suma;

    @Override
    public String toString() {
        return "Programare{" +
                "numeClient='" + numeClient + '\'' +
                ", cnp='" + cnp + '\'' +
                ", data=" + data +
                ", idLocatie=" + idLocatie +
                ", idTratament=" + idTratament +
                ", dataTratament=" + dataTratament +
                ", anulare=" + anulare +
                '}';
    }

    public Programare(String numeClient, String cnp, Date data, int idLocatie, int idTratament, Date dataTratament, int anulare) {
        this.numeClient = numeClient;
        this.cnp = cnp;
        this.data = data;
        this.idLocatie = idLocatie;
        this.idTratament = idTratament;
        this.dataTratament = dataTratament;
        this.suma = 0;
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
        List<LocalDateTime> dates = Arrays.asList(LocalDateTime.now().minusHours(4), LocalDateTime.now().minusHours(6));
        int randomDateIndex = new Random().nextInt(2);
        Date dataTratament = Date.from(dates.get(randomDateIndex).atZone(ZoneId.systemDefault()).toInstant());
        int anulare = ThreadLocalRandom.current().nextInt(0, 2);
        return new Programare(numeClient, cnp.toString(), dataTratament, idLocatie, idTratament, dataTratament,anulare);
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public Date getDataTratament() {
        return dataTratament;
    }

    public void setDataTratament(Date dataTratament) {
        this.dataTratament = dataTratament;
    }

    public int getAnulare() {
        return anulare;
    }

    public void setAnulare(int anulare) {
        this.anulare = anulare;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }
}

