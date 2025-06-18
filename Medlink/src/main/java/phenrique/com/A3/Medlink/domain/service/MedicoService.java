package phenrique.com.A3.Medlink.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    // Salvar um novo médico
    public Medico salvar(Medico medico) {
        if (medicoRepository.existsByCrm(medico.getCrm())) {
            throw new RuntimeException("Já existe um médico com este CRM");
        }
        if (medicoRepository.existsByEmail(medico.getEmail())) {
            throw new RuntimeException("Já existe um médico com este email");
        }
        return medicoRepository.save(medico);
    }
    
    // Buscar médico por ID
    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }
    
    // Buscar médico por CRM
    public Optional<Medico> buscarPorCrm(String crm) {
        return medicoRepository.findByCrm(crm);
    }
    
    // Listar todos os médicos
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }
    
    // Buscar médicos por nome
    public List<Medico> buscarPorNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    // Atualizar médico
    public Medico atualizar(Long id, Medico medico) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado");
        }
        medico.setId(id);
        return medicoRepository.save(medico);
    }
    
    // Excluir médico
    public void excluir(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado");
        }
        medicoRepository.deleteById(id);
    }
}
