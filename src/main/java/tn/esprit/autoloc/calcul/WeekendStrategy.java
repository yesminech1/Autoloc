package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

@Component
public class WeekendStrategy implements ITarifStrategy {
    @Override
    public VehiculeOffre getOffre() {
        return VehiculeOffre.WEEKEND;
    }

    @Override
    public double calculer(double tarifBase) {
        return tarifBase * 0.80;
    }
}