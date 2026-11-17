package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

@Service
public class BasicMaintenanceServiceImpl extends GenericServiceImpl<Maintenance, Long>
        implements IMaintenanceService {

    public BasicMaintenanceServiceImpl(IMaintenanceRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Maintenance maintenance, boolean isNew) {
        return maintenance != null
                && (!isNew || maintenance.getIdMaintenance() == null)
                && maintenance.getDateDebut() != null
                && maintenance.getDescription() != null
                && !maintenance.getDescription().isBlank();
    }

    @Override
    protected String getEntityName() {
        return "Maintenance";
    }

    @Override
    protected Long getId(Maintenance maintenance) {
        return maintenance.getIdMaintenance();
    }

    @Override
    protected void setFields(Maintenance source, Maintenance target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setDescription(source.getDescription());
    }
}
