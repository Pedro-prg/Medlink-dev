package phenrique.com.A3.Medlink.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoBaseDTO {
    
    @NotNull
    Long id;

}
