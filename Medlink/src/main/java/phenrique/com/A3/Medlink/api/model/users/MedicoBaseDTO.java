package phenrique.com.A3.Medlink.api.model.users;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoBaseDTO {
    
    @NotNull
    private Long id;

}
