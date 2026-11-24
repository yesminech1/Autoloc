package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ContratDto {
    private Long idContrat;

    @NotNull(message = "La date de signature est obligatoire.")
    private LocalDate dateSignature;

    @NotNull(message = "Le montant total est obligatoire.")
    @DecimalMin(value = "0.01", message = "Le montant total doit être supérieur à zéro.")
    private BigDecimal montantTotal;

    private boolean valide;
}
