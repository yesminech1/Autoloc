package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
public class BasicAgenceServiceImpl extends GenericServiceImpl<Agence, Long>
        implements IAgenceService {

    private final ISimulationService simulationService;

    // Constructeur propre
    public BasicAgenceServiceImpl(IAgenceRepository repository,
                                  ISimulationService simulationService) {
        super(repository);
        this.simulationService = simulationService;
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return agence != null
                && (!isNew || agence.getIdAgence() == null)
                && Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getVille())
                && Utils.isNotEmpty(agence.getAdresse())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence agence) {
        return agence.getIdAgence();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }

    @Override
    public boolean hasVehicules(Long id) {
        return !findById(id).getVehicules().isEmpty();
    }

    @Override
    public String simuler(Agence agence, int nbJours, VehiculeOffre offre) {
        // Plus d'exception ici ! On délègue au service dédié (Étape 11)
        return simulationService.simuler(agence, nbJours, offre);
    }
}
