package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.domain.entities.Agendamento;
import phenrique.com.A3.Medlink.domain.entities.DisponibilidadeMedico;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.entities.Paciente;
import phenrique.com.A3.Medlink.domain.repository.AgendamentoRepository;
import phenrique.com.A3.Medlink.domain.repository.DisponibilidadeMedicoRepository;
import phenrique.com.A3.Medlink.domain.repository.MedicoRepository;
import phenrique.com.A3.Medlink.domain.repository.PacienteRepository;
import phenrique.com.A3.Medlink.domain.service.AgendamentoService;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.time.LocalDate;
import java.time.DayOfWeek;
import phenrique.com.A3.Medlink.api.common.ValidationUtils;

@Controller
public class ReuniaoController {

    private static final Logger logger = LoggerFactory.getLogger(ReuniaoController.class);

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private DisponibilidadeMedicoRepository disponibilidadeMedicoRepository;

    @GetMapping("/reunioes")
    public String listarReunioes(@RequestParam(required = false) Long pacienteId,
                                 @RequestParam(required = false) Long medicoId,
                                 Model model) {
        if (pacienteId != null) {
            Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
            if (paciente == null) return "redirect:/login";
            List<Agendamento> reunioes = agendamentoService.listarPorPaciente(paciente)
                    .stream()
                    .filter(r -> !r.isFinalizado())
                    .toList();
            model.addAttribute("reunioes", reunioes);
            model.addAttribute("pacienteId", pacienteId);
            return "reunioes-paciente";
        } else if (medicoId != null) {
            Medico medico = medicoRepository.findById(medicoId).orElse(null);
            if (medico == null) return "redirect:/login";
            List<Agendamento> reunioes = agendamentoService.listarPorMedico(medico)
                    .stream()
                    .filter(r -> !r.isFinalizado())
                    .toList();
            model.addAttribute("reunioes", reunioes);
            model.addAttribute("medicoId", medicoId);
            return "reunioes-medico";
        }
        return "redirect:/login";
    }

    @GetMapping("/agendar")
    public String agendarFormSimples(@RequestParam Long pacienteId,
                                     @RequestParam(required = false) Long medicoId,
                                     @RequestParam(required = false) String data, // yyyy-MM-dd
                                     Model model) {
        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);
        model.addAttribute("pacienteId", pacienteId);
        model.addAttribute("medicoId", medicoId);
        model.addAttribute("data", data);
        List<LocalDateTime> horariosDisponiveis = new ArrayList<>();
        Medico medico = null;
        if (medicoId != null) {
            medico = medicoRepository.findById(medicoId).orElse(null);
            model.addAttribute("medico", medico);
        }
        if (medico != null && data != null && !data.isEmpty()) {
            // Buscar disponibilidades do médico para o dia da semana
            List<DisponibilidadeMedico> disponibilidades = disponibilidadeMedicoRepository.findByMedico(medico);
            LocalDateTime dataBase = LocalDateTime.parse(data + "T00:00:00");
            String diaSemanaPt = dataBase.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase();
            if (diaSemanaPt.equals("TERÇA")) diaSemanaPt = "TERCA";
            if (diaSemanaPt.equals("SÁBADO")) diaSemanaPt = "SABADO";
            // Filtrar disponibilidades para o dia
            for (DisponibilidadeMedico disp : disponibilidades) {
                if (disp.getDiaSemana().equalsIgnoreCase(diaSemanaPt)) {
                    try {
                        LocalTime inicio = LocalTime.parse(disp.getHoraInicio());
                        LocalTime fim = LocalTime.parse(disp.getHoraFim());
                        LocalTime hora = inicio;

                        // CORREÇÃO: Loop simplificado e correto para gerar horários
                        while (hora.isBefore(fim)) {
                            LocalDateTime horario = dataBase.withHour(hora.getHour()).withMinute(hora.getMinute());

                            // Verifica se já existe agendamento nesse horário
                            boolean ocupado = agendamentoRepository.findByMedico(medico).stream().anyMatch(a ->
                                a.getDataHora().toLocalDate().equals(horario.toLocalDate()) &&
                                Math.abs(Duration.between(a.getDataHora(), horario).toMinutes()) < 60
                            );

                            if (!ocupado) {
                                horariosDisponiveis.add(horario);
                            }
                            hora = hora.plusHours(1); // Incrementa de 1 em 1 hora
                        }
                    } catch (Exception e) {
                        logger.error("Erro ao processar disponibilidade para o médico {}: {}", medico.getId(), e.getMessage());
                        // ignora erro de parse para não quebrar a página
                    }
                }
            }
        }
        model.addAttribute("horariosDisponiveis", horariosDisponiveis);
        return "agendar";
    }

    @PostMapping("/agendar")
    public String agendarSimples(@RequestParam Long pacienteId,
                                 @RequestParam Long medicoId,
                                 @RequestParam(required = false) String data,
                                 @RequestParam(required = false) String hora,
                                 @RequestParam(required = false) String action,
                                 Model model) {
        if ("selecionar".equals(action)) {
            // Apenas recarrega o formulário com os dados atuais
            return agendarFormSimples(pacienteId, medicoId, data, model);
        }
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        if (paciente == null || medico == null) {
            model.addAttribute("erro", "Paciente ou médico não encontrado.");
            return "redirect:/agendar?pacienteId=" + pacienteId;
        }
        String dataHoraStr = data + "T" + hora;
        try {
            LocalDateTime dh = LocalDateTime.parse(dataHoraStr);
            agendamentoService.agendar(paciente, medico, dh);
            return "redirect:/reunioes?pacienteId=" + pacienteId;
        } catch (IllegalArgumentException e) {
            // Se não conseguir, retorna erro e horários disponíveis para aquele dia
            model.addAttribute("erro", e.getMessage());
            return agendarFormSimples(pacienteId, medicoId, data, model);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao agendar: " + e.getMessage());
            return agendarFormSimples(pacienteId, medicoId, data, model);
        }
    }

    // Endpoint temporário para debug
    @GetMapping("/debug/disponibilidades")
    @ResponseBody
    public String debugDisponibilidades() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DEBUG DISPONIBILIDADES ===\n");
        
        List<Medico> medicos = medicoRepository.findAll();
        sb.append("Total de médicos: ").append(medicos.size()).append("\n");
        
        for (Medico medico : medicos) {
            sb.append("\nMédico: ").append(medico.getNome()).append(" (ID: ").append(medico.getId()).append(")\n");
            List<DisponibilidadeMedico> disponibilidades = disponibilidadeMedicoRepository.findByMedico(medico);
            sb.append("Disponibilidades: ").append(disponibilidades.size()).append("\n");
            
            for (DisponibilidadeMedico disp : disponibilidades) {
                sb.append("  - ").append(disp.getDiaSemana())
                  .append(": ").append(disp.getHoraInicio())
                  .append(" às ").append(disp.getHoraFim()).append("\n");
            }
        }
        
        return sb.toString();
    }

    // Endpoint para testar geração de horários
    @GetMapping("/debug/gerar-horarios/{medicoId}")
    @ResponseBody
    public String testarGeracaoHorarios(@PathVariable Long medicoId) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TESTE GERAÇÃO DE HORÁRIOS ===\n");
        
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        if (medico == null) {
            return "Médico não encontrado";
        }
        
        sb.append("Médico: ").append(medico.getNome()).append("\n");
        
        List<DisponibilidadeMedico> disponibilidades = disponibilidadeMedicoRepository.findByMedico(medico);
        sb.append("Disponibilidades: ").append(disponibilidades.size()).append("\n");
        
        if (disponibilidades.isEmpty()) {
            return sb.toString() + "\nNenhuma disponibilidade encontrada!";
        }
        
        LocalDateTime agora = LocalDateTime.now();
        sb.append("Data/hora atual: ").append(agora).append("\n\n");
        
        // Testa a geração de horários para os próximos 7 dias
        for (int dia = 0; dia < 7; dia++) {
            LocalDateTime data = agora.plusDays(dia).withMinute(0).withSecond(0).withNano(0);
            String diaSemanaPt = data.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase();
            
            if (diaSemanaPt.equals("TERÇA")) diaSemanaPt = "TERCA";
            if (diaSemanaPt.equals("SÁBADO")) diaSemanaPt = "SABADO";
            
            sb.append("Dia ").append(dia + 1).append(": ").append(data.toLocalDate())
              .append(" (").append(diaSemanaPt).append(")\n");
            
            for (DisponibilidadeMedico disp : disponibilidades) {
                if (disp.getDiaSemana().equalsIgnoreCase(diaSemanaPt)) {
                    sb.append("  ✓ Disponível: ").append(disp.getHoraInicio())
                      .append(" às ").append(disp.getHoraFim()).append("\n");
                    
                    try {
                        LocalTime inicio = LocalTime.parse(disp.getHoraInicio());
                        LocalTime fim = LocalTime.parse(disp.getHoraFim());
                        
                        LocalTime hora = inicio;
                        while (!hora.isAfter(fim.minusMinutes(59))) {
                            LocalDateTime horario = data.withHour(hora.getHour()).withMinute(hora.getMinute());
                            if (horario.isAfter(agora)) {
                                sb.append("    - ").append(horario).append("\n");
                            }
                            hora = hora.plusHours(1);
                        }
                    } catch (Exception e) {
                        sb.append("    ERRO: ").append(e.getMessage()).append("\n");
                    }
                } else {
                    sb.append("  ✗ Não disponível: ").append(disp.getDiaSemana()).append("\n");
                }
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    // Endpoint para testar o template com dados simples
    @GetMapping("/teste-template")
    public String testeTemplate(Model model) {
        System.out.println("=== TESTE TEMPLATE ===");
        
        // Cria dados de teste simples
        List<LocalDateTime> horariosTeste = new ArrayList<>();
        LocalDateTime agora = LocalDateTime.now();
        
        // Adiciona alguns horários de teste
        for (int i = 1; i <= 5; i++) {
            horariosTeste.add(agora.plusDays(i).withHour(9).withMinute(0).withSecond(0).withNano(0));
            horariosTeste.add(agora.plusDays(i).withHour(10).withMinute(0).withSecond(0).withNano(0));
            horariosTeste.add(agora.plusDays(i).withHour(11).withMinute(0).withSecond(0).withNano(0));
        }
        
        System.out.println("Horários de teste criados: " + horariosTeste.size());
        for (LocalDateTime horario : horariosTeste) {
            System.out.println("  - " + horario);
        }
        
        // Cria um médico de teste
        Medico medicoTeste = new Medico();
        medicoTeste.setId(1L);
        medicoTeste.setNome("Dr. Teste");
        medicoTeste.setCrm("CRM12345");
        
        model.addAttribute("pacienteId", 1L);
        model.addAttribute("medicoId", 1L);
        model.addAttribute("horariosDisponiveis", horariosTeste);
        model.addAttribute("medico", medicoTeste);
        
        return "agendar";
    }

    @PostMapping("/reunioes/finalizar")
    public String finalizarReuniao(@RequestParam Long agendamentoId,
                                   @RequestParam(required = false) Long pacienteId,
                                   @RequestParam(required = false) Long medicoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElse(null);
        if (agendamento != null && !agendamento.isFinalizado()) {
            agendamento.setFinalizado(true);
            agendamentoRepository.save(agendamento);
        }
        if (pacienteId != null) {
            return "redirect:/reunioes?pacienteId=" + pacienteId;
        } else if (medicoId != null) {
            return "redirect:/reunioes?medicoId=" + medicoId;
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/reunioes/excluir")
    public String excluirReuniao(@RequestParam Long agendamentoId,
                                 @RequestParam Long pacienteId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElse(null);
        if (agendamento != null && agendamento.getPaciente() != null && agendamento.getPaciente().getId().equals(pacienteId)) {
            agendamentoRepository.delete(agendamento);
        }
        return "redirect:/reunioes?pacienteId=" + pacienteId;
    }

    @GetMapping("/api/horarios-disponiveis")
    @ResponseBody
    public List<String> getHorariosDisponiveis(@RequestParam Long medicoId, @RequestParam String data) {
        logger.info("Buscando horários para medicoId: {} e data: {}", medicoId, data);
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        List<LocalDateTime> horarios = new ArrayList<>();

        if (medico != null && data != null && !data.isEmpty()) {
            try {
                LocalDateTime dataSelecionada = LocalDate.parse(data).atStartOfDay();
                DayOfWeek dayOfWeek = dataSelecionada.getDayOfWeek();
                String diaSemanaDb = ValidationUtils.dayOfWeekToDbString(dayOfWeek);
                logger.info("Data {} corresponde a {}", data, diaSemanaDb);

                List<DisponibilidadeMedico> disponibilidades = disponibilidadeMedicoRepository.findByMedico(medico);
                List<Agendamento> agendamentosNoDia = agendamentoRepository.findByMedicoAndDataHoraBetween(medico, dataSelecionada, dataSelecionada.plusDays(1).minusNanos(1));
                logger.info("Médico {} tem {} agendamentos na data {}", medico.getId(), agendamentosNoDia.size(), data);

                for (DisponibilidadeMedico disp : disponibilidades) {
                    if (disp.getDiaSemana().equalsIgnoreCase(diaSemanaDb)) {
                        LocalTime inicio = LocalTime.parse(disp.getHoraInicio());
                        LocalTime fim = LocalTime.parse(disp.getHoraFim());
                        LocalTime hora = inicio;

                        while (hora.isBefore(fim)) {
                            LocalDateTime horario = dataSelecionada.withHour(hora.getHour()).withMinute(hora.getMinute());
                            
                            boolean ocupado = agendamentosNoDia.stream().anyMatch(a ->
                                Math.abs(Duration.between(a.getDataHora(), horario).toMinutes()) < 60
                            );

                            if (!ocupado && horario.isAfter(LocalDateTime.now())) {
                                horarios.add(horario);
                            }
                            hora = hora.plusHours(1);
                        }
                    }
                }
                 logger.info("Encontrados {} horários disponíveis.", horarios.size());
            } catch (Exception e) {
                logger.error("Erro ao buscar horários para medicoId: " + medicoId, e);
            }
        }
        return horarios.stream()
                .map(h -> h.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")))
                .collect(Collectors.toList());
    }
}