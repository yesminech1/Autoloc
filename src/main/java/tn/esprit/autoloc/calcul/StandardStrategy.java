package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component
public class StandardStrategy implements ITarifStrategy {
    @Override
    public VehiculeOffre getOffre() {
        return VehiculeOffre.STANDARD;
    }

    @Override
    public double calculer(double tarifBase) {
        return tarifBase;
    }
}