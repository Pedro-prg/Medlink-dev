package phenrique.com.A3.Medlink.api.model.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PacienteInputDTO {
    
    @NotBlank(message = "Digite seu nome")
    @Size(min = 3, max = 50)
    private String nome;

    

    @NotBlank(message = "Digite seu Email")
    @Email(message = "Tem que ser um email válido")
    private String email;

    @NotBlank(message = "A senha tem que possuir ao minimo 8 caracteres")
    @Size(min = 8, max = 50, message = "50 é o maximo de caracteres disponível ")
    private String senha;

    String telefone;


    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

}
