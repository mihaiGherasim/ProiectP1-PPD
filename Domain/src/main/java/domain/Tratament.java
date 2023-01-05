package domain;

import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ToString
public class Tratament implements Serializable {
    private int idTratament;
    private int cost;
    private int durataMinute;
    private int maxProgaramari;
    //private int idLocatie;


//    public int getClientiSimultan(){
//        List<Integer> clientiSimultan = Arrays.asList(3, 1, 1, 2, 1);
//        if(idLocatie == 1){
//            //id-urile tratamentelor si al locatiilor in baza de date pornesc de la 1
//            return clientiSimultan.get(idTratament-1);
//        }
//        return clientiSimultan.get(idTratament-1)*(idLocatie-1);
//    }


    public Tratament(int idTratament, int cost, int durataMinute, int maxProgaramari) {
        this.idTratament = idTratament;
        this.cost = cost;
        this.durataMinute = durataMinute;
        this.maxProgaramari = maxProgaramari;
    }

    public int getIdTratament() {
        return idTratament;
    }

    public void setIdTratament(int idTratament) {
        this.idTratament = idTratament;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDurataMinute() {
        return durataMinute;
    }

    public void setDurataMinute(int durataMinute) {
        this.durataMinute = durataMinute;
    }

    public int getMaxProgaramari() {
        return maxProgaramari;
    }

    public void setMaxProgaramari(int maxProgaramari) {
        this.maxProgaramari = maxProgaramari;
    }
}