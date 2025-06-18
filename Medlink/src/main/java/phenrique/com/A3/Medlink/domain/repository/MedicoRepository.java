package phenrique.com.A3.Medlink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phenrique.com.A3.Medlink.domain.entities.Medico;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Buscar médico por CRM
    Optional<Medico> findByCrm(String crm);
    
    // Buscar médicos por nome contendo o texto (case insensitive)
    List<Medico> findByNomeContainingIgnoreCase(String nome);
    
    // Verificar se existe médico com determinado CRM
    boolean existsByCrm(String crm);
    
    // Verificar se existe médico com determinado email
    boolean existsByEmail(String email);
}