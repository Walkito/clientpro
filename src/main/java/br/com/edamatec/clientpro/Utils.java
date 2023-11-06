package br.com.edamatec.clientpro;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean validateCPF(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> errosCPF = cpfValidator.invalidMessagesFor(cpf);
        if(errosCPF.size() > 0){
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean validateEmail(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
