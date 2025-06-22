package phenrique.com.A3.Medlink.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phenrique.com.A3.Medlink.domain.entities.Medico;
import phenrique.com.A3.Medlink.domain.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    // Salvar um novo médico
    public Medico salvar(Medico medico) {
        // Verificar se já existe médico com o mesmo email
        if (medicoRepository.findByEmail(medico.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um médico cadastrado com este email");
        }
        
        return medicoRepository.save(medico);
    }
    
    // Buscar médico por ID
    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }
    
    // Buscar médico por CRM
    public Optional<Medico> buscarPorCrm(String crm) {
        // Como o modelo antigo não tem findByCrm, vamos buscar todos e filtrar
        return medicoRepository.findAll().stream()
            .filter(m -> crm.equals(m.getCrm()))
            .findFirst();
    }
    
    // Listar todos os médicos
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }
    
    // Buscar médicos por nome
    public List<Medico> buscarPorNome(String nome) {
        // Como o modelo antigo não tem findByNomeContainingIgnoreCase, vamos buscar todos e filtrar
        return medicoRepository.findAll().stream()
            .filter(m -> m.getNome().toLowerCase().contains(nome.toLowerCase()))
            .collect(Collectors.toList());
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
