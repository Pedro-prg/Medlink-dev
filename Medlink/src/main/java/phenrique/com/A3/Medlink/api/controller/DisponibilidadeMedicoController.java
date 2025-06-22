package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.domain.entities.DisponibilidadeMedico;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.repository.DisponibilidadeMedicoRepository;
import phenrique.com.A3.Medlink.domain.repository.MedicoRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/medico/disponibilidade")
public class DisponibilidadeMedicoController {

    @Autowired
    private DisponibilidadeMedicoRepository disponibilidadeRepo;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public String form(@RequestParam Long medicoId, Model model) {
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        List<DisponibilidadeMedico> disponibilidades = disponibilidadeRepo.findByMedico(medico);
        model.addAttribute("medicoId", medicoId);
        model.addAttribute("disponibilidades", disponibilidades);
        // Gerar horários exatos para cada disponibilidade
        Map<Long, List<String>> horariosPorDisponibilidade = new LinkedHashMap<>();
        for (DisponibilidadeMedico disp : disponibilidades) {
            List<String> horarios = new java.util.ArrayList<>();
            try {
                LocalTime inicio = LocalTime.parse(disp.getHoraInicio());
                LocalTime fim = LocalTime.parse(disp.getHoraFim());
                if (fim.isAfter(inicio)) {
                    LocalTime hora = inicio;
                    while (!hora.isAfter(fim.minusMinutes(59))) {
                        horarios.add(hora.toString());
                        hora = hora.plusHours(1);
                    }
                } // else: intervalo inválido, não gera horários
            } catch (Exception e) {
                // ignora erro de parse
            }
            horariosPorDisponibilidade.put(disp.getId(), horarios);
        }
        model.addAttribute("horariosPorDisponibilidade", horariosPorDisponibilidade);
        return "disponibilidade-medico";
    }

    @PostMapping
    public String salvar(@RequestParam Long medicoId,
                         @RequestParam String diaSemana,
                         @RequestParam String horaInicio,
                         @RequestParam String horaFim) {
        Medico medico = medicoRepository.findById(medicoId).orElse(null);
        if (medico != null) {
            DisponibilidadeMedico disp = new DisponibilidadeMedico();
            disp.setMedico(medico);
            disp.setDiaSemana(diaSemana);
            disp.setHoraInicio(horaInicio);
            disp.setHoraFim(horaFim);
            disponibilidadeRepo.save(disp);
        }
        return "redirect:/medico/disponibilidade?medicoId=" + medicoId;
    }

    @PostMapping("/remover")
    public String remover(@RequestParam Long id, @RequestParam(required = false) Long medicoId, Model model) {
        if (medicoId == null) {
            model.addAttribute("erro", "Médico não identificado.");
            return "erro";
        }
        try {
            disponibilidadeRepo.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao remover disponibilidade: " + e.getMessage());
            model.addAttribute("medicoId", medicoId);
            // Retorna para a tela de disponibilidade com mensagem de erro
            Medico medico = medicoRepository.findById(medicoId).orElse(null);
            List<DisponibilidadeMedico> disponibilidades = disponibilidadeRepo.findByMedico(medico);
            model.addAttribute("disponibilidades", disponibilidades);
            return "disponibilidade-medico";
        }
        return "redirect:/medico/disponibilidade?medicoId=" + medicoId;
    }
}