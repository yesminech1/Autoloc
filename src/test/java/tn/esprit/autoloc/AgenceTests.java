package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.service.IAgenceService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@Disabled("Tests d'intégration d'un atelier précédent")
class AgenceTests {

    private final AgenceRepositoryMock basicAgenceRepository;

    private final IAgenceRepository fullAgenceRepository;

    private final IAgenceService agenceService;

    @Autowired
    AgenceTests(AgenceRepositoryMock basicAgenceRepository,
                IAgenceRepository fullAgenceRepository,
                IAgenceService agenceService) {
        this.basicAgenceRepository = basicAgenceRepository;
        this.fullAgenceRepository = fullAgenceRepository;
        this.agenceService = agenceService;
    }

    private void addAgence(CrudRepository<Agence, Long> repository) {
        int ms = (int) System.currentTimeMillis();

        Agence agence = new Agence();
        agence.setAdresse("1 Rue Hedi");
        agence.setNom("Agence ariana");
        agence.setTelephone("71585874");
        agence.setVille("Tunis");

        Vehicule premierVehicule = new Vehicule();
        premierVehicule.setCategorie(CategorieVehicule.SUV);
        premierVehicule.setImmatriculation("78514TU96" + ms);
        premierVehicule.setMarque("Isuzu");
        premierVehicule.setModele("DMax");
        premierVehicule.setStatut(StatutVehicule.EN_MAINTENANCE);
        premierVehicule.setTarifJournalier(new BigDecimal("100"));
        premierVehicule.setAgence(agence);

        Vehicule deuxiemeVehicule = new Vehicule();
        deuxiemeVehicule.setCategorie(CategorieVehicule.UTILITAIRE);
        deuxiemeVehicule.setImmatriculation("78541TU95" + ms);
        deuxiemeVehicule.setMarque("Toyota");
        deuxiemeVehicule.setModele("Yaris");
        deuxiemeVehicule.setStatut(StatutVehicule.DISPONIBLE);
        deuxiemeVehicule.setTarifJournalier(new BigDecimal("80"));
        deuxiemeVehicule.setAgence(agence);

        agence.getVehicules().add(premierVehicule);
        agence.getVehicules().add(deuxiemeVehicule);

        repository.save(agence);
    }

    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    @Test
    void createAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence Tunis");
        agence.setVille("Tunis2026");
        agence.setAdresse("1 Rue de Tunis");
        agence.setTelephone("71111111");

        agenceService.create(agence);
    }

    private void loadAgence(CrudRepository<Agence, Long> repository, String typeDepot) {
        StringBuilder resultat = new StringBuilder("Depot utilise : ")
                .append(typeDepot)
                .append('\n');

        repository.findAll().forEach(agence -> {
            resultat.append("Agence ")
                    .append(agence.getIdAgence())
                    .append(" - ")
                    .append(agence.getNom())
                    .append(" - ")
                    .append(agence.getVehicules().size())
                    .append(" vehicule(s)\n");

            agence.getVehicules().forEach(vehicule -> resultat
                    .append("  Vehicule ")
                    .append(vehicule.getIdVehicule())
                    .append(" - ")
                    .append(vehicule.getImmatriculation())
                    .append('\n'));
        });

        assertTrue(resultat.length() > ("Depot utilise : " + typeDepot + '\n').length(),
                "Le dépôt doit contenir au moins une agence");
    }

    @Test
    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "AgenceRepositoryMock (CrudRepository)");
    }

    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "IAgenceRepository (JpaRepository)");
    }

    @Test
    void loadSortedAgences() {
        List<Agence> agences = fullAgenceRepository.findAll(
                Sort.by(Sort.Direction.DESC, "idAgence")
        );

        StringBuilder resultat = new StringBuilder();
        agences.forEach(agence -> resultat
                .append("Agence ")
                .append(agence.getIdAgence())
                .append(" - ")
                .append(agence.getNom())
                .append('\n'));

        assertFalse(agences.isEmpty(), "Le dépôt doit contenir au moins une agence");
    }

    @Test
    void loadPagedAgences() {
        Page<Agence> agences = fullAgenceRepository.findAll(
                PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "idAgence"))
        );

        StringBuilder resultat = new StringBuilder("Nombre total de pages : ")
                .append(agences.getTotalPages())
                .append('\n');

        while (true) {

            resultat.append("Page en cours : ")
                    .append(agences.getNumber())
                    .append('\n');

            agences.forEach(agence -> resultat
                    .append("Agence ")
                    .append(agence.getIdAgence())
                    .append(" - ")
                    .append(agence.getNom())
                    .append('\n'));

            if (!agences.hasNext()) {
                break;
            }

            agences = fullAgenceRepository.findAll(agences.nextPageable());
        }

        assertTrue(agences.getTotalElements() > 0, "Le dépôt doit contenir au moins une agence");
    }
}

@Repository
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}
