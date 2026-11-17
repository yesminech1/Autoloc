package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

@Service
public class BasicEquipementServiceImpl extends GenericServiceImpl<Equipement, Long>
        implements IEquipementService {

    public BasicEquipementServiceImpl(IEquipementRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Equipement equipement, boolean isNew) {
        return equipement != null
                && (!isNew || equipement.getIdEquipement() == null)
                && Utils.isNotEmpty(equipement.getLibelle());
    }

    @Override
    protected String getEntityName() {
        return "Equipement";
    }

    @Override
    protected Long getId(Equipement equipement) {
        return equipement.getIdEquipement();
    }

    @Override
    protected void setFields(Equipement source, Equipement target) {
        target.setLibelle(source.getLibelle());
    }

}
