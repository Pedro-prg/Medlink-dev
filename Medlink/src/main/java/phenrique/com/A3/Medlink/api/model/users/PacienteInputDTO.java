package phenrique.com.A3.Medlink.api.model.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PacienteInputDTO {
    
    @NotBlank(message = "Digite seu nome")
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Digite seu Email")
    @Email(message = "Tem que ser um email válido")
    private String email;

    @NotBlank(message = "Digite sua senha")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String senha;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
