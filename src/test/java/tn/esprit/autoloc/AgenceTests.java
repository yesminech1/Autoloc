package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

@SpringBootTest
@EnableJpaRepositories(basePackages = "tn.esprit.autoloc")
@ComponentScan(basePackages = "tn.esprit.autoloc")
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    @Test
    public void addAgence() {
        // 7. Créer l'agence
        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");

        // 7. Créer les deux véhicules
        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("785414TU96");
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setStatut(StatutVehicule.EN_MAINTENANCE);
        v1.setTarifJournalier(new BigDecimal("100"));
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("785414TU95");
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setTarifJournalier(new BigDecimal("80"));
        v2.setAgence(agence);

        // Lier les véhicules à l'agence
        agence.getVehicules().add(v1);
        agence.getVehicules().add(v2);

        // 8. Persister via save
        agenceRepository.save(agence);
    }
    @Test
    public void loadAgence() {
        Iterable<Agence> agences = agenceRepository.findAll();

        StringBuilder sb = new StringBuilder();
        for (Agence agence : agences) {
            sb.append(agence.getIdAgence())
                    .append(" | ")
                    .append(agence.getNom())
                    .append(" Vehicules Count : ")
                    .append(agence.getVehicules().size());

            for (Vehicule v : agence.getVehicules()) {
                sb.append(" === ")
                        .append(v.getIdVehicule())
                        .append("|")
                        .append(v.getImmatriculation());
            }
            sb.append("\n");
        }

        // 14. Assertion qui échoue toujours pour afficher le résultat
        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}