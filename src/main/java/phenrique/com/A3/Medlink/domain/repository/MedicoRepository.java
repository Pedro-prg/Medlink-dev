package phenrique.com.A3.Medlink.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import phenrique.com.A3.Medlink.domain.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByEmail(String email);
}