package br.com.gtbr.domain.registro;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@Entity
@Builder
@Table(name = "registros")
@NoArgsConstructor
@AllArgsConstructor
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String data;
    private String tarefa;

    public Registro(DadosRegistro dadosRegistro){
        this.nome = dadosRegistro.nome();
        this.data = dadosRegistro.data();
        this.tarefa = dadosRegistro.tarefa();
    }
}
