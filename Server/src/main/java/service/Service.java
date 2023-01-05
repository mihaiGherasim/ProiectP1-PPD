package service;

import domain.Programare;
import domain.Tratament;
import domain.TratamentLocatie;
import repository.RepoTratament;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Service {

    RepoTratament repoTratament;
    List<Tratament> tratamente;
    int cereriProcesate=0;

    public Service(){
        repoTratament = new RepoTratament();
        try {
            tratamente = repoTratament.getAllTratamente();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public boolean verificaProgramare(Programare programare) throws IOException {
        cereriProcesate++;
        System.out.println(cereriProcesate);
        int idTratament = programare.getIdTratament();
        int idLocatie = programare.getIdLocatie();

        List<TratamentLocatie> tramenteLocatii = repoTratament.getAllTratamentLocatiiCurente();
        int max = -1;
        for(Tratament tratament : tratamente){
            if(tratament.getIdTratament() == idTratament){
                if(idLocatie == 1) {
                    max = tratament.getMaxProgaramari();
                }
                else{
                    max = tratament.getMaxProgaramari() * (idLocatie - 1);
                }
            }
        }
        for(TratamentLocatie tratamentLocatie:tramenteLocatii){
            if(tratamentLocatie.getIdLocatie() == idLocatie && tratamentLocatie.getIdTratament() == idTratament && tratamentLocatie.getOra() == programare.getData().getHours()){
                if(tratamentLocatie.getLocuriOcupate() < max) {
                    tratamentLocatie.setLocuriOcupate(tratamentLocatie.getLocuriOcupate()+1);
                    repoTratament.updateLocuriOcupate(tramenteLocatii);
                    return true;
                }
            }
        }
        return false;
    }
}
