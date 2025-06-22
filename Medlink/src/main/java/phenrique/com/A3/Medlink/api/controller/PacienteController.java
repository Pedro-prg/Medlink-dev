package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.domain.entities.Paciente;
import phenrique.com.A3.Medlink.domain.service.PacienteService;
import phenrique.com.A3.Medlink.api.model.users.PacienteInputDTO;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> cadastrarPaciente(@Valid @RequestBody PacienteInputDTO pacienteInput) {
        try {
            Paciente novoPaciente = new Paciente();
            novoPaciente.setNome(pacienteInput.getNome());
            novoPaciente.setCpf(pacienteInput.getCpf());
            novoPaciente.setEmail(pacienteInput.getEmail());
            novoPaciente.setSenha(pacienteInput.getSenha());
            
            pacienteService.savePaciente(novoPaciente);
            return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<?> buscarPaciente(@PathVariable String email) {
        Optional<Paciente> paciente = pacienteService.findPacienteById(email);
        
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Paciente>> buscarPorNome(@PathVariable String nome) {
        List<Paciente> pacientes = pacienteService.findByNome(nome);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteInputDTO pacienteInput) {
        try {
            // Primeiro buscar o paciente pelo ID
            Optional<Paciente> pacienteOpt = pacienteService.listarTodos().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
            
            if (pacienteOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Paciente pacienteAtualizado = pacienteOpt.get();
            pacienteAtualizado.setNome(pacienteInput.getNome());
            pacienteAtualizado.setCpf(pacienteInput.getCpf());
            pacienteAtualizado.setEmail(pacienteInput.getEmail());
            pacienteAtualizado.setSenha(pacienteInput.getSenha());
            
            pacienteService.atualizarPaciente(pacienteAtualizado);
            return ResponseEntity.ok(pacienteAtualizado);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarPaciente(@PathVariable Long id) {
        try {
            pacienteService.deletarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
