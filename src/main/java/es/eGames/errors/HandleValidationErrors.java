package es.eGames.errors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

/**
 * Created by daniel on 12/02/17.
 */
public class HandleValidationErrors {

    public static LinkedHashMap<String, List> mapErros(BindingResult bindingResult, Object entity) {
        LinkedHashMap<String, List> errors = new LinkedHashMap<String, List>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            if (errors.get(error.getField()) != null) {
                errors.get(error.getField()).add(error.getDefaultMessage());
            } else {
                List<String> messages = new ArrayList<>();
                messages.add(error.getDefaultMessage());
                errors.put(error.getField(), messages);
            }
        }
        return errors;
    }

}
