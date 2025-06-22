package phenrique.com.A3.Medlink.api.model.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MedicoInputDTO {
    
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

    @NotBlank(message = "Digite seu CRM")
    @Size(min = 10, max = 10, message = "CRM deve ser válido")
    String crm;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
}
}
