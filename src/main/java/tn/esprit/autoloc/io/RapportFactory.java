package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;

@Component
public class RapportFactory {
    public IRapport creerRapport() {
        return new ConsoleDecorator(); // La factory renvoie le Decorator
    }
}