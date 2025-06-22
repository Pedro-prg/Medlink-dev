package phenrique.com.A3.Medlink.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phenrique.com.A3.Medlink.api.common.ValidationUtils;
import phenrique.com.A3.Medlink.api.model.users.RegisterPacienteDTO;
import phenrique.com.A3.Medlink.api.model.users.RegisterMedicoDTO;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/validation")
@CrossOrigin(origins = "*")
public class ValidationController {
    
    /**
     * Valida CPF
     */
    @PostMapping("/cpf")
    public ResponseEntity<Map<String, Object>> validateCPF(@RequestBody Map<String, String> request) {
        String cpf = request.get("cpf");
        Map<String, Object> response = new HashMap<>();
        
        if (cpf == null || cpf.trim().isEmpty()) {
            response.put("valid", false);
            response.put("message", "CPF não pode ser vazio");
            return ResponseEntity.badRequest().body(response);
        }
        
        boolean isValid = ValidationUtils.isValidCPF(cpf);
        response.put("valid", isValid);
        response.put("cpf", cpf);
        response.put("cpfFormatado", ValidationUtils.formatCPF(ValidationUtils.removeFormatting(cpf)));
        
        if (isValid) {
            response.put("message", "CPF válido");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "CPF inválido");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * Valida CRM
     */
    @PostMapping("/crm")
    public ResponseEntity<Map<String, Object>> validateCRM(@RequestBody Map<String, String> request) {
        String crm = request.get("crm");
        Map<String, Object> response = new HashMap<>();
        
        if (crm == null || crm.trim().isEmpty()) {
            response.put("valid", false);
            response.put("message", "CRM não pode ser vazio");
            return ResponseEntity.badRequest().body(response);
        }
        
        boolean isValid = ValidationUtils.isValidCRM(crm);
        response.put("valid", isValid);
        response.put("crm", crm);
        response.put("crmFormatado", ValidationUtils.formatCRM(ValidationUtils.removeFormatting(crm)));
        
        if (isValid) {
            response.put("message", "CRM válido");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "CRM inválido");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * Valida registro de paciente
     */
    @PostMapping("/paciente")
    public ResponseEntity<Map<String, Object>> validatePaciente(@Valid @RequestBody RegisterPacienteDTO pacienteDTO) {
        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        response.put("message", "Dados do paciente válidos");
        response.put("paciente", pacienteDTO);
        response.put("cpfFormatado", pacienteDTO.getCpfFormatado());
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Valida registro de médico
     */
    @PostMapping("/medico")
    public ResponseEntity<Map<String, Object>> validateMedico(@Valid @RequestBody RegisterMedicoDTO medicoDTO) {
        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        response.put("message", "Dados do médico válidos");
        response.put("medico", medicoDTO);
        response.put("crmFormatado", medicoDTO.getCrmFormatado());
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Remove formatação de CPF ou CRM
     */
    @PostMapping("/remove-formatting")
    public ResponseEntity<Map<String, Object>> removeFormatting(@RequestBody Map<String, String> request) {
        String value = request.get("value");
        Map<String, Object> response = new HashMap<>();
        
        if (value == null || value.trim().isEmpty()) {
            response.put("error", "Valor não pode ser vazio");
            return ResponseEntity.badRequest().body(response);
        }
        
        String cleanValue = ValidationUtils.removeFormatting(value);
        response.put("original", value);
        response.put("clean", cleanValue);
        response.put("length", cleanValue.length());
        
        return ResponseEntity.ok(response);
    }
} 