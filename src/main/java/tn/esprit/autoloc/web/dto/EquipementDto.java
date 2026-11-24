package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipementDto {
    private Long idEquipement;

    @NotBlank(message = "Le libellé de l'équipement est obligatoire.")
    @Size(max = 100, message = "Le libellé ne peut pas dépasser 100 caractères.")
    private String libelle;
}
