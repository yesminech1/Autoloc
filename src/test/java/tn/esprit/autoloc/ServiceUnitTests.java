package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.calcul.ITarifStrategy;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.io.IRapport;
import tn.esprit.autoloc.io.RapportFactory;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IClientRepository;
import tn.esprit.autoloc.repository.IContratRepository;
import tn.esprit.autoloc.repository.IEmployeRepository;
import tn.esprit.autoloc.repository.IEquipementRepository;
import tn.esprit.autoloc.repository.IMaintenanceRepository;
import tn.esprit.autoloc.repository.IPaiementRepository;
import tn.esprit.autoloc.repository.IReservationRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.service.BasicAgenceServiceImpl;
import tn.esprit.autoloc.service.BasicClientServiceImpl;
import tn.esprit.autoloc.service.BasicContratServiceImpl;
import tn.esprit.autoloc.service.BasicEmployeServiceImpl;
import tn.esprit.autoloc.service.BasicEquipementServiceImpl;
import tn.esprit.autoloc.service.BasicMaintenanceServiceImpl;
import tn.esprit.autoloc.service.BasicPaiementServiceImpl;
import tn.esprit.autoloc.service.BasicReservationServiceImpl;
import tn.esprit.autoloc.service.BasicSimulationServiceImpl;
import tn.esprit.autoloc.service.BasicVehiculeServiceImpl;
import tn.esprit.autoloc.service.IGenericService;
import tn.esprit.autoloc.service.ISimulationService;
import tn.esprit.autoloc.service.IVehiculeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ServiceUnitTests {

    @Test
    void agenceServiceCreatesValidAgenceAndDelegatesSimulation() {
        IAgenceRepository repository = mock(IAgenceRepository.class);
        ISimulationService simulationService = mock(ISimulationService.class);
        BasicAgenceServiceImpl service = new BasicAgenceServiceImpl(repository, simulationService);
        Agence agence = agence();
        when(repository.save(agence)).thenReturn(agence);
        when(simulationService.simuler(agence, 3, VehiculeOffre.STANDARD)).thenReturn("rapport");

        assertCreates(service, repository, agence);
        agence.setIdAgence(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(agence));
        assertFalse(service.hasVehicules(1L));
        assertEquals("rapport", service.simuler(agence, 3, VehiculeOffre.STANDARD));
        verify(simulationService).simuler(agence, 3, VehiculeOffre.STANDARD);
    }

    @Test
    void clientServiceCreatesValidClient() {
        IClientRepository repository = mock(IClientRepository.class);
        BasicClientServiceImpl service = new BasicClientServiceImpl(repository);
        Client client = new Client();
        client.setNom("Chourou");
        client.setPrenom("Yesmine");
        client.setEmail("yesmine@example.com");
        client.setNumPermis("P-12345");
        client.setDateInscription(LocalDate.of(2026, 1, 1));
        when(repository.save(client)).thenReturn(client);

        assertCreates(service, repository, client);
    }

    @Test
    void contratServiceCreatesValidContrat() {
        IContratRepository repository = mock(IContratRepository.class);
        BasicContratServiceImpl service = new BasicContratServiceImpl(repository);
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.of(2026, 1, 1));
        contrat.setMontantTotal(new BigDecimal("250.00"));
        when(repository.save(contrat)).thenReturn(contrat);

        assertCreates(service, repository, contrat);
    }

    @Test
    void employeServiceCreatesValidEmploye() {
        IEmployeRepository repository = mock(IEmployeRepository.class);
        BasicEmployeServiceImpl service = new BasicEmployeServiceImpl(repository);
        Employe employe = new Employe();
        employe.setNom("Chourou");
        employe.setPrenom("Yesmine");
        employe.setRole("AGENT");
        when(repository.save(employe)).thenReturn(employe);

        assertCreates(service, repository, employe);
    }

    @Test
    void equipementServiceCreatesValidEquipement() {
        IEquipementRepository repository = mock(IEquipementRepository.class);
        BasicEquipementServiceImpl service = new BasicEquipementServiceImpl(repository);
        Equipement equipement = new Equipement();
        equipement.setLibelle("GPS");
        when(repository.save(equipement)).thenReturn(equipement);

        assertCreates(service, repository, equipement);
    }

    @Test
    void maintenanceServiceCreatesValidMaintenance() {
        IMaintenanceRepository repository = mock(IMaintenanceRepository.class);
        BasicMaintenanceServiceImpl service = new BasicMaintenanceServiceImpl(repository);
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.of(2026, 1, 1));
        maintenance.setDescription("Révision annuelle");
        when(repository.save(maintenance)).thenReturn(maintenance);

        assertCreates(service, repository, maintenance);
    }

    @Test
    void reservationServiceCreatesValidReservation() {
        IReservationRepository repository = mock(IReservationRepository.class);
        BasicReservationServiceImpl service = new BasicReservationServiceImpl(repository);
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.of(2026, 1, 1));
        reservation.setDateFin(LocalDate.of(2026, 1, 3));
        reservation.setStatut("CONFIRMEE");
        when(repository.save(reservation)).thenReturn(reservation);

        assertCreates(service, repository, reservation);
    }

    @Test
    void paiementServiceReadsAndRejectsUnknownPayment() {
        IPaiementRepository repository = mock(IPaiementRepository.class);
        BasicPaiementServiceImpl service = new BasicPaiementServiceImpl(repository);
        Paiement paiement = new Paiement();
        when(repository.findById(1L)).thenReturn(Optional.of(paiement));
        when(repository.findAll()).thenReturn(List.of(paiement));

        assertSame(paiement, service.findById(1L));
        assertEquals(List.of(paiement), service.findAll());
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.findById(2L));
    }

    @Test
    void vehiculeServiceCalculatesTarifAndDesaffectesAgence() {
        IVehiculeRepository repository = mock(IVehiculeRepository.class);
        ITarifStrategy weekendStrategy = mock(ITarifStrategy.class);
        when(weekendStrategy.getOffre()).thenReturn(VehiculeOffre.WEEKEND);
        when(weekendStrategy.calculer(200.0)).thenReturn(160.0);
        BasicVehiculeServiceImpl service = new BasicVehiculeServiceImpl(repository, List.of(weekendStrategy));
        Vehicule vehicule = vehicule();
        vehicule.setIdVehicule(1L);
        vehicule.setAgence(agence());
        when(repository.findById(1L)).thenReturn(Optional.of(vehicule));
        when(repository.save(vehicule)).thenReturn(vehicule);

        assertEquals(160.0, service.calculerTarif(vehicule, 2, VehiculeOffre.WEEKEND));
        assertSame(vehicule, service.desaffecte(1L));
        assertNull(vehicule.getAgence());
        assertThrows(IllegalArgumentException.class,
                () -> service.calculerTarif(vehicule, 2, VehiculeOffre.STANDARD));
    }

    @Test
    void simulationServiceCalculatesAndFormatsTotal() {
        IVehiculeService vehiculeService = mock(IVehiculeService.class);
        RapportFactory rapportFactory = mock(RapportFactory.class);
        IRapport rapport = mock(IRapport.class);
        BasicSimulationServiceImpl service = new BasicSimulationServiceImpl(vehiculeService, rapportFactory);
        Agence agence = agence();
        Vehicule first = vehicule();
        Vehicule second = vehicule();
        agence.setVehicules(Set.of(first, second));
        when(vehiculeService.calculerTarif(first, 2, VehiculeOffre.STANDARD)).thenReturn(100.0);
        when(vehiculeService.calculerTarif(second, 2, VehiculeOffre.STANDARD)).thenReturn(80.0);
        when(rapportFactory.creerRapport()).thenReturn(rapport);
        when(rapport.generer(agence, 180.0)).thenReturn("rapport total 180");

        assertEquals("rapport total 180", service.simuler(agence, 2, VehiculeOffre.STANDARD));
        verify(rapport).generer(agence, 180.0);
    }

    @Test
    void genericServiceRejectsInvalidInputWithoutSaving() {
        IEquipementRepository repository = mock(IEquipementRepository.class);
        BasicEquipementServiceImpl service = new BasicEquipementServiceImpl(repository);
        Equipement invalidEquipement = new Equipement();

        assertThrows(IllegalArgumentException.class, () -> service.create(invalidEquipement));
        verify(repository, never()).save(any(Equipement.class));
    }

    private static <T> void assertCreates(IGenericService<T, Long> service,
                                          JpaRepository<T, Long> repository,
                                          T entity) {
        assertSame(entity, service.create(entity));
        verify(repository).save(entity);
    }

    private static Agence agence() {
        Agence agence = new Agence();
        agence.setNom("Agence Tunis");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue de Tunis");
        agence.setTelephone("71111111");
        return agence;
    }

    private static Vehicule vehicule() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("123-TUN-456");
        vehicule.setMarque("Toyota");
        vehicule.setModele("Yaris");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("100.00"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);
        return vehicule;
    }
}
