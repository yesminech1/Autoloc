package tn.esprit.autoloc.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.service.IVehiculeService;
import tn.esprit.autoloc.web.controller.AgenceController;
import tn.esprit.autoloc.web.controller.MaintenanceController;
import tn.esprit.autoloc.web.controller.VehiculeController;
import tn.esprit.autoloc.web.mapper.AgenceMapperImpl;
import tn.esprit.autoloc.web.mapper.MaintenanceMapperImpl;
import tn.esprit.autoloc.web.mapper.VehiculeMapperImpl;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {
        AgenceController.class,
        VehiculeController.class,
        MaintenanceController.class
})
@Import({
        AgenceMapperImpl.class,
        VehiculeMapperImpl.class,
        MaintenanceMapperImpl.class,
        GlobalExceptionHandler.class
})
class ControllerValidationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IAgenceService agenceService;

    @MockitoBean
    private IVehiculeService vehiculeService;

    @MockitoBean
    private IMaintenanceService maintenanceService;

    @Test
    void agenceListReturnsDtosInsteadOfEntities() throws Exception {
        Agence agence = new Agence();
        agence.setIdAgence(7L);
        agence.setNom("Agence Tunis");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue de Tunis");
        agence.setTelephone("71111111");
        when(agenceService.findAll()).thenReturn(List.of(agence));

        mockMvc.perform(get("/agences"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idAgence").value(7))
                .andExpect(jsonPath("$[0].nom").value("Agence Tunis"))
                .andExpect(jsonPath("$[0].vehicules").doesNotExist());
    }

    @Test
    void agenceCreateAndUpdateRejectInvalidDtosAndReturnClientMessages() throws Exception {
        String invalidAgence = """
                {"nom":"","ville":"","adresse":"","telephone":""}
                """;

        mockMvc.perform(post("/agences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidAgence))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("nom")));

        mockMvc.perform(put("/agences/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidAgence))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("téléphone")));

        verifyNoInteractions(agenceService);
    }

    @Test
    void vehiculeCreateAndUpdateRejectMissingImmatriculation() throws Exception {
        String invalidVehicule = """
                {"immatriculation":" ","marque":"Toyota","modele":"Yaris",
                 "categorie":"CITADINE","tarifJournalier":100,"statut":"DISPONIBLE"}
                """;

        mockMvc.perform(post("/vehicules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidVehicule))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("immatriculation")));

        mockMvc.perform(put("/vehicules/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidVehicule))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("immatriculation")));

        verifyNoInteractions(vehiculeService);
    }

    @Test
    void maintenanceCreateAndUpdateRejectEmptyDescription() throws Exception {
        String invalidMaintenance = """
                {"dateDebut":"2026-01-01","description":" ","vehiculeId":1}
                """;

        mockMvc.perform(post("/maintenances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidMaintenance))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("description")));

        mockMvc.perform(put("/maintenances/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidMaintenance))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("description")));

        verifyNoInteractions(maintenanceService);
    }
}
