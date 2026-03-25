package org.example.td23_spring.Controller;

import org.example.td23_spring.Service.WelcomeService;
import org.example.td23_spring.Validator.WelcomeValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private final WelcomeService welcomeService;
    private final WelcomeValidator welcomeValidator;

    public WelcomeController(WelcomeService welcomeService, WelcomeValidator welcomeValidator) {
        this.welcomeService = welcomeService;
        this.welcomeValidator = welcomeValidator;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name) {

        try {
            welcomeValidator.validateName(name);

            String message = welcomeService.getWelcomeMessage(name);

            return ResponseEntity.ok(message);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
