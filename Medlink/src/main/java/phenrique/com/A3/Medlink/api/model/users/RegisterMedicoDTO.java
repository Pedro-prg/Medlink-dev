package phenrique.com.A3.Medlink.api.model.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterMedicoDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;
    
    @NotBlank(message = "CRM é obrigatório")
    @Pattern(regexp = "^\\d{1,6}$", message = "CRM deve conter apenas números (1 a 6 dígitos)")
    private String crm;
    
    @NotBlank(message = "Especialidade é obrigatória")
    @Size(min = 2, max = 50, message = "Especialidade deve ter entre 2 e 50 caracteres")
    private String especialidade;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone deve estar no formato (00) 00000-0000")
    private String telefone;
    
    @NotBlank(message = "Endereço é obrigatório")
    @Size(min = 10, max = 200, message = "Endereço deve ter entre 10 e 200 caracteres")
    private String endereco;
    
    // Construtores
    public RegisterMedicoDTO() {}
    
    public RegisterMedicoDTO(String nome, String crm, String especialidade, String email, String telefone, String endereco) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCrm() {
        return crm;
    }
    
    public void setCrm(String crm) {
        this.crm = crm;
    }
    
    public String getEspecialidade() {
        return especialidade;
    }
    
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    // Método para obter CRM formatado
    public String getCrmFormatado() {
        return crm;
    }
    
    @Override
    public String toString() {
        return "RegisterMedicoDTO{" +
                "nome='" + nome + '\'' +
                ", crm='" + crm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
} 