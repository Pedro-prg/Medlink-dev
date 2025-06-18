package phenrique.com.A3.Medlink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import phenrique.com.A3.Medlink.domain.entities.Paciente;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Define methods for Paciente repository operations
    Optional<Paciente> findByEmail(String email);
    List<Paciente> findByNome(String nome);

    }


