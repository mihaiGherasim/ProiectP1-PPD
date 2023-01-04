package domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Plata implements Serializable {
    private Date data;
    private String cnp;
    private int suma;
    private int idProgramare;
}
