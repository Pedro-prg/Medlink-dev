package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import phenrique.com.A3.Medlink.domain.entities.Agendamento;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.entities.Paciente;
import phenrique.com.A3.Medlink.domain.repository.MedicoRepository;
import phenrique.com.A3.Medlink.domain.repository.PacienteRepository;
import phenrique.com.A3.Medlink.domain.service.AgendamentoService;

import java.util.List;

@Controller
public class HistoricoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    // Histórico do paciente
    @GetMapping("/historico/paciente")
    public String historicoPaciente(@RequestParam Long pacienteId, Model model) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
        if (paciente == null) return "redirect:/login";
        List<Agendamento> historico = agendamentoService.listarPorPaciente(paciente)
                .stream()
                .filter(Agendamento::isFinalizado)
                .toList();
        model.addAttribute("pacienteId", pacienteId);
        model.addAttribute("historico", historico);
        return "historico-paciente";
    }

    // Histórico do médico
    @GetMapping("/historico/medico")
    public String historicoMedico(@RequestParam Long medicoId, Model model) {
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        if (medico == null) return "redirect:/login";
        List<Agendamento> historico = agendamentoService.listarPorMedico(medico)
                .stream()
                .filter(Agendamento::isFinalizado)
                .toList();
        model.addAttribute("medicoId", medicoId);
        model.addAttribute("historico", historico);
        return "historico-medico";
    }
}
