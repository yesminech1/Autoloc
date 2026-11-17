package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component
public class LongueDureeStrategy implements ITarifStrategy {
    @Override
    public VehiculeOffre getOffre() {
        return VehiculeOffre.LONGUE_DUREE;
    }

    @Override
    public double calculer(double tarifBase) {
        double t = tarifBase * 0.75;
        return t > 2000 ? t * 0.95 : t;
    }
}