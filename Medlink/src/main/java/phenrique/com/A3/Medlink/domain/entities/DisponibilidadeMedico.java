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
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @Column(name = "dia_semana")
    private String diaSemana; // Ex: "SEGUNDA", "TERCA", etc.
    
    @Column(name = "hora_inicio")
    private String horaInicio; // Ex: "08:00"
    
    @Column(name = "hora_fim")
    private String horaFim;    // Ex: "17:00"
}
