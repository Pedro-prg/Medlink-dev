package phenrique.com.A3.Medlink.api.common;

import java.util.regex.Pattern;
import java.time.DayOfWeek;

public class ValidationUtils {
    
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    private static final Pattern CRM_PATTERN = Pattern.compile("^\\d{1,6}$");
    
    /**
     * Valida CPF
     */
    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        
        // Remove formatação
        String cleanCPF = removeFormatting(cpf);
        
        // Verifica se tem 11 dígitos
        if (cleanCPF.length() != 11) {
            return false;
        }
        
        // Verifica se todos os dígitos são iguais
        if (cleanCPF.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        // Calcula primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cleanCPF.charAt(i)) * (10 - i);
        }
        int remainder = sum % 11;
        int digit1 = remainder < 2 ? 0 : 11 - remainder;
        
        // Calcula segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cleanCPF.charAt(i)) * (11 - i);
        }
        remainder = sum % 11;
        int digit2 = remainder < 2 ? 0 : 11 - remainder;
        
        // Verifica se os dígitos calculados são iguais aos do CPF
        return Character.getNumericValue(cleanCPF.charAt(9)) == digit1 &&
               Character.getNumericValue(cleanCPF.charAt(10)) == digit2;
    }
    
    /**
     * Valida CRM
     */
    public static boolean isValidCRM(String crm) {
        if (crm == null || crm.trim().isEmpty()) {
            return false;
        }
        
        // Remove formatação
        String cleanCRM = removeFormatting(crm);
        
        // Verifica se tem entre 1 e 6 dígitos
        if (cleanCRM.length() < 1 || cleanCRM.length() > 6) {
            return false;
        }
        
        // Verifica se contém apenas números
        return cleanCRM.matches("\\d+");
    }
    
    /**
     * Remove formatação de CPF ou CRM
     */
    public static String removeFormatting(String value) {
        if (value == null) {
            return "";
        }
        return value.replaceAll("[^\\d]", "");
    }
    
    /**
     * Formata CPF
     */
    public static String formatCPF(String cpf) {
        String cleanCPF = removeFormatting(cpf);
        if (cleanCPF.length() != 11) {
            return cpf; // Retorna original se não tiver 11 dígitos
        }
        return String.format("%s.%s.%s-%s", 
            cleanCPF.substring(0, 3),
            cleanCPF.substring(3, 6),
            cleanCPF.substring(6, 9),
            cleanCPF.substring(9, 11));
    }
    
    /**
     * Formata CRM
     */
    public static String formatCRM(String crm) {
        String cleanCRM = removeFormatting(crm);
        if (cleanCRM.isEmpty()) {
            return crm; // Retorna original se estiver vazio
        }
        return cleanCRM; // CRM não tem formatação específica, retorna apenas números
    }

    public static String dayOfWeekToDbString(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "SEGUNDA";
            case TUESDAY: return "TERCA";
            case WEDNESDAY: return "QUARTA";
            case THURSDAY: return "QUINTA";
            case FRIDAY: return "SEXTA";
            case SATURDAY: return "SABADO";
            default: return "DOMINGO";
        }
    }
} 