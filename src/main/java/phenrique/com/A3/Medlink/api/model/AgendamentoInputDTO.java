package phenrique.com.A3.Medlink.api.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoInputDTO {
    
    @NotNull(message = "ID do médico é obrigatório")
    private Long medicoId;
    
    @NotNull(message = "ID do paciente é obrigatório")
    private Long pacienteId;
    
    @NotNull(message = "Data/hora é obrigatória")
    @Future(message = "Data deve ser futura")
    private LocalDateTime dataHora;
    
    @Size(max = 255)
    private String observacoes;
}
