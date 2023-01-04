package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tratament {
    private int idTratament;
    private int cost;
    private int durataMinute;
    private int idLocatie;


    public int getClientiSimultan(){
        List<Integer> clientiSimultan = Arrays.asList(3, 1, 1, 2, 1);
        if(idLocatie == 1){
            //id-urile tratamentelor si al locatiilor in baza de date pornesc de la 1
            return clientiSimultan.get(idTratament-1);
        }
        return clientiSimultan.get(idTratament-1)*(idLocatie-1);
    }



}
