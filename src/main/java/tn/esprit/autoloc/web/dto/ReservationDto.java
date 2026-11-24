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
public class ReservationDto {
    private Long idReservation;

    @NotNull(message = "La date de début est obligatoire.")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire.")
    private LocalDate dateFin;

    @NotBlank(message = "Le statut de la réservation est obligatoire.")
    @Size(max = 20, message = "Le statut ne peut pas dépasser 20 caractères.")
    private String statut;

    @NotNull(message = "Le client est obligatoire.")
    @Positive(message = "L'identifiant du client doit être positif.")
    private Long clientId;

    @NotNull(message = "Le véhicule est obligatoire.")
    @Positive(message = "L'identifiant du véhicule doit être positif.")
    private Long vehiculeId;

    @Positive(message = "L'identifiant du contrat doit être positif.")
    private Long contratId;
}
