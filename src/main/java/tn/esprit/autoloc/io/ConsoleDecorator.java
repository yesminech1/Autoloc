package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.Agence;

@Component
public class ConsoleDecorator implements IRapport {

    private final IRapport delegate = new RapportBase();

    @Override
    public String generer(Agence agence, double total) {
        // Affichage console (l'entête)
        System.out.println("=== RAPPORT AGENCE ===");
        System.out.println("Id : " + agence.getIdAgence());
        System.out.println("Nom : " + agence.getNom());
        System.out.println("Num tel : " + agence.getTelephone());
        System.out.println("======================");

        return delegate.generer(agence, total);
    }
}