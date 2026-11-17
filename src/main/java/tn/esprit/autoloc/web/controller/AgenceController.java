package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences de location")
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Lister toutes les agences")
    @ApiResponse(responseCode = "200", description = "Liste des agences récupérée avec succès")
    @GetMapping
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @Operation(summary = "Consulter une agence par son ID")
    @ApiResponse(responseCode = "200", description = "Agence trouvée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("/{id}")
    public Agence findById(
            @Parameter(description = "Identifiant unique de l'agence")
            @PathVariable Long id) {
        return agenceService.findById(id);
    }

    @Operation(summary = "Créer une nouvelle agence")
    @ApiResponse(responseCode = "201", description = "Agence créée avec succès")
    @ApiResponse(responseCode = "400", description = "Données invalides (ID non nul ou champs manquants)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @Operation(summary = "Modifier une agence existante")
    @ApiResponse(responseCode = "200", description = "Agence modifiée avec succès")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @PutMapping("/{id}")
    public Agence update(
            @Parameter(description = "Identifiant unique de l'agence")
            @PathVariable Long id,
            @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @Operation(summary = "Supprimer une agence")
    @ApiResponse(responseCode = "204", description = "Agence supprimée avec succès")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @Parameter(description = "Identifiant unique de l'agence")
            @PathVariable Long id) {
        agenceService.deleteById(id);
    }

    // --- AJOUT POUR L'ÉTAPE 10 ---
    @Operation(summary = "Simuler le tarif total d'une agence")
    @ApiResponse(responseCode = "200", description = "Rapport de simulation généré")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @ApiResponse(responseCode = "500", description = "Erreur interne (exception générée à l'étape 9)")
    @GetMapping("/{id}/simuler")
    public String simuler(
            @Parameter(description = "Identifiant unique de l'agence") @PathVariable Long id,
            @Parameter(description = "Nombre de jours de location") @RequestParam int jours,
            @Parameter(description = "Type d'offre (STANDARD, WEEKEND, LONGUE_DUREE)") @RequestParam VehiculeOffre offre) {

        Agence agence = agenceService.findById(id);

        return agenceService.simuler(agence, jours, offre);
    }
}
