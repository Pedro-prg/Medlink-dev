package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.repository.MedicoRepository;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/{id}")
    public String perfilMedico(@PathVariable Long id, @RequestParam(required = false) Long medicoId, Model model) {
        Medico medico = medicoRepository.findById(id).orElse(null);
        if (medico == null) {
            model.addAttribute("erro", "Médico não encontrado.");
            return "redirect:/reunioes";
        }
        model.addAttribute("medico", medico);
        boolean isMedicoLogado = (medicoId != null && medicoId.equals(id));
        model.addAttribute("isMedicoLogado", isMedicoLogado);
        return "perfil-medico";
    }

    @GetMapping("/{id}/editar")
    public String editarPerfil(@PathVariable Long id, Model model) {
        Medico medico = medicoRepository.findById(id).orElse(null);
        if (medico == null) {
            model.addAttribute("erro", "Médico não encontrado.");
            return "redirect:/reunioes";
        }
        model.addAttribute("medico", medico);
        return "especialidades-medico";
    }

    @PostMapping("/{id}/editar")
    public String salvarPerfil(@PathVariable Long id,
                              @RequestParam String especialidades,
                              @RequestParam String experiencias,
                              @RequestParam String trabalhosPassados,
                              Model model) {
        Medico medico = medicoRepository.findById(id).orElse(null);
        if (medico == null) {
            model.addAttribute("erro", "Médico não encontrado.");
            return "redirect:/reunioes";
        }
        medico.setEspecialidades(especialidades);
        medico.setExperiencias(experiencias);
        medico.setTrabalhosPassados(trabalhosPassados);
        medicoRepository.save(medico);
        return "redirect:/reunioes?medicoId=" + id;
    }
}
