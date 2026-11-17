package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;

public interface IVehiculeService extends IGenericService<Vehicule, Long> {
    double calculerTarif (Vehicule vehicule, int nbJours, VehiculeOffre offre) ;
    Vehicule desaffecte(Long id);
}
