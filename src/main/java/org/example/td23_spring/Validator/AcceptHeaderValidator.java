package org.example.td23_spring.Validator;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class AcceptHeaderValidator {
    public void validate(String acceptHeader) {
        if (acceptHeader == null) {
            throw new IllegalArgumentException("L'en-tête 'Accept' est requis.");
        }
        if (!acceptHeader.equals(MediaType.TEXT_PLAIN_VALUE) && !acceptHeader.equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new UnsupportedOperationException(
                    "Type de contenu non supporté. Utilisez 'text/plain' ou 'application/json'."
            );
        }
    }
}
