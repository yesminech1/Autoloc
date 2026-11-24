package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PaiementDto {
    private Long idPaiement;

    @NotNull(message = "Le montant du paiement est obligatoire.")
    @DecimalMin(value = "0.01", message = "Le montant du paiement doit être supérieur à zéro.")
    private BigDecimal montant;

    @NotNull(message = "La date de paiement est obligatoire.")
    private LocalDate datePaiement;

    @NotBlank(message = "Le mode de paiement est obligatoire.")
    @Size(max = 30, message = "Le mode de paiement ne peut pas dépasser 30 caractères.")
    private String modePaiement;

    @NotNull(message = "Le contrat associé est obligatoire.")
    @Positive(message = "L'identifiant du contrat doit être positif.")
    private Long contratId;
}
