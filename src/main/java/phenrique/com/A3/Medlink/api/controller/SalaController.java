package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.domain.repository.AgendamentoRepository;
import phenrique.com.A3.Medlink.domain.entities.Agendamento;

@Controller
public class SalaController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping("/sala/{codigo}")
    public String sala(@PathVariable String codigo,
                       @RequestParam(required = false) String pacienteId,
                       @RequestParam(required = false) String medicoId,
                       Model model) {
        model.addAttribute("codigo", codigo);
        if (medicoId != null && !medicoId.equals("null") && !medicoId.isEmpty()) {
            model.addAttribute("medicoId", medicoId);
            Agendamento agendamento = agendamentoRepository.findByLinkSala("http://localhost:8080/sala/" + codigo);
            if (agendamento != null) {
                model.addAttribute("agendamentoId", agendamento.getId());
            }
            return "sala-medico";
        } else if (pacienteId != null && !pacienteId.equals("null") && !pacienteId.isEmpty()) {
            model.addAttribute("pacienteId", pacienteId);
            return "sala-paciente";
        } else {
            return "redirect:/login";
        }
    }
}
