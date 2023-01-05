package repository;

import domain.Tratament;
import domain.TratamentLocatie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RepoTratament {
    private String fisierTratamente = "src/main/resources/tratament.txt";
    private String fisierTratamentLocatie = "src/main/resources/tratament-locatie.txt";

    public RepoTratament(){
        try {
            initializareLocuriOcupate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tratament> getAllTratamente() throws FileNotFoundException {
        File file = new File(fisierTratamente);
        Scanner reader = new Scanner(file);
        List<Tratament> tratamente = new ArrayList<>();
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] s = data.split(" ");
            Tratament tratament = new Tratament(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
            tratamente.add(tratament);
        }
        return tratamente;
    }

    private void initializareLocuriOcupate() throws IOException {
        FileWriter fileWriter = new FileWriter(fisierTratamentLocatie);
        for(Tratament tratament: getAllTratamente()){
            for(int i=1; i<=5; i++){
                for(int j=10; j<=18; j++) {
                    fileWriter.write(tratament.getIdTratament() + " " + i + " " + j + " " + 0 + "\n");
                }
            }
        }
        fileWriter.close();
    }

    public List<TratamentLocatie> getAllTratamentLocatiiCurente() throws FileNotFoundException {
        File file = new File(fisierTratamentLocatie);
        Scanner reader = new Scanner(file);
        List<TratamentLocatie> tratamenteLocatii = new ArrayList<>();
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            if(!data.equals("")) {
                String[] s = data.split(" ");
                TratamentLocatie tratamentLocatie = new TratamentLocatie(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
                tratamenteLocatii.add(tratamentLocatie);
            }
        }
        return tratamenteLocatii;
    }

    public void updateLocuriOcupate(List<TratamentLocatie> tramenteLocatii) throws IOException {
        FileWriter fileWriter = new FileWriter(fisierTratamentLocatie);
        for(TratamentLocatie tratamentLocatie : tramenteLocatii){
            fileWriter.write(tratamentLocatie.getIdLocatie()+" "+tratamentLocatie.getIdTratament() + " " + tratamentLocatie.getOra() +" "+tratamentLocatie.getLocuriOcupate()+"\n");
        }
        fileWriter.close();
    }
}
