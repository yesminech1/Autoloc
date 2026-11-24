package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MaintenanceDto {
    private Long idMaintenance;

    @NotNull(message = "La date de début de maintenance est obligatoire.")
    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotBlank(message = "La description de la maintenance est obligatoire.")
    @Size(max = 1000, message = "La description ne peut pas dépasser 1000 caractères.")
    private String description;

    @Positive(message = "L'identifiant du véhicule doit être positif.")
    private Long vehiculeId;
}
