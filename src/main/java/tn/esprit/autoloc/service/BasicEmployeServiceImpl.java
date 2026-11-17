package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.IEmployeRepository;

@Service
public class BasicEmployeServiceImpl extends GenericServiceImpl<Employe, Long>
        implements IEmployeService {

    public BasicEmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Employe employe, boolean isNew) {
        return employe != null
                && (!isNew || employe.getIdEmploye() == null)
                && Utils.isNotEmpty(employe.getNom())
                && Utils.isNotEmpty(employe.getPrenom())
                && Utils.isNotEmpty(employe.getRole());
    }

    @Override
    protected String getEntityName() {
        return "Employe";
    }

    @Override
    protected Long getId(Employe employe) {
        return employe.getIdEmploye();
    }

    @Override
    protected void setFields(Employe source, Employe target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setRole(source.getRole());
        target.setAgence(source.getAgence());
    }

}
