package org.example.td23_spring.Service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    public String getWelcomeMessage(String name){
        return "Bienvenue " + name+ " !";
    }
}
