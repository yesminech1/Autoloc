package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.ITarifStrategy;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasicVehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long>
        implements IVehiculeService {

    private final Map<VehiculeOffre, ITarifStrategy> strategies;

    public BasicVehiculeServiceImpl(IVehiculeRepository repository, List<ITarifStrategy> strategyList) {
        super(repository);
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ITarifStrategy::getOffre, s -> s));
    }

    @Override
    public boolean check(Vehicule vehicule, boolean isNew) {
        return vehicule != null
                && (!isNew || vehicule.getIdVehicule() == null)
                && Utils.isNotEmpty(vehicule.getImmatriculation())
                && Utils.isNotEmpty(vehicule.getMarque())
                && Utils.isNotEmpty(vehicule.getModele())
                && vehicule.getCategorie() != null
                && vehicule.getTarifJournalier() != null
                && vehicule.getStatut() != null;
    }

    @Override
    protected String getEntityName() {
        return "Vehicule";
    }

    @Override
    protected Long getId(Vehicule vehicule) {
        return vehicule.getIdVehicule();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setCategorie(source.getCategorie());
        target.setTarifJournalier(source.getTarifJournalier());
        target.setStatut(source.getStatut());
        target.setAgence(source.getAgence());
    }

    @Override
    public double calculerTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre) {
        double tarifBase = vehicule.getTarifJournalier().doubleValue() * nbJours;

        ITarifStrategy strategy = strategies.get(offre);
        if (strategy == null) {
            throw new IllegalArgumentException("Offre inconnue : " + offre);
        }
        return strategy.calculer(tarifBase);
    }

    @Override
    public Vehicule desaffecte(Long id) {
        Vehicule vehicule = findById(id);
        vehicule.setAgence(null);
        return repository.save(vehicule);
    }
}
