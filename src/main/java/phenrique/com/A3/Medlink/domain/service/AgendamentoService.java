package phenrique.com.A3.Medlink.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phenrique.com.A3.Medlink.domain.entities.Agendamento;
import phenrique.com.A3.Medlink.domain.entities.DisponibilidadeMedico;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.entities.Paciente;
import phenrique.com.A3.Medlink.domain.repository.AgendamentoRepository;
import phenrique.com.A3.Medlink.domain.repository.DisponibilidadeMedicoRepository;
<<<<<<< HEAD


import java.time.LocalDateTime;
import java.util.List;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import phenrique.com.A3.Medlink.api.common.ValidationUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalTime;
>>>>>>> dev

@Service
public class AgendamentoService {

<<<<<<< HEAD
=======
    private static final Logger logger = LoggerFactory.getLogger(AgendamentoService.class);

>>>>>>> dev
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private DisponibilidadeMedicoRepository disponibilidadeMedicoRepository;

    public Agendamento agendar(Paciente paciente, Medico medico, LocalDateTime dataHora) {
<<<<<<< HEAD
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

=======
        logger.info("Tentando agendar para pacienteId: {}, medicoId: {}, dataHora: {}", paciente.getId(), medico.getId(), dataHora);
        if (dataHora.isBefore(LocalDateTime.now())) {
            logger.warn("Tentativa de agendamento em data passada bloqueada: {}", dataHora);
            throw new IllegalArgumentException("Não é possível agendar consultas em datas ou horários passados.");
        }
        String diaSemanaPt = ValidationUtils.dayOfWeekToDbString(dataHora.getDayOfWeek());
        
        LocalTime horaAgendamento = dataHora.toLocalTime();

        List<DisponibilidadeMedico> disponiveis = disponibilidadeMedicoRepository.findByMedico(medico);
        boolean disponivel = disponiveis.stream().anyMatch(d -> {
            if (!d.getDiaSemana().equalsIgnoreCase(diaSemanaPt)) {
                return false;
            }
            try {
                LocalTime horaInicio = LocalTime.parse(d.getHoraInicio());
                LocalTime horaFim = LocalTime.parse(d.getHoraFim());
                return !horaAgendamento.isBefore(horaInicio) && horaAgendamento.isBefore(horaFim);
            } catch (Exception e) {
                logger.error("Formato de hora inválido para disponibilidade ID: {}", d.getId(), e);
                return false;
            }
        });

        if (!disponivel) {
            logger.warn("Médico {} não disponível para o horário solicitado: {} {}", medico.getId(), diaSemanaPt, horaAgendamento);
            throw new IllegalArgumentException("O médico não está disponível neste dia e horário.");
        }

        logger.info("Horário validado como disponível. Criando agendamento.");
>>>>>>> dev
        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(paciente);
        agendamento.setMedico(medico);
        agendamento.setDataHora(dataHora);
        agendamento.setLinkSala("http://localhost:8080/sala/" + java.util.UUID.randomUUID().toString().substring(0,8));
<<<<<<< HEAD
        return agendamentoRepository.save(agendamento);
=======
        Agendamento salvo = agendamentoRepository.save(agendamento);
        logger.info("Agendamento criado com sucesso com id: {}", salvo.getId());
        return salvo;
>>>>>>> dev
    }

    public List<Agendamento> listarPorPaciente(Paciente paciente) {
        return agendamentoRepository.findByPaciente(paciente);
    }

    public List<Agendamento> listarPorMedico(Medico medico) {
        return agendamentoRepository.findByMedico(medico);
    }
}
