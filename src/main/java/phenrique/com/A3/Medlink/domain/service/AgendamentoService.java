package phenrique.com.A3.Medlink.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phenrique.com.A3.Medlink.domain.entities.Agendamento;
import phenrique.com.A3.Medlink.domain.entities.DisponibilidadeMedico;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.entities.Paciente;
import phenrique.com.A3.Medlink.domain.repository.AgendamentoRepository;
import phenrique.com.A3.Medlink.domain.repository.DisponibilidadeMedicoRepository;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private DisponibilidadeMedicoRepository disponibilidadeMedicoRepository;

    public Agendamento agendar(Paciente paciente, Medico medico, LocalDateTime dataHora) {
        String rawDiaSemana = dataHora.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("pt", "BR")).toUpperCase();
        String diaSemanaPt = rawDiaSemana.equals("TERÇA") ? "TERCA" :
                             rawDiaSemana.equals("SÁBADO") ? "SABADO" : rawDiaSemana;
        String hora = dataHora.toLocalTime().toString();

        List<DisponibilidadeMedico> disponiveis = disponibilidadeMedicoRepository.findByMedico(medico);
        boolean disponivel = disponiveis.stream().anyMatch(d ->
            d.getDiaSemana().equalsIgnoreCase(diaSemanaPt)
            && hora.compareTo(d.getHoraInicio()) >= 0
            && hora.compareTo(d.getHoraFim()) <= 0
        );

        if (!disponivel) {
            throw new IllegalArgumentException("O médico não está disponível neste dia e horário.");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(paciente);
        agendamento.setMedico(medico);
        agendamento.setDataHora(dataHora);
        agendamento.setLinkSala("http://localhost:8080/sala/" + java.util.UUID.randomUUID().toString().substring(0,8));
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarPorPaciente(Paciente paciente) {
        return agendamentoRepository.findByPaciente(paciente);
    }

    public List<Agendamento> listarPorMedico(Medico medico) {
        return agendamentoRepository.findByMedico(medico);
    }
}
