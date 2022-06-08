package com.devsuperior.bds03.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 4260244710442093136L;
    
    private List<FieldMessage> errors = new ArrayList<>();
    
    public List<FieldMessage> getErrors() {
        return errors;
    }
    
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
