package phenrique.com.A3.Medlink.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import phenrique.com.A3.Medlink.domain.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByEmail(String email);

}