package phenrique.com.A3.Medlink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phenrique.com.A3.Medlink.domain.entities.DisponibilidadeMedico;
import phenrique.com.A3.Medlink.domain.entities.Medico;

import java.util.List;

public interface DisponibilidadeMedicoRepository extends JpaRepository<DisponibilidadeMedico, Long> {
    List<DisponibilidadeMedico> findByMedico(Medico medico);
    
    @Query("SELECT d FROM DisponibilidadeMedico d WHERE d.medico = :medico AND d.diaSemana = :diaSemana")
    List<DisponibilidadeMedico> findByMedicoAndDiaSemana(@Param("medico") Medico medico, @Param("diaSemana") String diaSemana);
    
    @Query("SELECT COUNT(d) FROM DisponibilidadeMedico d WHERE d.medico = :medico")
    long countByMedico(@Param("medico") Medico medico);
}