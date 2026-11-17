package tn.esprit.autoloc.calcul;

public interface ITarifStrategy {
    VehiculeOffre getOffre();
    double calculer(double tarifBase);
}