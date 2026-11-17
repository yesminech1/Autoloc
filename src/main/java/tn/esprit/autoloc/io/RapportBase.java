package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class RapportBase implements IRapport {
    @Override
    public String generer(Agence agence, double total) {
        return String.valueOf(total);
    }
}