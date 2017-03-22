package es.eGames.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by daniel on 12/02/17.
 */
public class HandleValidationErrors {

    public static Map<String, Object> mapErros(BindingResult bindingResult, Object entity) {
        Map<String, List> errors = new HashMap<String, List>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            if (errors.get(error.getField()) != null) {
                errors.get(error.getField()).add(error.getDefaultMessage());
            } else {
                List<String> messages = new ArrayList<>();
                messages.add(error.getDefaultMessage());
                errors.put(error.getField(), messages);
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errors", errors);
        map.put("entity", entity);

        return map;
    }

}
