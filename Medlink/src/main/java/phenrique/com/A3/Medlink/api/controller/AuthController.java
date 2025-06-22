package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.entities.Paciente;
import phenrique.com.A3.Medlink.domain.repository.MedicoRepository;
import phenrique.com.A3.Medlink.domain.repository.PacienteRepository;

@Controller
public class AuthController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model model) {
        var medicoOpt = medicoRepository.findByEmail(email);
        if (medicoOpt.isPresent() && medicoOpt.get().getSenha().equals(senha)) {
            return "redirect:/reunioes?medicoId=" + medicoOpt.get().getId();
        }
        var pacienteOpt = pacienteRepository.findByEmail(email);
        if (pacienteOpt.isPresent() && pacienteOpt.get().getSenha().equals(senha)) {
            return "redirect:/reunioes?pacienteId=" + pacienteOpt.get().getId();
        }
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login";
    }

    @GetMapping("/registro")
    public String registroForm() {
        return "register";
    }

    @PostMapping("/registro")
    public String registrar(@RequestParam String nome,
                            @RequestParam String email,
                            @RequestParam String senha,
                            @RequestParam String tipo,
                            @RequestParam(required = false) String crm,
                            @RequestParam(required = false) String cpf,
                            @RequestParam(required = false) String especialidades,
                            @RequestParam(required = false) String experiencias,
                            @RequestParam(required = false) String trabalhosPassados,
                            Model model) {
        if ("MEDICO".equals(tipo)) {
            if (medicoRepository.findByEmail(email).isPresent()) {
                model.addAttribute("erro", "E-mail já cadastrado.");
                return "register";
            }
            Medico medico = new Medico();
            medico.setNome(nome);
            medico.setEmail(email);
            medico.setSenha(senha);
            medico.setCrm(crm);
            medico.setEspecialidades(especialidades);
            medico.setExperiencias(experiencias);
            medico.setTrabalhosPassados(trabalhosPassados);
            medicoRepository.save(medico);
        } else if ("PACIENTE".equals(tipo)) {
            if (pacienteRepository.findByEmail(email).isPresent()) {
                model.addAttribute("erro", "E-mail já cadastrado.");
                return "register";
            }
            Paciente paciente = new Paciente();
            paciente.setNome(nome);
            paciente.setEmail(email);
            paciente.setSenha(senha);
            paciente.setCpf(cpf);
            pacienteRepository.save(paciente);
        }
        return "redirect:/login";
    }
}
