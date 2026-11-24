package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VehiculeDto {
    private Long idVehicule;

    @NotBlank(message = "L'immatriculation est obligatoire.")
    @Size(max = 20, message = "L'immatriculation ne peut pas dépasser 20 caractères.")
    private String immatriculation;

    @NotBlank(message = "La marque est obligatoire.")
    @Size(max = 50, message = "La marque ne peut pas dépasser 50 caractères.")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire.")
    @Size(max = 50, message = "Le modèle ne peut pas dépasser 50 caractères.")
    private String modele;

    @NotNull(message = "La catégorie du véhicule est obligatoire.")
    private CategorieVehicule categorie;

    @NotNull(message = "Le tarif journalier est obligatoire.")
    @DecimalMin(value = "0.01", message = "Le tarif journalier doit être supérieur à zéro.")
    private BigDecimal tarifJournalier;

    @NotNull(message = "Le statut du véhicule est obligatoire.")
    private StatutVehicule statut;

    @Positive(message = "L'identifiant de l'agence doit être positif.")
    private Long agenceId;
}
