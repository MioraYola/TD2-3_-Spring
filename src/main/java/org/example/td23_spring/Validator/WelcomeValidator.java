package org.example.td23_spring.Validator;

import org.springframework.stereotype.Component;

@Component
public class WelcomeValidator {
    public void validateName(String name){
        if (name == null || name.isBlank()) {
            throw  new IllegalArgumentException("Le paramètre 'name' est requis et ne peut être vide ");
        }
    }
}
