package phenrique.com.A3.Medlink.domain.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "A data de nascimento é obrigatória")
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    private String telefone;

    @NotBlank(message = "O email é obrigatório")
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Column(name = "senha", nullable = false)
    private String senha;

    public Paciente(long id, String nome, String cpf, String telefone, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }
}
