package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.autoloc.domain.Agence;

@Component
public class ConsoleDecorator implements IRapport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleDecorator.class);
    private final IRapport delegate = new RapportBase();

    @Override
    public String generer(Agence agence, double total) {
        LOGGER.info("=== RAPPORT AGENCE ===");
        LOGGER.info("Id : {}", agence.getIdAgence());
        LOGGER.info("Nom : {}", agence.getNom());
        LOGGER.info("Num tel : {}", agence.getTelephone());
        LOGGER.info("======================");

        return delegate.generer(agence, total);
    }
}
