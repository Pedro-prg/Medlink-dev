package phenrique.com.A3.Medlink.api.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoOutputDTO {
 
        private Long id;
    private String nomeMedico;
    private String nomePaciente;
    private LocalDateTime dataHora;
    private String linkConsulta;
    private boolean confirmado;
    
}
