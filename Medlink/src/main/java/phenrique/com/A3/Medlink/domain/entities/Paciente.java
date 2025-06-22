package phenrique.com.A3.Medlink.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "paciente")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
}
