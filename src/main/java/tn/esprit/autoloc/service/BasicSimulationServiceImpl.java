package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.io.IRapport;
import tn.esprit.autoloc.io.RapportFactory;

@Service
@RequiredArgsConstructor
public class BasicSimulationServiceImpl implements ISimulationService {

    private final IVehiculeService vehiculeService;
    private final RapportFactory rapportFactory;

    @Override
    public String simuler(Agence agence, int nbJours, VehiculeOffre offre) {
        double total = 0;
        for (Vehicule v : agence.getVehicules()) {
            total += vehiculeService.calculerTarif(v, nbJours, offre);
        }

        IRapport rapport = rapportFactory.creerRapport();
        return rapport.generer(agence, total);
    }
}