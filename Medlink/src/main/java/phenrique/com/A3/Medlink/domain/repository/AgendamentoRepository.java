package phenrique.com.A3.Medlink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phenrique.com.A3.Medlink.domain.entities.Agendamento;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.entities.Paciente;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByPaciente(Paciente paciente);
    List<Agendamento> findByMedico(Medico medico);
    Agendamento findByLinkSala(String linkSala);
    List<Agendamento> findByMedicoAndDataHoraBetween(Medico medico, LocalDateTime start, LocalDateTime end);
}
