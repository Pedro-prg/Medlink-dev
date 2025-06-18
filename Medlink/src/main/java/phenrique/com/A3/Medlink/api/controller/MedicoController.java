package phenrique.com.A3.Medlink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.api.model.users.MedicoInputDTO;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.service.MedicoService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> cadastrarMedico(@Valid @RequestBody MedicoInputDTO medicoInput) {
        try {
            Medico novoMedico = new Medico(
                medicoInput.getNome(),
                medicoInput.getCrm(),
                medicoInput.getTelefone(),
                medicoInput.getEmail(),
                medicoInput.getSenha()
            );
            
            Medico medicoCadastrado = medicoService.salvar(novoMedico);
            return new ResponseEntity<>(medicoCadastrado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Medico>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/buscar{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<?> buscarPorCrm(@PathVariable String crm) {
        return medicoService.buscarPorCrm(crm)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Medico>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(medicoService.buscarPorNome(nome));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarMedico(
            @PathVariable Long id,
            @Valid @RequestBody MedicoInputDTO medicoInput) {
        try {
            Medico medicoAtualizado = new Medico(
                medicoInput.getNome(),
                medicoInput.getCrm(),
                medicoInput.getTelefone(),
                medicoInput.getEmail(),
                medicoInput.getSenha()
            );
            
            return ResponseEntity.ok(medicoService.atualizar(id, medicoAtualizado));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> excluirMedico(@PathVariable Long id) {
        try {
            medicoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
