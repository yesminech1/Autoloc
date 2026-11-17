package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

@Service
public class BasicReservationServiceImpl extends GenericServiceImpl<Reservation, Long>
        implements IReservationService {

    public BasicReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    public boolean check(Reservation reservation, boolean isNew) {
        return reservation != null
                && (!isNew || reservation.getIdReservation() == null)
                && reservation.getDateDebut() != null
                && reservation.getDateFin() != null
                && Utils.isNotEmpty(reservation.getStatut());
    }

    @Override
    protected String getEntityName() {
        return "Reservation";
    }

    @Override
    protected Long getId(Reservation reservation) {
        return reservation.getIdReservation();
    }

    @Override
    protected void setFields(Reservation source, Reservation target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setStatut(source.getStatut());
        target.setClient(source.getClient());
        target.setVehicule(source.getVehicule());
        target.setContrat(source.getContrat());
    }

}
