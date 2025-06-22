package phenrique.com.A3.Medlink.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "medico")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String crm;
    // outros campos específicos do médico
    private String especialidades;
    private String experiencias;
    @Column(name = "trabalhos_passados")
    private String trabalhosPassados;
}
