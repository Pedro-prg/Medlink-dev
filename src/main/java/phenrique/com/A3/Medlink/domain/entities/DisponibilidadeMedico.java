package phenrique.com.A3.Medlink.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "disponibilidade_medico")
public class DisponibilidadeMedico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Medico medico;

    private String diaSemana; // Ex: "SEGUNDA", "TERCA", etc.
    private String horaInicio; // Ex: "08:00"
    private String horaFim;    // Ex: "17:00"
}
